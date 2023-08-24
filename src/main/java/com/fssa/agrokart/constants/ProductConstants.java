package com.fssa.agrokart.constants;

/**
 * The `ProductConstants` class contains constant variables related to the product model in the Agrokart system.
 * It defines various units, minimum values, and thresholds used for product attributes.
 *
 * These constants are used to maintain standardized values and thresholds for product-related calculations and checks.
 * The class is designed as final and cannot be instantiated.
 *
 * @author HemanathMuralikrishnan
 *
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
    public static final double MINIMUM_WEIGHT = 1;

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
