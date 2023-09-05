package com.fssa.agrokart.dao;

import com.fssa.agrokart.model.*;
import com.fssa.agrokart.util.*;
import com.fssa.agrokart.model.ProductCategory;
import com.fssa.agrokart.model.ProductStatus;
import com.fssa.agrokart.model.ProductStockUnits;
import com.fssa.agrokart.exception.*;
import com.fssa.agrokart.validator.ProductConstants;
import java.sql.*;
import java.util.*;

/**
 * A class which holds the Data access object It has method with sql queries the
 * methods will do CRUD operations on the product model object
 *
 * @author HemanathMuralikrishnan
 */
public class ProductDAO {

	// to print the success messages
	Logger logger = new Logger();

	/**
	 * Inserts a new product into the database along with its related information,
	 * such as available stock, nutrition, and quantities.
	 *
	 * @param product The product to be inserted.
	 * @return True if the product insertion and related information are successful,
	 *         otherwise false.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public boolean insertProduct(Product product) throws DAOException {

		int generatedProductId = -1; // Default value in case of failure
		int categoryId = getCategoryIDByName(product.getCategory().toString().toLowerCase());
		int statusId = getStatusIDByName(product.getStatus().toString().toLowerCase());
		int affectedRows = 0;

		try (Connection conn = ConnectionUtil.getConnection()) {

			conn.setAutoCommit(false); // Start transaction

			String sql = "INSERT INTO product (eng_name, tam_name, image_Url, category_id, description, status_id)"
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				stmt.setString(1, product.getName().getEnglishName().trim());
				stmt.setString(2, product.getName().getTamilName().trim());
				stmt.setString(3, product.getImageUrl().trim());
				stmt.setInt(4, categoryId); // Set the category ID obtained from getCategoryIDByName()
				stmt.setString(5, product.getDescription().trim());
				stmt.setInt(6, statusId);
				affectedRows = stmt.executeUpdate();

				if (affectedRows > 0) {
					try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							generatedProductId = generatedKeys.getInt(1);
						}
					}
				}

				// Call methods to add available stock, nutrition, and quantities
				if (!addAvailableStock(conn, product.getAvailableStock(), generatedProductId)
						|| !addNutr(conn, product.getNutrition(), generatedProductId)
						|| !addQuantities(conn, product.getQuantities(), generatedProductId)) {
					conn.rollback(); // Rollback the transaction if any of the sub-methods fail
					return false;
				}

				conn.commit(); // Commit the transaction
				logger.info("Product inserted successfully.");

			} catch (SQLException e) {
				conn.rollback(); // Rollback the transaction in case of an exception
				ExceptionLoggerUtil.logException(e);
				throw new DAOException(ProductDAOErrors.INSERT_ERROR, e);

			} finally {
				conn.setAutoCommit(true); // Reset auto-commit mode
			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.INSERT_ERROR, e);
		}

		return (affectedRows == 1);
	}

	/**
	 * Adds available stock for a product to the database.
	 *
	 * @param conn      The database connection to use.
	 * @param stock     The product's available stock details.
	 * @param productId The ID of the product.
	 * @return True if the stock was successfully added, false otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean addAvailableStock(Connection conn, ProductAvailableStock stock, int productId) throws DAOException {

//		get the unit_id from the product units table
		int unitId = getUnitIdByName(stock.getUnit().toString().toLowerCase());

		String sql = "INSERT INTO product_available_stock(product_id, weight, unit_id) " + "VALUES(?,?,?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, productId);
			stmt.setDouble(2, stock.getNum());
			stmt.setInt(3, unitId);

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.AVAILABLE_STOCK_ERROR, e);
		}
	}

	/**
	 * Adds nutrition information for a product to the database.
	 *
	 * @param conn      The database connection to use.
	 * @param nutr      The nutrition details of the product.
	 * @param productId The ID of the product.
	 * @return True if the nutrition information was successfully added, false
	 *         otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean addNutr(Connection conn, ProductNutrition nutr, int productId) throws DAOException {

//		get the unit_id for protein and carbo
		int procarboId = getUnitIdByName(ProductConstants.PROTEIN_CARBOHYDRATES_UNIT);

//		get the unit_id for kcal
		int caloriesId = getUnitIdByName(ProductConstants.CALORIES_UNIT);

		String sql = "INSERT INTO product_nutr(product_id, protein, protein_unit_id, "
				+ "carbohydrates, carbo_unit_id, calories, cal_unit_id) " + "VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, productId);
			stmt.setDouble(2, nutr.getProteinNum());
			stmt.setInt(3, procarboId);
			stmt.setDouble(4, nutr.getCarbonNumb());
			stmt.setInt(5, procarboId);
			stmt.setDouble(6, nutr.getKcalNum());
			stmt.setInt(7, caloriesId);

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;

		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.NUTR_ERROR, e);
		}
	}

	/**
	 * Adds quantity information for a product in the database.
	 *
	 * @param conn      The database connection to use.
	 * @param qty       The quantity information to be added.
	 * @param productId The ID of the product for which to add quantity information.
	 * @return True if adding quantity information is successful for all rows,
	 *         otherwise false.
	 * @throws DAOException If there's an error during the database operation or if
	 *                      any rows were not affected.
	 */
	public boolean addQuantities(Connection conn, SortedSet<ProductQuantitiesCategory> qty, int productId)
			throws DAOException {

		String sql = "INSERT INTO product_quantities_cate (product_id, weight, unit_id, rupees) VALUES (?, ?, ?, ?)";

		int[] affectedRows;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (ProductQuantitiesCategory quantity : qty) {
				int unitId = getUnitIdByName(quantity.getUnit().toString().toLowerCase());
				stmt.setInt(1, productId);
				stmt.setDouble(2, quantity.getWeight());
				stmt.setInt(3, unitId);
				stmt.setDouble(4, quantity.getRs());
				stmt.addBatch();
			}

			// Execute the batch insertion
			affectedRows = stmt.executeBatch();
		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.QTY_ERROR, e);
		}

		// Return true if no exception occurred and all rows were affected
		return Arrays.stream(affectedRows).allMatch(rowsAffected -> rowsAffected > 0);
	}

	/**
	 * Retrieves the ID of a category based on its name from the database.
	 *
	 * @param categoryName The name of the category for which to retrieve the ID.
	 * @return The ID of the category if found, otherwise -1.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public int getCategoryIDByName(String categoryName) throws DAOException {

		int categoryId = -1; // Default value in case of failure

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT id FROM categories WHERE name = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, categoryName);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					categoryId = rs.getInt("id");
				}

			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.CATEGORY_ERROR, e);
		}

		return categoryId;
	}

	/**
	 * Retrieves the ID of a status based on its name from the database.
	 *
	 * @param status The name of the status for which to retrieve the ID.
	 * @return The ID of the status if found, otherwise -1.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public int getStatusIDByName(String status) throws DAOException {

		int statusId = -1; // Default value in case of failure

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT id FROM status WHERE name = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, status);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {

					statusId = rs.getInt("id");
				}

			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.STATUS_ERROR, e);
		}

		return statusId;
	}

	/**
	 * Retrieves the ID of a unit based on its name from the database.
	 *
	 * @param unit The name of the unit for which to retrieve the ID.
	 * @return The ID of the unit if found, otherwise -1.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public int getUnitIdByName(String unit) throws DAOException {

		int unitId = -1; // Default value in case of failure

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT id FROM units WHERE unit = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, unit);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {

					unitId = rs.getInt("id");
				}

			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.UNIT_ID_ERROR, e);
		}

		return unitId;

	}

	/**
	 * Deletes a product and its associated data from the database.
	 *
	 * @param productId The ID of the product to be deleted.
	 * @return True if the product and its associated data are successfully deleted,
	 *         otherwise false.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public boolean deleteProduct(int productId) throws DAOException {

		int rowsAffected = 0;

		try (Connection conn = ConnectionUtil.getConnection()) {

			conn.setAutoCommit(false); // Start transaction

			String deleteProductSql = "DELETE FROM product WHERE id = ?";

			try (PreparedStatement deleteProductStmt = conn.prepareStatement(deleteProductSql)) {

				deleteProductStmt.setInt(1, productId);

				if (!deleteProductAvblStock(conn, productId) || !deleteProductNutr(conn, productId)
						|| !deleteProductQutCate(conn, productId)) {

					conn.rollback();
					return false;
				}

				rowsAffected = deleteProductStmt.executeUpdate();

				conn.commit();// Commit the transaction
				logger.info("Product deletion successful.");
			} catch (SQLException e) {
				conn.rollback();
				throw new DAOException(ProductDAOErrors.DELETE_ERROR, e);
			} finally {
				conn.setAutoCommit(true); // Reset auto-commit mode
			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.DELETE_ERROR, e);
		}

		return (rowsAffected == 1);
	}

	/**
	 * Deletes available stock data associated with a product from the database.
	 * 
	 * @param conn The database connection to use.
	 * @param id   The ID of the product for which to delete available stock data.
	 * @return True if the available stock data is successfully deleted, otherwise
	 *         false.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public boolean deleteProductAvblStock(Connection conn, int id) throws DAOException {

		String deleteStockSql = "DELETE FROM product_available_stock WHERE product_id = ?";

		try (PreparedStatement deleteStockStmt = conn.prepareStatement(deleteStockSql)) {

			deleteStockStmt.setInt(1, id);
			int rowsAffected = deleteStockStmt.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.AVBL_DELETE_ERROR, e);

		}
	}

	/**
	 * Deletes nutrition data associated with a product from the database.
	 * 
	 * @param conn The database connection to use.
	 * @param id   The ID of the product for which to delete nutrition data.
	 * @return True if the nutrition data is successfully deleted, otherwise false.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public boolean deleteProductNutr(Connection conn, int id) throws DAOException {

		String deleteNutrSql = "DELETE FROM product_nutr WHERE product_id = ?";

		try (PreparedStatement deleteNutrStmt = conn.prepareStatement(deleteNutrSql)) {

			deleteNutrStmt.setInt(1, id);
			int rowsAffected = deleteNutrStmt.executeUpdate();

			return rowsAffected > 0;

		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.NUTR_DELETE_ERROR, e);

		}
	}

	/**
	 * Deletes quantity category data associated with a product from the database.
	 * 
	 * @param conn The database connection to use.
	 * @param id   The ID of the product for which to delete quantity category data.
	 * @return True if the quantity category data is successfully deleted, otherwise
	 *         false.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public boolean deleteProductQutCate(Connection conn, int id) throws DAOException {

		String deleteQuantitiesSql = "DELETE FROM product_quantities_cate WHERE product_id = ?";

		try (PreparedStatement deleteQutCateStmt = conn.prepareStatement(deleteQuantitiesSql)) {

			deleteQutCateStmt.setInt(1, id);
			int rowsAffected = deleteQutCateStmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.QUANT_CAT_DELETE_ERROR, e);

		}
	}

	/**
	 * Retrieves a list of all products from the database.
	 *
	 * @return A list containing all products retrieved from the database.
	 * @throws DAOException If there's an error during the database operation.
	 */
	public List<Product> getAllProducts() throws DAOException {

		List<Product> productList = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT p.id, p.eng_name, p.tam_name, p.image_url, p.description, p.created_at, p.updated_at, "
					+ "c.name AS category, s.name AS status, pa.weight, u.unit AS stock_unit_name, pa.created_at AS avbl_created_at, pa.updated_at AS avbl_updated_at, "
					+ "pn.protein, up.unit AS protein_unit_name, pn.carbohydrates, uc.unit AS carbo_unit_name, "
					+ "pn.calories, ucal.unit AS cal_unit_name, pn.created_at AS nutr_created_at, pn.updated_at AS nutr_updated_at "
					+ "FROM product p " + "JOIN categories c ON p.category_id = c.id "
					+ "JOIN status s ON p.status_id = s.id "
					+ "LEFT JOIN product_available_stock pa ON p.id = pa.product_id "
					+ "LEFT JOIN units u ON pa.unit_id = u.id " + "LEFT JOIN product_nutr pn ON p.id = pn.product_id "
					+ "LEFT JOIN units up ON pn.protein_unit_id = up.id "
					+ "LEFT JOIN units uc ON pn.carbo_unit_id = uc.id "
					+ "LEFT JOIN units ucal ON pn.cal_unit_id = ucal.id";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						Product product = createProductFromResultSet(rs);
						productList.add(product);
					}

				}

			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.READ_ALL_ERROR, e);
		}

		logger.info("All products read successfully.");

		return productList;
	}

	/**
	 * Creates a Product object from the data retrieved in a ResultSet.
	 *
	 * @param rs The ResultSet containing the product data.
	 * @return A Product object with details populated from the ResultSet.
	 * @throws SQLException        If there's an error while reading data from the
	 *                             ResultSet.
	 * @throws ConnectionException If there's an error establishing a database
	 *                             connection.
	 */

	private Product createProductFromResultSet(ResultSet rs) throws SQLException, ConnectionException {

		Product product = new Product();

		// Set product details from ResultSet
		product.setId(rs.getInt("id"));
		product.setName(new ProductName(rs.getString("eng_name"), rs.getString("tam_name")));
		product.setImageUrl(rs.getString("image_Url"));

		String categoryString = rs.getString("category");
		if (categoryString != null) {
			ProductCategory category = ProductCategory.valueOf(categoryString.toUpperCase());
			product.setCategory(category);
		}
		product.setDescription(rs.getString("description"));
		String statuString = rs.getString("status");
		if (statuString != null) {
			ProductStatus status = ProductStatus.valueOf(statuString.toUpperCase());
			product.setStatus(status);
		}
		product.setCreatedDateTime(rs.getTimestamp("created_at").toLocalDateTime());
		product.setUpdateDateTime(rs.getTimestamp("updated_at").toLocalDateTime());

		// Fetch and set product available stock
		ProductAvailableStock availableStock = createAvailableStockFromResultSet(rs);
		product.setAvailableStock(availableStock);

		// Fetch and set product nutritions
		ProductNutrition nutritions = createNutritionsFromResultSet(rs);
		product.setNutrition(nutritions);

		// Fetch and set product quantities
		SortedSet<ProductQuantitiesCategory> quantities = createQuantitiesFromResultSet(rs.getInt("id"));
		product.setQuantities(quantities);

		return product;
	}

	/**
	 * Creates a ProductAvailableStock object from the data retrieved in a
	 * ResultSet.
	 *
	 * @param rs The ResultSet containing the available stock data.
	 * @return A ProductAvailableStock object with details populated from the
	 *         ResultSet.
	 * @throws SQLException If there's an error while reading data from the
	 *                      ResultSet.
	 */
	private ProductAvailableStock createAvailableStockFromResultSet(ResultSet rs) throws SQLException {

		ProductAvailableStock stock = new ProductAvailableStock();

		stock.setNum(rs.getDouble("weight"));
		String stockUnit = rs.getString("stock_unit_name");
		if (stockUnit != null) {
			ProductStockUnits stockEnum = ProductStockUnits.valueOf(stockUnit.toUpperCase());
			stock.setUnit(stockEnum);
		}
		stock.setCreatedDateTime(rs.getTimestamp("avbl_created_at").toLocalDateTime());
		stock.setUpdatedTimeDate(rs.getTimestamp("avbl_updated_at").toLocalDateTime());

		return stock;
	}

	/**
	 * Creates a ProductNutrition object from the data retrieved in a ResultSet.
	 *
	 * @param rs The ResultSet containing the nutrition data.
	 * @return A ProductNutrition object with details populated from the ResultSet.
	 * @throws SQLException If there's an error while reading data from the
	 *                      ResultSet.
	 */
	private ProductNutrition createNutritionsFromResultSet(ResultSet rs) throws SQLException {

		ProductNutrition nutr = new ProductNutrition();

		nutr.setProteinNum(rs.getDouble("protein"));
		nutr.setCarbonNumb(rs.getDouble("carbohydrates"));
		nutr.setKcalNum(rs.getDouble("calories"));
		nutr.setCreatedDateTime(rs.getTimestamp("nutr_created_at").toLocalDateTime());
		nutr.setUdpatedDateTime(rs.getTimestamp("nutr_updated_at").toLocalDateTime());

		return nutr;
	}

	/**
	 * Creates a SortedSet of ProductQuantitiesCategory objects from the data
	 * retrieved in a ResultSet for a specific product ID.
	 *
	 * @param id The ID of the product for which to create quantities.
	 * @return A SortedSet of ProductQuantitiesCategory objects with details
	 *         populated from the ResultSet.
	 * @throws SQLException        If there's an error while reading data from the
	 *                             ResultSet.
	 * @throws ConnectionException If there's an error establishing a database
	 *                             connection.
	 */
	private SortedSet<ProductQuantitiesCategory> createQuantitiesFromResultSet(int id)
			throws SQLException, ConnectionException {

		SortedSet<ProductQuantitiesCategory> set = new TreeSet<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT pq.weight AS qty_weight, uq.unit AS qty_unit_name, pq.rupees, pq.created_at AS pq_created_at, pq.updated_at AS pq_updated_at "
					+ "FROM product_quantities_cate pq " + "LEFT JOIN units uq ON pq.unit_id = uq.id "
					+ "WHERE pq.product_id = ?"; // Replace "?" with the desired product ID

			try (PreparedStatement pst = conn.prepareStatement(sql)) {

				pst.setInt(1, id);

				try (ResultSet rs = pst.executeQuery()) {

					while (rs.next()) {

						double weight = rs.getDouble("qty_weight");
						String unit = rs.getString("qty_unit_name");
						double rupees = rs.getDouble("rupees");

						// Create a new ProductQuantities object with the extracted data
						ProductQuantitiesCategory quantity = new ProductQuantitiesCategory();
						quantity.setWeight(weight);

						if (unit != null) {
							ProductStockUnits units = ProductStockUnits.valueOf(unit.toUpperCase());
							quantity.setUnit(units);
						}

						quantity.setRs(rupees);
						quantity.setCreatedDateTime(rs.getTimestamp("pq_created_at").toLocalDateTime());
						quantity.setUpdatedDateTime(rs.getTimestamp("pq_updated_at").toLocalDateTime());

						// Add the ProductQuantities object to the TreeSet
						set.add(quantity);

					}

				}

			}
		}

		return set;
	}

	/**
	 * Retrieves a Product object by its English name.
	 *
	 * @param name The English name of the product to be retrieved.
	 * @return The Product object representing the product with the specified name,
	 *         or null if not found.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public Product readProductByName(String name) throws DAOException {

		Product product = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT p.id, p.eng_name, p.tam_name, p.image_url, p.description, p.created_at, p.updated_at, "
					+ "c.name AS category, s.name AS status, pa.weight, u.unit AS stock_unit_name, pa.created_at AS avbl_created_at, pa.updated_at AS avbl_updated_at, "
					+ "pn.protein, up.unit AS protein_unit_name, pn.carbohydrates, uc.unit AS carbo_unit_name, "
					+ "pn.calories, ucal.unit AS cal_unit_name, pn.created_at AS nutr_created_at, pn.updated_at AS nutr_updated_at "
					+ "FROM product p " + "JOIN categories c ON p.category_id = c.id "
					+ "JOIN status s ON p.status_id = s.id "
					+ "LEFT JOIN product_available_stock pa ON p.id = pa.product_id "
					+ "LEFT JOIN units u ON pa.unit_id = u.id " + "LEFT JOIN product_nutr pn ON p.id = pn.product_id "
					+ "LEFT JOIN units up ON pn.protein_unit_id = up.id "
					+ "LEFT JOIN units uc ON pn.carbo_unit_id = uc.id "
					+ "LEFT JOIN units ucal ON pn.cal_unit_id = ucal.id WHERE p.eng_name = ? OR p.tam_name = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, name);
				stmt.setString(2, name);

				try (ResultSet rs = stmt.executeQuery()) {

					if (rs.next()) {

						product = createProductFromResultSet(rs);

					}

				}

			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.PRODUCT_NOT_FOUND_NAME + " " + name + ".", e);
		}

		logger.info("Successfully product read by name.");

		return product;
	}

	/**
	 * Retrieves a product from the database based on the given product ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return A Product object containing the details of the retrieved product, or
	 *         null if not found.
	 * @throws DAOException If there's an issue with the database access or query
	 *                      execution.
	 */

	public Product readProductById(int id) throws DAOException {

		Product product = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT p.id, p.eng_name, p.tam_name, p.image_url, p.description, p.created_at, p.updated_at, "
					+ "c.name AS category, s.name AS status, pa.weight, u.unit AS stock_unit_name, pa.created_at AS avbl_created_at, pa.updated_at AS avbl_updated_at, "
					+ "pn.protein, up.unit AS protein_unit_name, pn.carbohydrates, uc.unit AS carbo_unit_name, "
					+ "pn.calories, ucal.unit AS cal_unit_name, pn.created_at AS nutr_created_at, pn.updated_at AS nutr_updated_at "
					+ "FROM product p " + "JOIN categories c ON p.category_id = c.id "
					+ "JOIN status s ON p.status_id = s.id "
					+ "LEFT JOIN product_available_stock pa ON p.id = pa.product_id "
					+ "LEFT JOIN units u ON pa.unit_id = u.id " + "LEFT JOIN product_nutr pn ON p.id = pn.product_id "
					+ "LEFT JOIN units up ON pn.protein_unit_id = up.id "
					+ "LEFT JOIN units uc ON pn.carbo_unit_id = uc.id "
					+ "LEFT JOIN units ucal ON pn.cal_unit_id = ucal.id WHERE p.id = ?";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setInt(1, id);

				try (ResultSet rs = stmt.executeQuery()) {

					if (rs.next()) {

						product = createProductFromResultSet(rs);

					}

				}

			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.PRODUCT_NOT_FOUND_ID + " " + id + ".", e);
		}

		logger.info("Successfully product read by id.");

		return product;
	}

	/**
	 * Updates the details of a product based on its product ID.
	 *
	 * @param conn          The database connection to use.
	 * @param productId     The ID of the product to be updated.
	 * @param updateProduct The updated Product object containing new details.
	 * @return True if the update is successful, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean updateProductById(int productId, Product updateProduct) throws DAOException {

		// Get the category ID based on the updated category name
		int categoryId = getCategoryIDByName(updateProduct.getCategory().toString().toLowerCase());

		int affectedRows = 0;

		try (Connection conn = ConnectionUtil.getConnection()) {
			// Start a transaction
			conn.setAutoCommit(false);

			// Update the product basic details
			String updateProductSql = "UPDATE product "
					+ "SET eng_name = ?, tam_name = ?, image_url = ?, category_id = ?, "
					+ "description = ? WHERE id = ?";

			try (PreparedStatement stmt = conn.prepareStatement(updateProductSql)) {
				stmt.setString(1, updateProduct.getName().getEnglishName().trim());
				stmt.setString(2, updateProduct.getName().getTamilName().trim());
				stmt.setString(3, updateProduct.getImageUrl().trim());
				stmt.setInt(4, categoryId);
				stmt.setString(5, updateProduct.getDescription().trim());
				stmt.setInt(6, productId);

				affectedRows = stmt.executeUpdate();

				if (!updateAvailStock(conn, updateProduct.getAvailableStock(), productId)
						|| !updateNutr(conn, updateProduct.getNutrition(), productId)
						|| !deleteProductQutCate(conn, productId)
						|| !addQuantities(conn, updateProduct.getQuantities(), productId)) {

					conn.rollback();
					return false;
				}

				// Commit the transaction after all updates are successful
				conn.commit();
				logger.info("Product and related information updated successfully");

			} catch (SQLException e) {
				conn.rollback();
				ExceptionLoggerUtil.logException(e);
				throw new DAOException(ProductDAOErrors.DELETE_ERROR, e);
			} finally {
				conn.setAutoCommit(true); // Reset auto-commit mode
			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.PRODUCT_UPDATE_ERROR, e);
		}

		return (affectedRows == 1);
	}

	/**
	 * Updates the available stock of a product.
	 *
	 * @param conn      The database connection to use.
	 * @param stock     The ProductAvailableStock object containing the updated
	 *                  stock details.
	 * @param productId The ID of the product for which the stock is being updated.
	 * @return True if the stock update is successful, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean updateAvailStock(Connection conn, ProductAvailableStock stock, int productId) throws DAOException {

		int stockUnitId = getUnitIdByName(stock.getUnit().toString().toLowerCase());
		// Update the product available stock
		String updateAvailableStockSql = "UPDATE product_available_stock "
				+ "SET weight = ?, unit_id = ? WHERE product_id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(updateAvailableStockSql)) {
			stmt.setDouble(1, stock.getNum());
			stmt.setInt(2, stockUnitId);
			stmt.setInt(3, productId);
			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.PRODUCT_UPDATE_AVAILABLE_STOCK_ERROR, e);

		}
	}

	/**
	 * Updates the nutrition information of a product.
	 *
	 * @param conn      The database connection to use.
	 * @param nutr      The ProductNutrition object containing the updated nutrition
	 *                  details.
	 * @param productId The ID of the product for which the nutrition information is
	 *                  being updated.
	 * @return True if the nutrition update is successful, false otherwise.
	 * @throws DAOException If an error occurs during the database operation.
	 */
	public boolean updateNutr(Connection conn, ProductNutrition nutr, int productId) throws DAOException {

		int procarboUnitId = getUnitIdByName(ProductConstants.PROTEIN_CARBOHYDRATES_UNIT);
		int caloriesUnitId = getUnitIdByName(ProductConstants.CALORIES_UNIT);
		// Update the product nutrition's
		String updateNutrSql = "UPDATE product_nutr " + "SET protein = ?, protein_unit_id = ?, "
				+ "carbohydrates = ?, carbo_unit_id = ?, " + "calories = ?, cal_unit_id = ? WHERE product_id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(updateNutrSql)) {
			stmt.setDouble(1, nutr.getProteinNum());
			stmt.setInt(2, procarboUnitId);
			stmt.setDouble(3, nutr.getCarbonNumb());
			stmt.setInt(4, procarboUnitId);
			stmt.setDouble(5, nutr.getKcalNum());
			stmt.setInt(6, caloriesUnitId);
			stmt.setInt(7, productId);

			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException(ProductDAOErrors.PRODUCT_UPDATE_NUTR_ERROR, e);
		}
	}

	/**
	 * Checks if a product with the given English or Tamil name exists in the
	 * database.
	 *
	 * @param name The name of the product to check.
	 * @return true if a product with the given name exists, false otherwise.
	 * @throws DAOException If there is an issue with the database operation.
	 */

	public boolean isProductWithNameExists(String name) throws DAOException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT COUNT(*) FROM product WHERE eng_name = ? OR tam_name = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, name);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (ConnectionException | SQLException e) {

			throw new DAOException(ProductDAOErrors.IS_PRODUCT_EXISTS_WITH_NAME, e);
		}

		return false;
	}

	/**
	 * Checks if a product with the given ID exists in the database.
	 *
	 * @param productId The ID of the product to check.
	 * @return true if a product with the given ID exists, false otherwise.
	 * @throws DAOException If there is an issue with the database operation.
	 */
	public boolean isProductWithIdExists(int productId) throws DAOException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT COUNT(*) FROM product WHERE id = ?";
			try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
				preparedStatement.setInt(1, productId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(ProductDAOErrors.IS_PRODUCT_EXISTS_WITH_ID, e);
		}

		return false;
	}

	/**
	 * Updates the status of a product with the given status name and product ID.
	 *
	 * @param status    The new status to set for the product.
	 * @param productId The ID of the product to update.
	 * @return True if the update was successful, false otherwise.
	 * @throws DAOException If there's an error updating the product status.
	 */

	public boolean updateProductStatus(String status, int productId) throws DAOException {

		int statusId = getStatusIDByName(status);

		try (Connection conn = ConnectionUtil.getConnection()) {

			String updateQuery = "UPDATE product SET status_id = ? WHERE id = ?";

			try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
				preparedStatement.setInt(1, statusId);
				preparedStatement.setInt(2, productId);

				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected > 0;
			}
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(ProductDAOErrors.UPDATE_STATUS_ERROR, e);
		}

	}
}
