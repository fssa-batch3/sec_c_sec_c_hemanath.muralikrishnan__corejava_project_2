package com.fssa.agrokart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fssa.agrokart.exception.ConnectionException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.MainOrderHistory;
import com.fssa.agrokart.model.OrderStatus;
import com.fssa.agrokart.model.PaymentMethod;
import com.fssa.agrokart.model.PaymentStatus;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductAvailableStock;
import com.fssa.agrokart.model.ProductQuantitiesCategory;
import com.fssa.agrokart.model.ProductStatus;
import com.fssa.agrokart.model.ProductStockUnits;
import com.fssa.agrokart.model.SubOrderItems;
import com.fssa.agrokart.util.ConnectionUtil;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

import ch.qos.logback.classic.Logger;

public class OrderDAO {

	public List<ProductQuantitiesCategory> findQtyCat(List<SubOrderItems> items) throws DAOException {

		List<ProductQuantitiesCategory> listOfQty = new ArrayList<>();
		String sql = "SELECT pq.id, pq.product_id, pq.weight, pu.unit AS unit, pq.rupees FROM product_quantities_cate pq "
				+ "JOIN units pu ON pq.unit_id = pu.id WHERE pq.id = ?";

		try (Connection conn = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = conn.prepareStatement(sql)) {
				for (SubOrderItems item : items) {

					pst.setInt(1, item.getQtyId());

					// Execute the query and get the result set.
					try (ResultSet rs = pst.executeQuery()) {
						while (rs.next()) {

							ProductQuantitiesCategory productQty = new ProductQuantitiesCategory();
							productQty.setId(rs.getInt("id"));
							productQty.setProductId(rs.getInt("product_id"));
							productQty.setWeight(rs.getDouble("weight"));
							productQty.setUnit(ProductStockUnits.valueOf(rs.getString("unit").toUpperCase()));
							productQty.setRs(rs.getDouble("rupees"));

							listOfQty.add(productQty);
						}
					}
				}
			}
		} catch (ConnectionException | SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Failed to read the quantities cate: " + e.getMessage(), e);
		}

		return listOfQty;
	}

	public List<Product> findBasicDetails(List<SubOrderItems> items) throws DAOException {

		List<Product> listOfProduct = new ArrayList<>();

		String sql = "SELECT ps.name AS status, pa.weight AS stock FROM "
				+ "product pr JOIN status ps ON pr.status_id = ps.id LEFT JOIN product_available_stock pa ON pa.product_id = pr.id WHERE pr.id = ?";

		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				for (SubOrderItems ele : items) {

					pst.setInt(1, ele.getProductId());

					try (ResultSet rs = pst.executeQuery()) {

						while (rs.next()) {

							Product product = new Product();
							product.setId(ele.getProductId());
							product.setStatus(ProductStatus.valueOf(rs.getString("status").toUpperCase()));

							ProductAvailableStock stock = new ProductAvailableStock();
							stock.setNum(rs.getDouble("stock"));

							product.setAvailableStock(stock);

							listOfProduct.add(product);
						}
					}
				}
			}
		} catch (ConnectionException | SQLException e) {

			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Failed to read product basic details: " + e.getMessage(), e);
		}

		return listOfProduct;
	}

	public boolean OrderCreation(MainOrderHistory order) throws DAOException {

		String sql = "INSERT INTO user_order (user_id, user_address, total_products, delivery_day, total_amount, order_status, razorpay_order_id, payment_method, payment_status, is_order_cancelled)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)"; // Added a placeholder for the 10th value

		int affectedRows = 0;
		int generatedOrderId = -1; // Default value in case of failure

		try {
			Connection conn = ConnectionUtil.getConnection();
			conn.setAutoCommit(false); // Start transaction

			try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				stmt.setInt(1, order.getUserId());
				stmt.setString(2, order.getUserAddress());
				stmt.setInt(3, order.getTotalProducts());
				stmt.setDate(4, java.sql.Date.valueOf(order.getDeliveryDay()));
				stmt.setDouble(5, order.getTotalAmount());
				stmt.setString(6, order.getOrderStatus().toString());
				stmt.setString(7, order.getRazorpay_order_id());
				stmt.setString(8, order.getPaymentMethod().toString());
				if (order.getPaymentStatus() == null) {
					stmt.setString(9, null);

				} else {

					stmt.setString(9, order.getPaymentStatus().toString());
				}
				stmt.setBoolean(10, false); // Set the 10th parameter

				affectedRows = stmt.executeUpdate(); // Execute the SQL statement

				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedOrderId = generatedKeys.getInt(1);
					}
				}

				if (!(subOrderItems(order.getOrderItems(), conn, generatedOrderId))) {
					conn.rollback();
					return false;
				}

				conn.commit(); // Commit the transaction

			} catch (SQLException e) {
				conn.rollback(); // Rollback the transaction in case of an exception
				ExceptionLoggerUtil.logException(e);
				throw new DAOException("Error While inserting the order history", e);

			} finally {
				conn.setAutoCommit(true); // Reset auto-commit mode
			}
		} catch (ConnectionException | SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while inserting the order details: " + e.getMessage(), e);
		}

		return (affectedRows == 1);
	}

	// Assuming you have a method like this to handle sub-order items
	public boolean subOrderItems(List<SubOrderItems> orderItems, Connection conn, int orderId) throws DAOException {

		String sql = "INSERT INTO user_order_items(seller_id, main_order_id, product_id, product_qty_cat_id, nos_of_qty) VALUES (?,?,?,?,?)";

		int[] affectedRows;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (SubOrderItems item : orderItems) {
				stmt.setInt(1, item.getSellerId());
				stmt.setInt(2, orderId);
				stmt.setInt(3, item.getProductId());
				stmt.setInt(4, item.getQtyId());
				stmt.setInt(5, item.getQtyNos());
				stmt.addBatch();
			}

			// Execute the batch insertion
			affectedRows = stmt.executeBatch();
		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while inserting the sub order details " + e.getMessage(), e);
		}

		// Return true if no exception occurred and all rows were affected
		return Arrays.stream(affectedRows).allMatch(rowsAffected -> rowsAffected > 0);

	}

	public List<MainOrderHistory> readAllOrderHistory(int userId) throws DAOException {

		String sql = "SELECT id,user_id,user_address,total_products,delivery_day,total_amount,order_status,payment_method,payment_status,is_order_cancelled,creation_date_time "
				+ "FROM user_order WHERE user_id = ?";

		List<MainOrderHistory> listOfHistory = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, userId);

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						MainOrderHistory history = new MainOrderHistory();
						history.setUniqueId(rs.getInt("id"));
						history.setUserId(rs.getInt("user_id"));
						history.setUserAddress(rs.getString("user_address"));
						history.setTotalProducts(rs.getInt("total_products"));
						history.setDeliveryDay(rs.getDate("delivery_day").toLocalDate());
						history.setTotalAmount(rs.getDouble("total_amount"));
						history.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
						history.setPaymentMethod(PaymentMethod.valueOf(rs.getString("payment_method")));

						if (rs.getString("payment_status") != null) {
							history.setPaymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")));
						}
						history.setOrderCancelled(rs.getBoolean("is_order_cancelled"));
						history.setOrderItems(readAllSubOrders(rs.getInt("id")));
						history.setOrderCreationDateTime(rs.getTimestamp("creation_date_time").toLocalDateTime());

						listOfHistory.add(history);

					}

				}
			}
		} catch (SQLException | ConnectionException e) {

			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while reading the order History " + e.getMessage(), e);
		}

		return listOfHistory;

	}

	public List<SubOrderItems> readAllSubOrders(int mainOrderId) throws DAOException {

		String sql = "SELECT seller_id,main_order_id,product_id,product_qty_cat_id,nos_of_qty FROM user_order_items WHERE main_order_id = ?";

		List<SubOrderItems> orderItems = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, mainOrderId);

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						SubOrderItems item = new SubOrderItems();

						item.setMainOrderId(mainOrderId);
						item.setProductId(rs.getInt("product_id"));
						item.setQtyId(rs.getInt("product_qty_cat_id"));
						item.setQtyNos(rs.getInt("nos_of_qty"));
						item.setSellerId(rs.getInt("seller_id"));

						orderItems.add(item);

					}

				}
			}
		} catch (ConnectionException | SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while reading suborder items " + e.getMessage(), e);
		}

		return orderItems;
	}

	public boolean cancelOrder(int mainOrderId, int userId) throws DAOException {

		String sql = "UPDATE user_order SET is_order_cancelled = ?,order_status=?  WHERE id = ? AND user_id = ?";

		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setBoolean(1, true);
				stmt.setString(2, OrderStatus.CANCELLED.toString());
				stmt.setInt(3, mainOrderId);
				stmt.setInt(4, userId);

				int result = stmt.executeUpdate();

				return result > 0;
			}

		} catch (ConnectionException | SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while cancelling the order" + e.getMessage(), e);
		}
	}

}
