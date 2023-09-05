package com.fssa.agrokart.dao;

/**
 * The {@code ProductDAOErrors} class is a utility class that defines constant
 * error messages related to various operations in the product DAO (Data Access
 * Object) layer. These error messages provide meaningful descriptions for
 * different error scenarios that may occur during database operations related
 * to product management.
 *
 * <p>
 * This class is not meant to be instantiated, as it only holds static final
 * error message strings. The error messages can be accessed using the public
 * static final fields provided by this class.
 * </p>
 */

public final class ProductDAOErrors {

	// Error messages related to product insertion
	public static final String INSERT_ERROR = "Failed to insert the product into the database.";

	// Error messages related to category retrieval
	public static final String CATEGORY_ERROR = "Failed to find the category id.";

	// Error messages related to status retrieval
	public static final String STATUS_ERROR = "Failed to find the status id.";

	// Error messages related to unit ID retrieval
	public static final String UNIT_ID_ERROR = "Failed to find the unit id.";

	// Error messages related to available stock insertion
	public static final String AVAILABLE_STOCK_ERROR = "Failed to insert the product available stock into the database.";

	// Error message related to read all products
	public static final String READ_ALL_ERROR = "Failed to read all products from the database.";

	// Error messages related to nutrition insertion
	public static final String NUTR_ERROR = "Failed to insert the product nutrition's into the database.";

	// Error messages related to quantities insertion
	public static final String QTY_ERROR = "Failed to insert the product quantities into the database.";

	// Error messages related to product deletion
	public static final String DELETE_ERROR = "Failed to delete the product with ID.";

	public static final String AVBL_DELETE_ERROR = "Failed to delete the product available stock with ID.";

	public static final String NUTR_DELETE_ERROR = "Failed to delete the product nutrition's with ID.";

	public static final String QUANT_CAT_DELETE_ERROR = "Failed to delete the product quantities category with ID.";

	// Error message for product not found by ID
	public static final String PRODUCT_NOT_FOUND_ID = "Product not found with ID";

	// Error message for product not found by name
	public static final String PRODUCT_NOT_FOUND_NAME = "Product not found with this name";

	// Error message for updating product details
	public static final String PRODUCT_UPDATE_ERROR = "Failed to update the product details.";

	// Error message for updating product available stock
	public static final String PRODUCT_UPDATE_AVAILABLE_STOCK_ERROR = "Failed to update the product available stock.";

	// Error message for updating product nutrition
	public static final String PRODUCT_UPDATE_NUTR_ERROR = "Failed to update the product nutrition's.";

	// Error message for updating product quantities by category
	public static final String PRODUCT_UPDATE_QUANTITIES_CATE_ERROR = "Failed to update the product quantities categories.";

//    Error message for isproductexsists with the name 
	public static final String IS_PRODUCT_EXISTS_WITH_NAME = "Failed to find the product with the given name.";

//	Error message for isproductexsists with the id
	public static final String IS_PRODUCT_EXISTS_WITH_ID = "Product with the given ID already exists in the database.";

//	Error message while updating the product status
	public static final String UPDATE_STATUS_ERROR = "Error while updating product status";

	// Private constructor to prevent instantiation
	private ProductDAOErrors() {

	}

}
