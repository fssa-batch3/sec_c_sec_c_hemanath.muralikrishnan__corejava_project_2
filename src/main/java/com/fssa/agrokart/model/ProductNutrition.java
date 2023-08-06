/**
 *
 */
package com.fssa.agrokart.model;

import com.fssa.agrokart.constants.ProductConstants;

/**
 * A model object class for product nutrition's
 * @author HemanathMuralikrishnan
 */
public class ProductNutrition {
    private double proteinNum;
    private double carbonNumb;
    private double kcalNum;

    //	default constructor
    public ProductNutrition() {

    }

    //	parameterized constructor
    public ProductNutrition(double proteinNum, double carbonNumb, double kcalNum) {
        super();
        this.proteinNum = proteinNum;
        this.carbonNumb = carbonNumb;
        this.kcalNum = kcalNum;
    }

    public double getProteinNum() {
        return proteinNum;
    }

    public void setProteinNum(double proteinNum) {
        this.proteinNum = proteinNum;
    }

    public double getCarbonNumb() {
        return carbonNumb;
    }

    public void setCarbonNumb(double carbonNumb) {
        this.carbonNumb = carbonNumb;
    }

    public double getKcalNum() {
        return kcalNum;
    }

    public void setKcalNum(double kcalNum) {
        this.kcalNum = kcalNum;
    }

    @Override
    public String toString() {
        return "Protein: " + proteinNum + " " + ProductConstants.PROTEIN_CARBOHYDRATES_UNIT + " " +
                "Carbohydrates: " + carbonNumb + " " + ProductConstants.PROTEIN_CARBOHYDRATES_UNIT + " " +
                "Calories: " + kcalNum + " " + ProductConstants.CALORIES_UNIT;
    }
}
