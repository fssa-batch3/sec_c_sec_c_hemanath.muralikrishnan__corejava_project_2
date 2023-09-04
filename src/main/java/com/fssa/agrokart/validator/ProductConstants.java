package com.fssa.agrokart.validator;

/**
 * The `ProductConstants` class contains constant variables related to the product model in the Agrokart system.
 * It defines various units, minimum values, and thresholds used for product attributes.
 * <p>
 * These constants are used to maintain standardized values and thresholds for product-related calculations and checks.
 * The class is designed as final and cannot be instantiated.
 *
 * @author HemanathMuralikrishnan
 */
public final class ProductConstants {

    /**
     * The unit for protein and carbohydrates in products, represented in grams.
     */
    public static final String PROTEIN_CARBOHYDRATES_UNIT = "gm";

    /**
     * The unit for calorie content in products, represented in kilocalories.
     */
    public static final String CALORIES_UNIT = "kcal";

    /**
     * The minimum value for all nutrition-related attributes in products.
     */
    public static final double MINIMUM_NUTRITION = 0;

    public static final double MINIMUM_PROTEIN_VALUE = 0;

    public static final double MAXIMUM_PROTEIN_VALUE = 12.5;

    public static final double MINIMUM_CARBOHYDRATES_VALUE = 0;

    public static final double MAXIMUM_CARBOHYDRATES_VALUE = 50;

    public static final double MINIMUM_CALORIES_VALUE = 0;

    public static final double MAXIMUM_CALORIES_VALUE = 200;

    /**
     * The minimum available stock quantity for products.
     */
    public static final double MINIMUM_AVAILABLE_STOCK_QUANTITY = 20;

    /**
     * The minimum weight required for products measured in grams if the selected quantity is in grams.
     */
    public static final double MINIMUM_WEIGHT_FOR_GRAM = 100;

    /**
     * The minimum weight required for products measured in units other than grams.
     */
    public static final double MINIMUM_WEIGHT_FOR_KG = 1;

    public static final double MINIMUM_WEIGHT_FOR_PKT = 1;

    public static final double MINIMUM_WEIGHT_FOR_NOS = 1;

    /**
     * The minimum amount allowed for all products.
     */
    public static final double MINIMUM_AMOUNT = 10;

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private ProductConstants() {
        // This constructor is intentionally empty.
    }
}
