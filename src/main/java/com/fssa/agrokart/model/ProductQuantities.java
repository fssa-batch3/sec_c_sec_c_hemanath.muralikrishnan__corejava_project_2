/**
 *
 */
package com.fssa.agrokart.model;

import java.util.Objects;

import com.fssa.agrokart.enums.ProductStockUnits;

/**
 * A model object class for product quantities
 * @author HemanathMuralikrishnan
 */
public class ProductQuantities implements Comparable<ProductQuantities> {

    private double rs;
    private ProductStockUnits unit;
    private double weight;

    //	default constructor
    public ProductQuantities() {

    }

    //	Parameterized constructor
    public ProductQuantities(double weight, ProductStockUnits unit, double rs) {

        this.weight = weight;
        this.unit = unit;
        this.rs = rs;
    }

    // get and set for weight

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    // get and set for unit

    public ProductStockUnits getUnit() {
        return unit;
    }

    public void setUnit(ProductStockUnits unit) {
        this.unit = unit;
    }

    // get and set for rs

    public double getRs() {
        return rs;
    }

    public void setRs(float rs) {
        this.rs = rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProductQuantities other = (ProductQuantities) obj;
        return Double.compare(other.rs, rs) == 0 && Double.compare(other.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rs, weight);
    }

    @Override
    public int compareTo(ProductQuantities other) {
        return Double.compare(this.rs, other.rs);
    }

    @Override
    public String toString() {
        return "Quantity: " + weight + " " + unit + ", Rs: " + rs;
    }

}
