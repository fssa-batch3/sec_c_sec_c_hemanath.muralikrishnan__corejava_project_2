/**
 * A model class representing the available stock of a product.
 * This class defines the structure of the available stock information for a product.
 * Each instance of this class holds the numeric value and unit of available stock.
 *
 * @param num The numeric value of available stock.
 * @param unit The unit of measurement for the available stock.
 * @author HemanathMuralikrishnan
 */
package com.fssa.agrokart.model;

import com.fssa.agrokart.enums.ProductStockUnits;

public class ProductAvailableStock {

    private double num;
    private ProductStockUnits unit;

    /**
     * Default constructor for creating an instance of ProductAvailableStock class.
     * Initializes the available stock number and unit with default values.
     */
    public ProductAvailableStock() {
        // Default constructor
    }

    /**
     * Constructor to create an instance of ProductAvailableStock class with specified values.
     *
     * @param num The numeric value of available stock.
     * @param unit The unit of measurement for the available stock.
     */
    public ProductAvailableStock(double num, ProductStockUnits unit) {
        this.num = num;
        this.unit = unit;
    }

    /**
     * Get the numeric value of available stock.
     *
     * @return The numeric value of available stock.
     */
    public double getNum() {
        return num;
    }

    /**
     * Set the numeric value of available stock.
     *
     * @param num The numeric value of available stock to set.
     */
    public void setNum(double num) {
        this.num = num;
    }

    /**
     * Get the unit of measurement for the available stock.
     *
     * @return The unit of measurement for the available stock.
     */
    public ProductStockUnits getUnit() {
        return unit;
    }

    /**
     * Set the unit of measurement for the available stock.
     *
     * @param unit The unit of measurement for the available stock to set.
     */
    public void setUnit(ProductStockUnits unit) {
        this.unit = unit;
    }

    /**
     * Generate a string representation of the available stock.
     *
     * @return A formatted string containing the available stock value and unit.
     */
    @Override
    public String toString() {
        return num + " " + unit;
    }
}
