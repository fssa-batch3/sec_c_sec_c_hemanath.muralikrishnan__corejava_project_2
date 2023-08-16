package com.fssa.agrokart.error;

/**
 *  An interface which holds error messages for product DAO operations
 * @author HemanathMuralikrishnan
 */

public final class ProductDAOErrors {

//	while inserting the product into the databases
public static final String INSERT_ERROR = "Failed to insert the product into the database.";

//	while searching for the category in the database
public static final String CATEGORY_ERROR = "Failed to find the category id.";

//	while searching for the status in the database
public static final String STATUS_ERROR = "Failed to find the status id.";

//	while searching for the unit id in the database
public static final String UNIT_ID_ERROR = "Failed to find the unit id.";

//	while inserting the product available stock into the database
public static final String AVAILABLE_STOCK_ERROR = "Failed to insert the product available stock into the database.";

//	while insert the nutr into the database
public static final String NUTR_ERROR = "Failed to insert the product nutrition's into the database.";

//	while insert the quantites into the database
public static final String QTY_ERROR = "Failed to insert the product quantities into the database.";

//	while insert the quantites as batch into the database
public static final String QTY_BATCH_ERROR = "Failed to insert the batch quantities into the database.";

//	while deleting some errors 
public static final String DELET_ERROR = "Error while deleting product with ID.";

//	the requested id doesn't found in the database
public static final String PRODUCT_NOT_FOUND_ID = "Product not found with ID";

//	while reading all products from the product table
public static final String READ_PRODUCT_ERROR = "Error while fetching all products from the database.";

//	while searching for the id products is 
public static final String PRODUCT_NOT_FOUND_NAME = "Product not found with this name";

//	while updating the product details
public static final String PRODUCT_UPDATE_ERROR = "Failed to update the product details.";

private ProductDAOErrors(){

}

}
