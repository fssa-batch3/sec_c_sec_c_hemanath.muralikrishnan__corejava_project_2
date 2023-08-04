/**
 * 
 */
package com.fssa.agrokart.model;

import java.util.Comparator;
import java.util.Objects;

import com.fssa.agrokart.enums.ProductStockUnits;

/**
 * @author HemanathMuralikrishn
 *
 */
public class ProductQuantites implements Comparable<ProductQuantites> {

	private double rs;
	private ProductStockUnits unit;
	private double weight;

//	default constructor
	public ProductQuantites() {

	}

//	Parameterized constructor
	public ProductQuantites(double weight, ProductStockUnits unit, double rs) {

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
		ProductQuantites other = (ProductQuantites) obj;
		return Double.compare(other.rs, rs) == 0 && Double.compare(other.weight, weight) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rs, weight);
	}

	@Override
	public int compareTo(ProductQuantites other) {
		return Double.compare(this.rs, other.rs);
	}

	@Override
	public String toString() {
		return "Quantity: " + weight + " " + unit + ", Rs: " + rs;
	}

}
