/**
 * A model class representing quantity categories for a product.
 * This class defines the structure of quantity categories for a product, including weight, unit, and price.
 * Each instance of this class holds the weight, unit, and price of a quantity category.
 *
 * @param rs The price of the quantity category.
 * @param unit The unit of measurement for the quantity category.
 * @param weight The weight or amount of the quantity category.
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.enums.ProductStockUnits for units of measurement.
 * @see java.lang.Comparable for implementing comparison logic.
 */
package com.fssa.agrokart.model;

import java.util.Objects;

import com.fssa.agrokart.enums.ProductStockUnits;

/**
 * A model object class for product quantities
 *
 * @author HemanathMuralikrishnan
 */
public class ProductQuantitiesCate implements Comparable<ProductQuantitiesCate> {

    private double rs;
    private ProductStockUnits unit;
    private double weight;

    /**
     * Default constructor for creating an instance of ProductQuantitiesCate class.
     * Initializes the quantity category attributes with default values.
     */
    public ProductQuantitiesCate() {
        // Default constructor
    }


    /**
     * Constructor to create an instance of ProductQuantitiesCate class with specified values.
     *
     * @param weight The weight or amount of the quantity category.
     * @param unit   The unit of measurement for the quantity category.
     * @param rs     The price of the quantity category.
     */
    public ProductQuantitiesCate(double weight, ProductStockUnits unit, double rs) {

        this.weight = weight;
        this.unit = unit;
        this.rs = rs;
    }

    /**
     * Get the weight or amount of the quantity category.
     *
     * @return The weight of the quantity category.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the weight or amount of the quantity category.
     *
     * @param weight The weight of the quantity category to set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Get the unit of measurement for the quantity category.
     *
     * @return The unit of measurement for the quantity category.
     */
    public ProductStockUnits getUnit() {
        return unit;
    }

    /**
     * Set the unit of measurement for the quantity category.
     *
     * @param unit The unit of measurement for the quantity category to set.
     */
    public void setUnit(ProductStockUnits unit) {
        this.unit = unit;
    }

    /**
     * Get the price of the quantity category.
     *
     * @return The price of the quantity category.
     */
    public double getRs() {
        return rs;
    }

    /**
     * Set the price of the quantity category.
     *
     * @param rs The price of the quantity category to set.
     */
    public void setRs(double rs) {
        this.rs = rs;
    }

    /**
     * Check if two ProductQuantitiesCate objects are equal based on their weight and price.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductQuantitiesCate other = (ProductQuantitiesCate) obj;
        return Double.compare(other.rs, rs) == 0 && Double.compare(other.weight, weight) == 0;
    }

    /**
     * Calculate the hash code of the ProductQuantitiesCate object based on its weight and price.
     *
     * @return The hash code of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(rs, weight);
    }

    /**
     * Compare two ProductQuantitiesCate objects based on their price.
     *
     * @param other The object to compare with.
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to,
     * or greater than the specified object.
     */
    @Override
    public int compareTo(ProductQuantitiesCate other) {
        return Double.compare(this.rs, other.rs);
    }

    /**
     * Generate a string representation of the quantity category.
     *
     * @return A formatted string containing the weight, unit, and price.
     */
    @Override
    public String toString() {
        return "Quantity: " + weight + " " + unit + ", Rs: " + rs;
    }

}
