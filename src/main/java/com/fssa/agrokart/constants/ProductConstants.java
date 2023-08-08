package com.fssa.agrokart.constants;

/**
 * An interface which holds the constant variable for a product model object
 *
 * @author HemanathMuralikrishnan
 */

public final class ProductConstants {

    //	units for protein and carbohydrates is "gram" only it will not change
    public static final String PROTEIN_CARBOHYDRATESS_UNIT = "gm";

    //	unit for kcal is "cSal" only it will not change
    public static final String CALORIES_UNIT = "kcal";

    //	minimum value for all nutrition
    public static final double MINIMUM_NUTRITION = 0;

    //	variable to store the minimum quantity
    public static final double MINIMUM_AVAILABLE_STOCK_QUANTITY = 20;

    //	if the selected qty is gram, then the weight must be greater than 100
    public static final double MINIMUM_WEIGHT_FOR_GRAM = 100;

    //	if the selected qty is other than gram must be greater than zero
    public static final double MINIMUM_WEIGHT = 0;


    private ProductConstants(){

    }
}
