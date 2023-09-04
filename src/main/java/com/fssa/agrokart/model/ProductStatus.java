/**
 * An enumeration representing the status of a product.
 * This enum defines the possible status values for a product, indicating whether it is available or not.
 * Each enum value represents a specific product status.
 *
 * @see com.fssa.agrokart.model.Product for usage of product status.
 * @see com.fssa.agrokart.validator.ProductValidator for validation of product status.
 */
package com.fssa.agrokart.model;

/**
 * Enumeration defining the possible statuses of a product.
 *
 * @see com.fssa.agrokart.model.Product for usage of product status.
 * @see com.fssa.agrokart.validator.ProductValidator for validation of product status.
 */
public enum ProductStatus {
	/**
	 * Represents the available status of a product.
	 */
	AVAILABLE,
	/**
	 * Represents the not available status of a product.
	 */
	NOT_AVAILABLE
}
