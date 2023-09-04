package com.fssa.agrokart.model;

/**
 * Enumeration representing different categories of products.
 *
 * This enum defines various categories that a product can belong to.
 * Each constant in this enum corresponds to a specific product category.
 *
 * Example usage:
 * ProductCategory category = ProductCategory.FRESH_FRUITS;
 *
 * @see Product
 */
public enum ProductCategory {

    /**
     * Exotic fruits product category.
     */
    EXOTIC_FRUITS,

    /**
     * Exotic veggies product category.
     */
    EXOTIC_VEGGIES,

    /**
     * Fresh veggies product category.
     */
    FRESH_VEGGIES,

    /**
     * Fresh fruits product category.
     */
    FRESH_FRUITS,

    /**
     * Leafy green product category.
     */
    LEAFY_GREEN,

    /**
     * Tubers product category.
     */
    TUBERS
}
