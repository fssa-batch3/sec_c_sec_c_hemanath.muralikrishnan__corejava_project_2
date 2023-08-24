/**
 *
 */
package com.fssa.agrokart.error;

/**
 * An interface which holds the error message for product validator
 *
 * @author HemanathMuralikrishnan
 */
public final class ProductValidatorErrors {

    /**
     * The constant INVALID_PRODUCT_OBJ.
     */
    public static final String INVALID_PRODUCT_OBJ = "Product cannot be null";

    /**
     * The constant INVALID_NAME_OBJ.
     */
    public static final String INVALID_NAME_OBJ = "Product name cannot be null.";

    /**
     * The constant INVALID_ENGLISH_NAME_NULL.
     */
    public static final String INVALID_ENGLISH_NAME_NULL = "Product english name cannot be null or empty string.";

    /**
     * The constant INVALID_TAMIL_NAME_NULL.
     */
    public static final String INVALID_TAMIL_NAME_NULL = "Product tamil name cannot be null or empty string.";

    /**
     * The constant INVALID_ENGLISH_NAME_PATTERN.
     */
    public static final String INVALID_ENGLISH_NAME_PATTERN = "Product English name must contain alphabets only and have a length between 2 and 50 characters. It should not have consecutive spaces exceeding one. Please ensure that the name follows the pattern: '^(?!.*\\s{2,})[A-Za-z ]{2,50}$'.";

    /**
     * The constant INVALID_TAMIL_NAME_PATTERN.
     */
    public static final String INVALID_TAMIL_NAME_PATTERN = "Product Tamil name is invalid. It should consist of Tamil characters, dots, and spaces. Consecutive spaces are not allowed. The name should not start with a space. Please ensure that the name follows the pattern: '^(?!\\s)(?!.*\\s{2})[\\p{IsTamil}. ]+'.";

    /**
     * The constant INVALID_IMAGE_URL_NULL.
     */
    public static final String INVALID_IMAGE_URL_NULL = "Product image url cannot be null or empty string.";

    /**
     * The constant INVALID_IMAGE_URL_PATTERN.
     */
    public static final String INVALID_IMAGE_URL_PATTERN = "Invalid image URL. The URL should start with 'http', 'https', or 'ftp', followed by the domain name, and end with a valid image file extension (jpg, jpeg, gif, png, bmp). Please ensure that the URL matches the pattern: '(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b'.";

    /**
     * The constant INVALID_CATEGORY.
     */
    public static final String INVALID_CATEGORY = "Invalid product category";

    /**
     * The constant INVALID_DESCRIPTION_NULL.
     */
    public static final String INVALID_DESCRIPTION_NULL = "Product description cannot be null or empty string";

    /**
     * The constant INVALID_DESCRIPTION_PATTERN.
     */
    public static final String INVALID_DESCRIPTION_PATTERN = "Description should be at least 10 characters long and can only contain letters, numbers, punctuation, and basic symbols.";

    /**
     * The constant INVALID_NUTR_OBJ.
     */
    public static final String INVALID_NUTR_OBJ = "Product nutritions cannot be null.";

    /**
     * The constant INVALID_PROTEIN_NUTR.
     */
    public static final String INVALID_PROTEIN_NUTR = "Protein must be greater than or equal to zero. Per 100gm of the product.";

    /**
     * The constant INVALID_CARBOHYDRATES_NUTR.
     */
    public static final String INVALID_CARBOHYDRATES_NUTR = "Carbohydrates must be greater than or equal to zero. Per 100gm of the product.";

    /**
     * The constant INVALID_CALORIES_NUTR.
     */
    public static final String INVALID_CALORIES_NUTR = "Calories must be greater than or equal to zero. Per 100gm of the product.";

    /**
     * The constant INVALID_AVAILABLE_STOCK_OBJ.
     */
    public static final String INVALID_AVAILABLE_STOCK_OBJ = "Available stock cannot be null.";


    /**
     * The constant INVALID_AVAILABLE_STOCK_UNIT.
     */
    public static final String INVALID_AVAILABLE_STOCK_UNIT = "Available stock unit cannot be null.";

    /**
     * The constant INVALID_AVAILABLE_STOCK_NUM.
     */
    public static final String INVALID_AVAILABLE_STOCK_NUM = "Available stock quantity must be greater than or equal to 20.";

    /**
     * The constant INVALID_STATUS.
     */
    public static final String INVALID_STATUS = "Active status cannot be null.";


    /**
     * The constant INVALID_QUANTITY_CATE_OBJ.
     */
    public static final String INVALID_QUANTITY_CATE_OBJ = "Quantities category cannot be null.";

    /**
     * The constant INVALID_QUANTITY_CATE_UNIT.
     */
    public static final String INVALID_QUANTITY_CATE_UNIT = "Quantities category unit cannot be null.";


    /**
     * The constant INVALID_QUANTITY_CATE_AMOUNT.
     */
    public static final String INVALID_QUANTITY_CATE_AMOUNT = "Quantities category amount must be greater than or equal to 10.";


    /**
     * The constant INVALID_QUANTITY_CATE_GRAM.
     */
    public static final String INVALID_QUANTITY_CATE_GRAM = "Quantities which have gram must be greater than or equal 100 gram.";


    /**
     * The constant INVALID_QUANTITY_CATE_NUM.
     */
    public static final String INVALID_QUANTITY_CATE_NUM = "Quantity must be greater than or equal to 1.";


    /**
     * The constant INVALID_QUANTITY_FOR_KG.
     */
    public static final String INVALID_QUANTITY_FOR_KG = "Selected available stock quantity is KG. Only you can add KG or GM for quantities cate.";

    /**
     * The constant INVALID_QUANTITY_FOR_PKT.
     */
    public static final String INVALID_QUANTITY_FOR_PKT = "Selected available stock is PKT. Only you can add PKT for quantities cate.";

    /**
     * The constant INVALID_QUANTITY_FOR_NOS.
     */
    public static final String INVALID_QUANTITY_FOR_NOS = "Selected available stock is NOS. Only you can add NOS for quantities cate.";


    /**
     * Instantiates a new Product validator errors.
     */
    private ProductValidatorErrors() {


    }

}
