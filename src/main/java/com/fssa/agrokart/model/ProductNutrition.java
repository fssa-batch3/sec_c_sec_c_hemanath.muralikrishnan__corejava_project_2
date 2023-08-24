/**
 * A model class representing nutritional information for a product.
 * This class defines the structure of nutritional information for a product,
 * including protein, carbohydrates, and calories.
 * Each instance of this class holds the numeric values for protein, carbohydrates, and calories.
 *
 * @param proteinNum The amount of protein in the product.
 * @param carbonNumb The amount of carbohydrates in the product.
 * @param kcalNum The amount of calories in the product.
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.constants.ProductConstants for units of measurement.
 */
package com.fssa.agrokart.model;

import com.fssa.agrokart.constants.ProductConstants;

public class ProductNutrition {

    private double proteinNum;
    private double carbonNumb;
    private double kcalNum;

    /**
     * Default constructor for creating an instance of ProductNutrition class.
     * Initializes the nutritional values with default values.
     */
    public ProductNutrition() {
        // Default constructor
    }

    /**
     * Constructor to create an instance of ProductNutrition class with specified nutritional values.
     *
     * @param proteinNum The amount of protein in the product.
     * @param carbonNumb The amount of carbohydrates in the product.
     * @param kcalNum The amount of calories in the product.
     */
    public ProductNutrition(double proteinNum, double carbonNumb, double kcalNum) {
        this.proteinNum = proteinNum;
        this.carbonNumb = carbonNumb;
        this.kcalNum = kcalNum;
    }

    /**
     * Get the amount of protein in the product.
     *
     * @return The amount of protein.
     */
    public double getProteinNum() {
        return proteinNum;
    }

    /**
     * Set the amount of protein in the product.
     *
     * @param proteinNum The amount of protein to set.
     */
    public void setProteinNum(double proteinNum) {
        this.proteinNum = proteinNum;
    }

    /**
     * Get the amount of carbohydrates in the product.
     *
     * @return The amount of carbohydrates.
     */
    public double getCarbonNumb() {
        return carbonNumb;
    }

    /**
     * Set the amount of carbohydrates in the product.
     *
     * @param carbonNumb The amount of carbohydrates to set.
     */
    public void setCarbonNumb(double carbonNumb) {
        this.carbonNumb = carbonNumb;
    }

    /**
     * Get the amount of calories in the product.
     *
     * @return The amount of calories.
     */
    public double getKcalNum() {
        return kcalNum;
    }

    /**
     * Set the amount of calories in the product.
     *
     * @param kcalNum The amount of calories to set.
     */
    public void setKcalNum(double kcalNum) {
        this.kcalNum = kcalNum;
    }

    /**
     * Generate a string representation of the nutritional information.
     *
     * @return A formatted string containing the protein, carbohydrates, and calories.
     */
    @Override
    public String toString() {
        return "Protein: " + proteinNum + " " + ProductConstants.PROTEIN_CARBOHYDRATES_UNIT + " " +
                "Carbohydrates: " + carbonNumb + " " + ProductConstants.PROTEIN_CARBOHYDRATES_UNIT + " " +
                "Calories: " + kcalNum + " " + ProductConstants.CALORIES_UNIT;
    }
}
