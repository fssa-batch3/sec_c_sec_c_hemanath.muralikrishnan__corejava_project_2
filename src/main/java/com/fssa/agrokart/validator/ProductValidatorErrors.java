/**
 *
 */
package com.fssa.agrokart.validator;

/**
 * An interface which holds the error message constants for product validator.
 * This class defines a set of constant error messages that are used for validation checks
 * in the product validation process. These error messages are intended to provide informative
 * feedback for various validation failures and input errors.
 * <p>
 * Each constant represents a specific type of validation error and provides a descriptive message
 * that can be used to identify and handle the error case.
 * <p>
 * This class is designed as a final utility class and cannot be instantiated.
 *
 * @author HemanathMuralikrishnan
 */
public final class ProductValidatorErrors {

    /**
     * The constant INVALID_PRODUCT_OBJ.
     * Error message indicating that the provided product object is null.
     */
    public static final String INVALID_PRODUCT_OBJ = "Product cannot be null";

    /**
     * The constant INVALID_NAME_OBJ.
     * Error message indicating that the product name is null.
     */
    public static final String INVALID_NAME_OBJ = "Product name cannot be null.";

    /**
     * The constant INVALID_ENGLISH_NAME_NULL.
     * Error message indicating that the English name of the product is null or empty.
     */
    public static final String INVALID_ENGLISH_NAME_NULL = "Product english name cannot be null or empty string.";

    /**
     * The constant INVALID_TAMIL_NAME_NULL.
     * Error message indicating that the Tamil name of the product is null or empty.
     */
    public static final String INVALID_TAMIL_NAME_NULL = "Product tamil name cannot be null or empty string.";

    /**
     * The constant INVALID_ENGLISH_NAME_PATTERN.
     * Error message indicating that the English name of the product does not match the required pattern.
     */
    public static final String INVALID_ENGLISH_NAME_PATTERN = "Product English name must contain alphabets only and have a length between 2 and 50 characters. It should not have consecutive spaces exceeding one. Please ensure that the name follows the pattern: '^(?!.*\\s{2,})[A-Za-z ]{2,50}$'.";

    /**
     * The constant INVALID_TAMIL_NAME_PATTERN.
     * Error message indicating that the Tamil name of the product does not match the required pattern.
     */
    public static final String INVALID_TAMIL_NAME_PATTERN = "Product Tamil name is invalid. It should consist of Tamil characters, dots, and spaces. Consecutive spaces are not allowed. The name should not start with a space. Please ensure that the name follows the pattern: '^(?!\\s)(?!.*\\s{2})[\\p{IsTamil}. ]+'.";

    /**
     * The constant INVALID_IMAGE_URL_NULL.
     * Error message indicating that the image URL of the product is null or empty.
     */
    public static final String INVALID_IMAGE_URL_NULL = "Product image url cannot be null or empty string.";

    /**
     * The constant INVALID_IMAGE_URL_PATTERN.
     * Error message indicating that the image URL of the product does not match the required pattern.
     */
    public static final String INVALID_IMAGE_URL_PATTERN = "Invalid image URL. The URL should start with 'http', 'https', or 'ftp', followed by the domain name, and end with a valid image file extension (jpg, jpeg, gif, png, bmp). Please ensure that the URL matches the pattern: '(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b'.";

    /**
     * The constant INVALID_CATEGORY.
     * Error message indicating that the product category is invalid.
     */

    public static final String INVALID_CATEGORY = "Invalid product category";

    /**
     * The constant INVALID_DESCRIPTION_NULL.
     * Error message indicating that the product description is null or empty.
     */
    public static final String INVALID_DESCRIPTION_NULL = "Product description cannot be null or empty string";

    /**
     * The constant INVALID_DESCRIPTION_PATTERN.
     * Error message indicating that the product description does not match the required pattern.
     */
    public static final String INVALID_DESCRIPTION_PATTERN = "Description should be at least 10 characters long and can only contain letters, numbers, punctuation, and basic symbols.";

    /**
     * The constant INVALID_NUTR_OBJ.
     * Error message indicating that the product nutrition information is null.
     */
    public static final String INVALID_NUTR_OBJ = "Product nutritions cannot be null.";

    /**
     * The constant INVALID_PROTEIN_NUTR.
     * Error message indicating that the protein content of the product is invalid.
     */
    public static final String INVALID_PROTEIN_NUTR = "Protein must be greater than or equal to zero and lesser than or equal to 12.5gm . Per 250gm of the product.";

    /**
     * The constant INVALID_CARBOHYDRATES_NUTR.
     * Error message indicating that the carbohydrates content of the product is invalid.
     */
    public static final String INVALID_CARBOHYDRATES_NUTR = "Carbohydrates must be greater than or equal to zero and lesser than or equal to 50gm . Per 250gm of the product.";

    /**
     * The constant INVALID_CALORIES_NUTR.
     * Error message indicating that the calories content of the product is invalid.
     */
    public static final String INVALID_CALORIES_NUTR = "Calories must be greater than or equal to zero or lesser than or equal to 200. Per 250gm of the product.";

    /**
     * The constant INVALID_AVAILABLE_STOCK_OBJ.
     * Error message indicating that the available stock object is null.
     */
    public static final String INVALID_AVAILABLE_STOCK_OBJ = "Available stock cannot be null.";


    /**
     * The constant INVALID_AVAILABLE_STOCK_UNIT.
     * Error message indicating that the unit of available stock is null.
     */
    public static final String INVALID_AVAILABLE_STOCK_UNIT = "Available stock unit cannot be null.";

    /**
     * The constant INVALID_AVAILABLE_STOCK_NUM.
     * Error message indicating that the quantity of available stock is invalid.
     */
    public static final String INVALID_AVAILABLE_STOCK_NUM = "Available stock quantity must be greater than 20.";


    /**
     * The constant INVALID_AVAILABLE_STOCK_PKT_NOS.
     * Error message indicating that the quantity of available stock for NOS and PKT must be a whole number.
     */
    public static final String INVALID_AVAILABLE_STOCK_PKT_NOS = "For available stock NOS and PKT must be whole number. Eg: 25,26";

    /**
     * The constant INVALID_STATUS.
     * Error message indicating that the product status is invalid.
     */
    public static final String INVALID_STATUS = "Active status cannot be null.";


    /**
     * The constant INVALID_QUANTITY_CATE_OBJ.
     * Error message indicating that the quantity category object is null.
     */
    public static final String INVALID_QUANTITY_CATE_OBJ = "Quantities category cannot be null.";

    /**
     * The constant INVALID_QUANTITY_CATE_UNIT.
     * Error message indicating that the unit of the quantity category is null.
     */
    public static final String INVALID_QUANTITY_CATE_UNIT = "Quantities category unit cannot be null.";


    /**
     * The constant INVALID_QUANTITY_CATE_AMOUNT.
     * Error message indicating that the amount of the quantity category is invalid.
     */
    public static final String INVALID_QUANTITY_CATE_AMOUNT = "Quantities category amount must be greater than or equal to 10.";


    /**
     * The constant INVALID_QUANTITY_WEIGHT.
     * Error message indicating that the weight of the quantity category is invalid.
     */
    public static final String INVALID_QUANTITY_WEIGHT = "Product quantity category weight must be lesser than available stock and have minimum weight.";


    /**
     * The constant INVALID_QUANTITY_WEIGHT_PKT_NOS.
     * Error message indicating that the weight of the quantity category for NOS and PKT must be a whole number.
     */
    public static final String INVALID_QUANTITY_WEIGTH_PKT_NOS = "Product quantity category must be a whole number. Because you selected the available stock as PKT or NOS.";

    /**
     * The constant INVALID_QUANTITY_FOR_KG.
     * Error message indicating that the quantity category unit is incompatible with the available stock unit.
     **/
    public static final String INVALID_QUANTITY_FOR_KG = "Selected available stock quantity is KG. Only you can add KG or GM for quantities cate.";

    /**
     * The constant INVALID_QUANTITY_FOR_PKT.
     * Error message indicating that the quantity category unit is incompatible with the available stock unit.
     */
    public static final String INVALID_QUANTITY_FOR_PKT = "Selected available stock is PKT. Only you can add PKT for quantities cate.";

    /**
     * The constant INVALID_QUANTITY_FOR_NOS.
     * Error message indicating that the quantity category unit is incompatible with the available stock unit.
     */
    public static final String INVALID_QUANTITY_FOR_NOS = "Selected available stock is NOS. Only you can add NOS for quantities cate.";


    /**
     * Instantiates a new Product validator errors.
     * Private constructor to prevent instantiation of the class.
     */
    private ProductValidatorErrors() {
        // This constructor is intentionally empty.

    }

}
