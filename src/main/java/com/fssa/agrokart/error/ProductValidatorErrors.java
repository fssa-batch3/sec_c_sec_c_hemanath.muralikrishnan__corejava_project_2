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

    //	INVALID_OBJECT nullity checks
    public static final String INVALID_OBJECT = "object cannot be null.";

    //	INVALID_INSTANCE_CLASS not instance of current class
    public static final String INVALID_INSTANCE_CLASS = "object is not current instance of class.";

    //	INVALID_STRING checks for null or empty string
    public static final String INVALID_STRING = "cannot be null or empty string.";

    //	INVALID product id
    public static final String INVALID_ID = "id cannot be zero or lesser than zero.";

    //	PATTERN_MISMATCH_MESSAGE
    public static final String PATTERN_MISMATCH_MESSAGE = "given input doesn't match the requested format.";

    //	INVALID PRODUCT CATEGORY SELECTED
    public static final String INVALID_CATEGORY = "invalid category selected";

    //	INVALID nutr count
    public static final String INVALID_NUTR_COUNT = "value cannot be lesser than zero";

    //	INVALID available stock unit
    public static final String INVALID_AVAIL_UNIT = "Product available stock unit is null.";

    //	INVALID available stock num
    public static final String INVALID_AVAIL_NUM = "available stock amount must be greater that 20";

    //	INVALID product created date
    public static final String INVALID_CREATED_DATE_NULL = "created date cannot be null.";

    //	INVALID product created date
    public static final String INVALID_CREATED_DATE = "created date must be the current date";

    //	INVALID product created time
    public static final String INVALID_CREATED_TIME_NULL = "created time cannot be null.";

    //	INVALID product created time
    public static final String INVALID_CREATED_TIME = "created time must be within 24 hours.";

    //	INVALID product updated date
    public static final String INVALID_UPDATED_DATE_NULL = "updated date cannot be null.";

    //	INVALID product updated date
    public static final String INVALID_UPDATED_DATE = "updated date must be same as created date or future date";

    //	INVALID product update time
    public static final String INVALID_UPDATED_TIME_NULL = "update time cannot be null.";

    //	INVALID product update time
    public static final String INVALID_UPDATED_TIME = "updated time must be greater than or equal to the creation time.";

    //	INVALID product quantity unit
    public static final String INVALID_QTY_OBJ = "object cannot be null.";

    //	INVALID product quantity unit
    public static final String INVALID_QTY_UNIT = "Product quantity unit cannot be null.";

    //	INVALID product quantity selected for kg
    public static final String INVALID_QTY_KG = "is kg. So you can only add the quantites kg and gm unit.";

    //	INVALID product quantity selected for pkt
    public static final String INVALID_QTY_PKT = "is pkt. So you can only add the quantites with pkt unit.";

    //	INVALID product quantity selected for nos
    public static final String INVALID_QTY_NOS = "is nos. So you can only add the quantites with nos unit.";

    //	INVALID product price
    public static final String INVALID_QTY_RS = "quantity rupees must be greater than 10";

    //	INVALID product quantity weight
    public static final String INVALID_QTY_WEIGHT_GM = "for gram quantity weight must be greater than 100 gm";

    //	INVALID product quantity weight
    public static final String INVALID_QTY_WEIGHT = "quantity weight is invalid.";

    //	INVALID product status
    public static final String INVALID_STATUS = "invalid.";

    private ProductValidatorErrors() {


    }

}
