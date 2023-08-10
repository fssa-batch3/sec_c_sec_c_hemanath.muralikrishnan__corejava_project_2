package com.fssa.agrokart.dao;

import com.fssa.agrokart.errors.ProductDAOErrors;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.util.*;
import com.fssa.agrokart.enums.ProductCategory;
import com.fssa.agrokart.enums.ProductStatus;
import com.fssa.agrokart.enums.ProductStockUnits;
import com.fssa.agrokart.exceptions.*;
import com.fssa.agrokart.constants.ProductConstants;

import java.sql.*;
import java.util.*;

/**
 *  A class which holds the Data access object
 *  It has method with sql queries the methods will do CRUD operations on the product model object
 * @author HemanathMuralikrishnan
 */
public class ProductDAO {

    Logger logger = new Logger();

    //	method to insert the product basic details
    public boolean insertProduct(Product product) throws ProductDAOException {

        int generatedProductId = -1; // Default value in case of failure

        try (Connection conn = ConnectionUtil.getConnection()) {

            // Get the category ID based on the category name
            int categoryId = getCategoryIDByName(product.getCategory().toString().toLowerCase());

            // Get the status ID based on the status name
            int statusId = getStatusIDByName(product.getStatus().toString().toLowerCase());

            String sql = "INSERT INTO product (eng_name, tam_name, image_Url, category_id, description, status_id, "
                    + "created_date, created_time, updated_date, updated_time) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, product.getName().getEnglishName().trim());
                stmt.setString(2, product.getName().getTamilName().trim());
                stmt.setString(3, product.getImageUrl().trim());
                stmt.setInt(4, categoryId); // Set the category ID obtained from getCategoryIDByName()
                stmt.setString(5, product.getDescription().trim());
                stmt.setInt(6, statusId);
                stmt.setDate(7, java.sql.Date.valueOf(product.getCreationDate()));
                stmt.setTime(8, java.sql.Time.valueOf(product.getCreationTime()));
                stmt.setDate(9, java.sql.Date.valueOf(product.getUpdatedDate()));
                stmt.setTime(10, java.sql.Time.valueOf(product.getUpdatedTime()));

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedProductId = generatedKeys.getInt(1);
                        }
                    }

                }

            }
        } catch (SQLException | ConnectionException e) {

            throw new ProductDAOException(ProductDAOErrors.INSERT_ERROR + " " + e.getMessage());
        }

//		calling the available stock method to insert the product available stock
        addAvailableStock(product.getAvailableStock(), generatedProductId);

//		calling the add nutr method to insert the nutr
        addNutr(product.getNutrition(), generatedProductId);

//		calling the add qty method to insert the quantities
        addQuantities(product.getQuantities(), generatedProductId);

        logger.info("Product inserted successfully.");

//		if the insert product completed successful then return true
        return true;

    }

    //	Method to add the available stock for the product
    public boolean addAvailableStock(ProductAvailableStock stock, int productId) throws ProductDAOException {

//		get the unit_id from the product units table
        int unitId = getUnitId(stock.getUnit().toString().toLowerCase());

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO product_available_stock(product_id, weight, unit_id) " + "VALUES(?,?,?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, productId);
                stmt.setDouble(2, stock.getNum());
                stmt.setInt(3, unitId);

                stmt.executeUpdate();
            }
        } catch (SQLException | ConnectionException e) {

            throw new ProductDAOException(ProductDAOErrors.AVAILABLE_STOCK_ERROR + " " + e.getMessage());
        }

//		if the insert available stock successful then return true
        return true;
    }

    //	Method to add the nutrition's for the product
    public boolean addNutr(ProductNutrition nutr, int productId) throws ProductDAOException {

//		get the unit_id for protein and carbo
        int procarboId = getUnitId(ProductConstants.PROTEIN_CARBOHYDRATESS_UNIT);

//		get the unit_id for kcal
        int caloriesId = getUnitId(ProductConstants.CALORIES_UNIT);

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO product_nutr(product_id, protein, protein_unit_id, "
                    + "carbohydrates, carbo_unit_id, calories, cal_unit_id) " + "VALUES(?,?,?,?,?,?,?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, productId);
                stmt.setDouble(2, nutr.getProteinNum());
                stmt.setInt(3, procarboId);
                stmt.setDouble(4, nutr.getCarbonNumb());
                stmt.setInt(5, procarboId);
                stmt.setDouble(6, nutr.getCarbonNumb());
                stmt.setInt(7, caloriesId);

                stmt.executeUpdate();

            }

        } catch (SQLException | ConnectionException e) {

            throw new ProductDAOException(ProductDAOErrors.NUTR_ERROR + " " + e.getMessage());
        }

//		if the nutr insert successfully then return true
        return true;
    }

    // Method to add the quantities for the product
    public boolean addQuantities(SortedSet<ProductQuantities> qty, int productId) throws ProductDAOException {
        String sql = "INSERT INTO product_quantities (product_id, weight, unit_id, rupees) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionUtil.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (ProductQuantities quantity : qty) {
                    int unitId = getUnitId(quantity.getUnit().toString().toLowerCase());
                    stmt.setInt(1, productId);
                    stmt.setDouble(2, quantity.getWeight());
                    stmt.setInt(3, unitId);
                    stmt.setDouble(4, quantity.getRs());
                    stmt.addBatch();
                }

                // Execute the batch insertion
                int[] affectedRows = stmt.executeBatch();
                for (int rowsAffected : affectedRows) {
                    if (rowsAffected <= 0) {
                        throw new ProductDAOException(ProductDAOErrors.QTY_BATCH_ERROR);
                    }
                }
            }
        } catch (SQLException | ConnectionException e) {
            throw new ProductDAOException(ProductDAOErrors.QTY_ERROR + " " + e.getMessage());
        }

        // if the insert is successful then return true
        return true;
    }

    // Method to get the category ID based on the category name
    public int getCategoryIDByName(String categoryName) throws ProductDAOException {

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

            throw new ProductDAOException(ProductDAOErrors.CATEGORY_ERROR + " " + e.getMessage());
        }

        return categoryId;
    }

    // Method to get the status ID based on the status name
    public int getStatusIDByName(String status) throws ProductDAOException {

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

            throw new ProductDAOException(ProductDAOErrors.STATUS_ERROR + " " + e.getMessage());
        }

        return statusId;
    }

    //	Method to get the unit id based on the unit name
    public int getUnitId(String unit) throws ProductDAOException {

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

            throw new ProductDAOException(ProductDAOErrors.UNIT_ID_ERROR + " " + e.getMessage());
        }

        return unitId;

    }

    // Method to delete a product and its associated data using JOINs
    public boolean deleteProduct(int productId) throws ProductDAOException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String deleteStockSql = "DELETE FROM product_available_stock WHERE product_id = ?";
            String deleteNutrSql = "DELETE FROM product_nutr WHERE product_id = ?";
            String deleteQuantitiesSql = "DELETE FROM product_quantities WHERE product_id = ?";
            String deleteProductSql = "DELETE FROM product WHERE id = ?";

            try (PreparedStatement deleteStockStmt = conn.prepareStatement(deleteStockSql);
                 PreparedStatement deleteNutrStmt = conn.prepareStatement(deleteNutrSql);
                 PreparedStatement deleteQuantitiesStmt = conn.prepareStatement(deleteQuantitiesSql);
                 PreparedStatement deleteProductStmt = conn.prepareStatement(deleteProductSql)) {

                // First, delete from the dependent tables
                deleteStockStmt.setInt(1, productId);
                deleteStockStmt.executeUpdate();

                deleteNutrStmt.setInt(1, productId);
                deleteNutrStmt.executeUpdate();

                deleteQuantitiesStmt.setInt(1, productId);
                deleteQuantitiesStmt.executeUpdate();

                // Finally, delete the product
                deleteProductStmt.setInt(1, productId);
                int rowsAffected = deleteProductStmt.executeUpdate();

                if (rowsAffected == 0) {
                    throw new ProductDAOException(ProductDAOErrors.PRODUCT_NOT_FOUND_ID + " " + productId + ".");
                }
            }
        } catch (SQLException | ConnectionException e) {
            throw new ProductDAOException(ProductDAOErrors.DELET_ERROR + " " + e.getMessage());
        }

        logger.info("Product deletion successful.");
        return true; // Product deletion successful
    }

    //	Method to read all the products from the product table
    public List<Product> getAllProducts() throws ProductDAOException {

        List<Product> productList = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT p.id, p.eng_name, p.tam_name, p.image_url, p.description, " +
                    "p.created_date, p.created_time, p.updated_date, p.updated_time, " +
                    "c.name AS category, s.name AS status, pa.weight, u.unit AS stock_unit_name, " +
                    "pn.protein, up.unit AS protein_unit_name, pn.carbohydrates, uc.unit AS carbo_unit_name, " +
                    "pn.calories, ucal.unit AS cal_unit_name " +
                    "FROM product p " +
                    "JOIN categories c ON p.category_id = c.id " +
                    "JOIN status s ON p.status_id = s.id " +
                    "LEFT JOIN product_available_stock pa ON p.id = pa.product_id " +
                    "LEFT JOIN units u ON pa.unit_id = u.id " +
                    "LEFT JOIN product_nutr pn ON p.id = pn.product_id " +
                    "LEFT JOIN units up ON pn.protein_unit_id = up.id " +
                    "LEFT JOIN units uc ON pn.carbo_unit_id = uc.id " +
                    "LEFT JOIN units ucal ON pn.cal_unit_id = ucal.id";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {

                        Product product = createProductFromResultSet(rs);
                        productList.add(product);
                    }

                }

            }

        } catch (SQLException | ConnectionException e) {

            throw new ProductDAOException(ProductDAOErrors.READ_PRODUCT_ERROR + " " + e.getMessage());
        }

        logger.info("All products read successfull.");

        return productList;
    }

    public Product createProductFromResultSet(ResultSet rs) throws SQLException, ConnectionException {

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
        product.setCreationDate(rs.getDate("created_date").toLocalDate());
        product.setCreationTime(rs.getTime("created_time").toLocalTime());
        product.setUpdatedDate(rs.getDate("updated_date").toLocalDate());
        product.setUpdatedTime(rs.getTime("updated_time").toLocalTime());

        // Fetch and set product available stock
        ProductAvailableStock availableStock = createAvailableStockFromResultSet(rs);
        product.setAvailableStock(availableStock);

        // Fetch and set product nutritions
        ProductNutrition nutritions = createNutritionsFromResultSet(rs);
        product.setNutrition(nutritions);

        // Fetch and set product quantities
        SortedSet<ProductQuantities> quantities = createQuantitiesFromResultSet(rs.getInt("id"));
        product.setQuantities(quantities);

        return product;
    }

    private ProductAvailableStock createAvailableStockFromResultSet(ResultSet rs) throws SQLException {

        ProductAvailableStock stock = new ProductAvailableStock();

        stock.setNum((float) rs.getDouble("weight"));
        String stockUnit = rs.getString("stock_unit_name");
        if (stockUnit != null) {
            ProductStockUnits stockEnum = ProductStockUnits.valueOf(stockUnit.toUpperCase());
            stock.setUnit(stockEnum);
        }

        return stock;
    }

    private ProductNutrition createNutritionsFromResultSet(ResultSet rs) throws SQLException {

        ProductNutrition nutr = new ProductNutrition();

        nutr.setProteinNum(rs.getDouble("protein"));
        nutr.setCarbonNumb(rs.getDouble("carbohydrates"));
        nutr.setKcalNum(rs.getDouble("calories"));

        return nutr;
    }

    private SortedSet<ProductQuantities> createQuantitiesFromResultSet(int id) throws SQLException, ConnectionException {

        SortedSet<ProductQuantities> set = new TreeSet<>();

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT pq.weight AS qty_weight, uq.unit AS qty_unit_name, pq.rupees " +
                    "FROM product_quantities pq " +
                    "LEFT JOIN units uq ON pq.unit_id = uq.id " +
                    "WHERE pq.product_id = ?"; // Replace "?" with the desired product ID

            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()) {

                    while (rs.next()) {

                        double weight = rs.getDouble("qty_weight");
                        String unit = rs.getString("qty_unit_name");
                        double rupees = rs.getDouble("rupees");

                        // Create a new ProductQuantites object with the extracted data
                        ProductQuantities quantity = new ProductQuantities();
                        quantity.setWeight((float) weight);

                        if (unit != null) {
                            ProductStockUnits units = ProductStockUnits.valueOf(unit.toUpperCase());
                            quantity.setUnit(units);
                        }

                        quantity.setRs((float) rupees);

                        // Add the ProductQuantites object to the TreeSet
                        set.add(quantity);

                    }

                }

            }
        }

        return set;
    }



    //	Read the product by name
    public Product readProductByName(String name) throws ProductDAOException {

        Product product = null;

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT p.id, p.eng_name, p.tam_name, p.image_url, p.description, " +
                    "p.created_date, p.created_time, p.updated_date, p.updated_time, " +
                    "c.name AS category, s.name AS status, pa.weight, u.unit AS stock_unit_name, " +
                    "pn.protein, up.unit AS protein_unit_name, pn.carbohydrates, uc.unit AS carbo_unit_name, " +
                    "pn.calories, ucal.unit AS cal_unit_name " +
                    "FROM product p " +
                    "JOIN categories c ON p.category_id = c.id " +
                    "JOIN status s ON p.status_id = s.id " +
                    "LEFT JOIN product_available_stock pa ON p.id = pa.product_id " +
                    "LEFT JOIN units u ON pa.unit_id = u.id " +
                    "LEFT JOIN product_nutr pn ON p.id = pn.product_id " +
                    "LEFT JOIN units up ON pn.protein_unit_id = up.id " +
                    "LEFT JOIN units uc ON pn.carbo_unit_id = uc.id " +
                    "LEFT JOIN units ucal ON pn.cal_unit_id = ucal.id "// Join with units table for qty_unit_name
                    + "WHERE p.eng_name = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, name);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {

                        product = createProductFromResultSet(rs);

                    }

                }

            }

        } catch (SQLException | ConnectionException e) {

            throw new ProductDAOException(
                    ProductDAOErrors.PRODUCT_NOT_FOUND_NAME + " " + name + "." + " " + e.getMessage());
        }

        logger.info("Successfully product read by name.");

        return product;
    }

    // Update the product details by product id
    public boolean updateProductById(int productId, Product updateProduct) throws ProductDAOException {

        try (Connection conn = ConnectionUtil.getConnection()) {
            // Start a transaction
            conn.setAutoCommit(false);

            // Get the category ID based on the updated category name
            int categoryId = getCategoryIDByName(updateProduct.getCategory().toString().toLowerCase());

            // Get the status ID based on the updated status name
            int statusId = getStatusIDByName(updateProduct.getStatus().toString().toLowerCase());

            // Update the product basic details
            String updateProductSql = "UPDATE product "
                    + "SET eng_name = ?, tam_name = ?, image_url = ?, category_id = ?, "
                    + "description = ?, status_id = ?, created_date = ?, created_time = ?, "
                    + "updated_date = ?, updated_time = ? WHERE id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateProductSql)) {
                stmt.setString(1, updateProduct.getName().getEnglishName().trim());
                stmt.setString(2, updateProduct.getName().getTamilName().trim());
                stmt.setString(3, updateProduct.getImageUrl().trim());
                stmt.setInt(4, categoryId);
                stmt.setString(5, updateProduct.getDescription().trim());
                stmt.setInt(6, statusId);
                stmt.setDate(7, java.sql.Date.valueOf(updateProduct.getCreationDate()));
                stmt.setTime(8, java.sql.Time.valueOf(updateProduct.getCreationTime()));
                stmt.setDate(9, java.sql.Date.valueOf(updateProduct.getUpdatedDate()));
                stmt.setTime(10, java.sql.Time.valueOf(updateProduct.getUpdatedTime()));
                stmt.setInt(11, productId);

                int affectedRows = stmt.executeUpdate();

                if (affectedRows == 0) {
                    // Product not found with the given name, rollback the transaction
                    conn.rollback();
                    throw new ProductDAOException(ProductDAOErrors.PRODUCT_NOT_FOUND_ID + " " + productId + ".");
                }
            }

            // Update the product available stock
            String updateAvailableStockSql = "UPDATE product_available_stock " + "SET weight = ?, unit_id = ? WHERE product_id = ?";


            try (PreparedStatement stmt = conn.prepareStatement(updateAvailableStockSql)) {
                stmt.setDouble(1, updateProduct.getAvailableStock().getNum());
                int stockUnitId = getUnitId(updateProduct.getAvailableStock().getUnit().toString().toLowerCase());
                stmt.setInt(2, stockUnitId);
                stmt.setInt(3, productId);
                stmt.executeUpdate();
            }

            // Update the product nutrition's
            String updateNutrSql = "UPDATE product_nutr " + "SET protein = ?, protein_unit_id = ?, "
                    + "carbohydrates = ?, carbo_unit_id = ?, " + "calories = ?, cal_unit_id = ? WHERE product_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(updateNutrSql)) {
                stmt.setDouble(1, updateProduct.getNutrition().getProteinNum());
                int procarboUnitId = getUnitId(ProductConstants.PROTEIN_CARBOHYDRATESS_UNIT);
                stmt.setInt(2, procarboUnitId);
                stmt.setDouble(3, updateProduct.getNutrition().getCarbonNumb());
                stmt.setInt(4, procarboUnitId);
                stmt.setDouble(5, updateProduct.getNutrition().getKcalNum());
                int caloriesUnitId = getUnitId(ProductConstants.CALORIES_UNIT);
                stmt.setInt(6, caloriesUnitId);
                stmt.setInt(7, productId);

                stmt.executeUpdate();
            }

            // Update the product quantities
            String updateQuantitiesSql = "UPDATE product_quantities " + "SET weight = ?, unit_id = ?, rupees = ? WHERE product_id = ?";


            try (PreparedStatement stmt = conn.prepareStatement(updateQuantitiesSql)) {
                Set<ProductQuantities> quantities = updateProduct.getQuantities();
                for (ProductQuantities quantity : quantities) {
                    stmt.setDouble(1, quantity.getWeight());
                    int qtyUnitId = getUnitId(quantity.getUnit().toString().toLowerCase());
                    stmt.setInt(2, qtyUnitId);
                    stmt.setDouble(3, quantity.getRs());
                    stmt.setInt(4, productId);

                    stmt.addBatch();
                }

                // Execute the batch update
                stmt.executeBatch();
            }

            // Commit the transaction
            conn.commit();

            logger.info("Product updated successfully");

            return true;
        } catch (SQLException | ConnectionException e) {
            // Rollback the transaction on error
            throw new ProductDAOException(ProductDAOErrors.PRODUCT_UPDATE_ERROR + " " + e.getMessage());
        }
    }

}
