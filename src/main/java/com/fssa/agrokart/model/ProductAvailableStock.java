/**
 * A model class representing the available stock of a product.
 * This class defines the structure of the available stock information for a product.
 * Each instance of this class holds the numeric value and unit of available stock.
 *
 * @param num  The numeric value of available stock.
 * @param unit The unit of measurement for the available stock.
 * @author HemanathMuralikrishnan
 */
package com.fssa.agrokart.model;

import java.time.LocalDateTime;

public class ProductAvailableStock {

    private double num;
    private ProductStockUnits unit;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedTimeDate;

    /**
     * Default constructor for creating an instance of the ProductAvailableStock class.
     * Initializes the available stock number and unit with default values.
     */
    public ProductAvailableStock() {
        // Default constructor
    }

    /**
     * Constructor to create an instance of the ProductAvailableStock class with specified values.
     *
     * @param num  The numeric value of available stock.
     * @param unit The unit of measurement for the available stock.
     */
    public ProductAvailableStock(double num, ProductStockUnits unit) {
        this.num = num;
        this.unit = unit;
    }

    /**
     * Gets the numeric value of available stock.
     *
     * @return The numeric value of available stock.
     */
    public double getNum() {
        return num;
    }

    /**
     * Sets the numeric value of available stock.
     *
     * @param num The numeric value of available stock to set.
     */
    public void setNum(double num) {
        this.num = num;
    }

    /**
     * Gets the unit of measurement for the available stock.
     *
     * @return The unit of measurement for the available stock.
     */
    public ProductStockUnits getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement for the available stock.
     *
     * @param unit The unit of measurement for the available stock to set.
     */
    public void setUnit(ProductStockUnits unit) {
        this.unit = unit;
    }

    /**
     * Gets the creation date and time of the available stock information.
     *
     * @return The creation date and time of the available stock information.
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the creation date and time of the available stock information.
     *
     * @param createdDateTime The creation date and time to set.
     */
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Gets the last updated date and time of the available stock information.
     *
     * @return The last updated date and time of the available stock information.
     */
    public LocalDateTime getUpdatedTimeDate() {
        return updatedTimeDate;
    }

    /**
     * Sets the last updated date and time of the available stock information.
     *
     * @param updatedTimeDate The last updated date and time to set.
     */
    public void setUpdatedTimeDate(LocalDateTime updatedTimeDate) {
        this.updatedTimeDate = updatedTimeDate;
    }

    /**
     * Returns a string representation of the ProductAvailableStock object.
     *
     * @return A string representation of the ProductAvailableStock object.
     */
    @Override
    public String toString() {
        return "ProductAvailableStock{" +
                "num=" + num +
                ", unit=" + unit +
                ", createdDateTime=" + createdDateTime +
                ", updatedTimeDate=" + updatedTimeDate +
                '}';
    }
}
