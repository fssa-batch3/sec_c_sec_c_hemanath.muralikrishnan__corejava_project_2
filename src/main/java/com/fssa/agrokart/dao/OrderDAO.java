package com.fssa.agrokart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.agrokart.exception.ConnectionException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductQuantitiesCategory;
import com.fssa.agrokart.model.ProductStockUnits;
import com.fssa.agrokart.model.SubOrderItems;
import com.fssa.agrokart.util.ConnectionUtil;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

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
							productQty.setUnit(ProductStockUnits.valueOf(rs.getString("unit")));
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

		String sql = "";

		try (Connection conn = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

			}
		} catch (ConnectionException | SQLException e) {

			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Failed to read product basic details: " + e.getMessage(), e);
		}

		return listOfProduct;
	}

}
