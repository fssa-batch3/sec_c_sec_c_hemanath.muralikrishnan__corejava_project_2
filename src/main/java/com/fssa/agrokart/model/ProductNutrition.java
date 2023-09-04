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
 * @see com.fssa.agrokart.validator.ProductConstants for units of measurement.
 */
package com.fssa.agrokart.model;

import java.time.LocalDateTime;

public class ProductNutrition {

    private double proteinNum;
    private double carbonNumb;
    private double kcalNum;
    private LocalDateTime createdDateTime;
    private LocalDateTime udpatedDateTime;

    /**
     * Default constructor for creating an instance of the ProductNutrition class.
     * Initializes the nutritional values with default values.
     */
    public ProductNutrition() {
        // Default constructor
    }

    /**
     * Constructor to create an instance of the ProductNutrition class with specified nutritional values.
     *
     * @param proteinNum The amount of protein in the product.
     * @param carbonNumb The amount of carbohydrates in the product.
     * @param kcalNum    The amount of calories in the product.
     */
    public ProductNutrition(double proteinNum, double carbonNumb, double kcalNum) {
        this.proteinNum = proteinNum;
        this.carbonNumb = carbonNumb;
        this.kcalNum = kcalNum;
    }

    /**
     * Gets the amount of protein in the product.
     *
     * @return The amount of protein in the product.
     */
    public double getProteinNum() {
        return proteinNum;
    }

    /**
     * Sets the amount of protein in the product.
     *
     * @param proteinNum The amount of protein to set.
     */
    public void setProteinNum(double proteinNum) {
        this.proteinNum = proteinNum;
    }

    /**
     * Gets the amount of carbohydrates in the product.
     *
     * @return The amount of carbohydrates in the product.
     */
    public double getCarbonNumb() {
        return carbonNumb;
    }

    /**
     * Sets the amount of carbohydrates in the product.
     *
     * @param carbonNumb The amount of carbohydrates to set.
     */
    public void setCarbonNumb(double carbonNumb) {
        this.carbonNumb = carbonNumb;
    }

    /**
     * Gets the amount of calories in the product.
     *
     * @return The amount of calories in the product.
     */
    public double getKcalNum() {
        return kcalNum;
    }

    /**
     * Sets the amount of calories in the product.
     *
     * @param kcalNum The amount of calories to set.
     */
    public void setKcalNum(double kcalNum) {
        this.kcalNum = kcalNum;
    }

    /**
     * Gets the creation date and time of the nutritional information.
     *
     * @return The creation date and time of the nutritional information.
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the creation date and time of the nutritional information.
     *
     * @param createdDateTime The creation date and time to set.
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets the last updated date and time of the nutritional information.
     *
     * @return The last updated date and time of the nutritional information.
     */
    public LocalDateTime getUdpatedDateTime() {
        return udpatedDateTime;
    }

    /**
     * Sets the last updated date and time of the nutritional information.
     *
     * @param udpatedDateTime The last updated date and time to set.
     */
    public void setUdpatedDateTime(LocalDateTime udpatedDateTime) {
        this.udpatedDateTime = udpatedDateTime;
    }

    /**
     * Returns a string representation of the ProductNutrition object.
     *
     * @return A string representation of the ProductNutrition object.
     */
    @Override
    public String toString() {
        return "ProductNutrition{" +
                "proteinNum=" + proteinNum +
                ", carbonNumb=" + carbonNumb +
                ", kcalNum=" + kcalNum +
                ", createdDateTime=" + createdDateTime +
                ", udpatedDateTime=" + udpatedDateTime +
                '}';
    }
}
