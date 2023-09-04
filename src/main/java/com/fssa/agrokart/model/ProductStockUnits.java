/**
 * An enumeration representing the units of measurement for product stock.
 * This enum defines the possible units of measurement for product stock quantities, such as kg, gm, pkt, and nos.
 * Each enum value corresponds to a specific unit of measurement.
 *
 * @see com.fssa.agrokart.model.Product for usage of product stock units.
 * @see com.fssa.agrokart.validator.ProductValidator for validation of product stock units.
 */
package com.fssa.agrokart.model;

/**
 * Enumeration defining the possible units of measurement for product stock quantities.
 *
 * @see com.fssa.agrokart.model.Product for usage of product stock units.
 * @see com.fssa.agrokart.validator.ProductValidator for validation of product stock units.
 */
public enum ProductStockUnits {
    /**
     * Represents the unit of measurement "kg" (kilograms).
     */
    KG,
    /**
     * Represents the unit of measurement "gm" (grams).
     */
    GM,
    /**
     * Represents the unit of measurement "pkt" (packets).
     */
    PKT,
    /**
     * Represents the unit of measurement "nos" (count, pieces).
     */
    NOS
}
