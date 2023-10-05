/**
 * A model class representing quantity categories for a product.
 * This class defines the structure of quantity categories for a product, including weight, unit, and price.
 * Each instance of this class holds the weight, unit, and price of a quantity category.
 *
 * @param rs The price of the quantity category.
 * @param unit The unit of measurement for the quantity category.
 * @param weight The weight or amount of the quantity category.
 * @author HemanathMuralikrishnan
 * @see com.fssa.agrokart.model.ProductStockUnits for units of measurement.
 * @see java.lang.Comparable for implementing comparison logic.
 */
package com.fssa.agrokart.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A model object class for product quantities.
 *
 * @author HemanathMuralikrishnan
 */
public class ProductQuantitiesCategory implements Comparable<ProductQuantitiesCategory> {

	private int productId;
	private int id;
	private double rs;
	private ProductStockUnits unit;
	private double weight;

	private LocalDateTime createdDateTime;

	private LocalDateTime updatedDateTime;

	/**
	 * Default constructor for creating an instance of the ProductQuantitiesCategory
	 * class. Initializes the quantity category attributes with default values.
	 */
	public ProductQuantitiesCategory() {
		// Default constructor
	}

	/**
	 * Constructor to create an instance of the ProductQuantitiesCategory class with
	 * specified values.
	 *
	 * @param weight The weight or amount of the quantity category.
	 * @param unit   The unit of measurement for the quantity category.
	 * @param rs     The price of the quantity category.
	 */
	public ProductQuantitiesCategory(double weight, ProductStockUnits unit, double rs) {
		this.weight = weight;
		this.unit = unit;
		this.rs = rs;
	}

	/**
	 * Gets the price of the quantity category.
	 *
	 * @return The price of the quantity category.
	 */
	public double getRs() {
		return rs;
	}

	/**
	 * Sets the price of the quantity category.
	 *
	 * @param rs The price to set.
	 */
	public void setRs(double rs) {
		this.rs = rs;
	}

	/**
	 * Gets the unit of measurement for the quantity category.
	 *
	 * @return The unit of measurement for the quantity category.
	 */
	public ProductStockUnits getUnit() {
		return unit;
	}

	/**
	 * Sets the unit of measurement for the quantity category.
	 *
	 * @param unit The unit of measurement to set.
	 */
	public void setUnit(ProductStockUnits unit) {
		this.unit = unit;
	}

	/**
	 * Gets the weight or amount of the quantity category.
	 *
	 * @return The weight or amount of the quantity category.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight or amount of the quantity category.
	 *
	 * @param weight The weight or amount to set.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Gets the creation date and time of the quantity category.
	 *
	 * @return The creation date and time of the quantity category.
	 */
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * Sets the creation date and time of the quantity category.
	 *
	 * @param createdDateTime The creation date and time to set.
	 */
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * Gets the last updated date and time of the quantity category.
	 *
	 * @return The last updated date and time of the quantity category.
	 */
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	/**
	 * Sets the last updated date and time of the quantity category.
	 *
	 * @param updatedDateTime The last updated date and time to set.
	 */
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * Calculate the hash code of the ProductQuantitiesCategory object based on its
	 * weight and price.
	 *
	 * @return The hash code of the object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(rs, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ProductQuantitiesCategory other = (ProductQuantitiesCategory) obj;

		// Compare the fields you consider for equality
		return Objects.equals(rs, other.rs) && Objects.equals(weight, other.weight);
	}

	/**
	 * Compare two ProductQuantitiesCategory objects based on their price.
	 *
	 * @param other The object to compare with.
	 * @return A negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(ProductQuantitiesCategory other) {
		return Double.compare(this.rs, other.rs);
	}

	/**
	 * Generate a string representation of the quantity category.
	 *
	 * @return A formatted string containing the weight, unit, and price.
	 */
	@Override
	public String toString() {
		return "ProductQuantitiesCategory{" + "rs=" + rs + ", unit=" + unit + ", weight=" + weight
				+ ", createdDateTime=" + createdDateTime + ", updatedDateTime=" + updatedDateTime + '}';
	}
}
