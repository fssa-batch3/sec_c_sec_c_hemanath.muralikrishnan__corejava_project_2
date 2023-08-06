package com.fssa.agrokart.constants;

/**
 * An interface which holds the constant variable for a product model object
 *
 * @author HemanathMuralikrishnan
 */

public interface ProductConstants {

    //	units for protein and carbohydrates is "gram" only it will not change
    String PROTEIN_CARBOHYDRATES_UNIT = "gm";

    //	unit for kcal is "cal" only it will not change
    String CALORIES_UNIT = "kcal";

    //	minimum value for all nutrition
    static final double MINIMUM_NUTRITION = 0;

    //	variable to store the minimum quantity

    static final double MINIMUM_AVAILABLE_STOCK_QUANTITY = 20;

    //	if the selected qty is gram then the weight must be greater than 100
    static final double MINIMUM_WEIGHT_FOR_GRAM = 100;

    //	if the selected qty is other than gram must be greater than zero
    static final double MINIMUM_WEIGHT = 0;
}
