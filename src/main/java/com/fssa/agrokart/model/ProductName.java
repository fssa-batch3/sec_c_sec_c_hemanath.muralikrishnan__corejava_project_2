/**
 * A model class representing product names in different languages.
 * This class defines the structure of product names, including their English and Tamil versions.
 * Each instance of this class holds the English and Tamil names of a product.
 *
 * @param englishName The name of the product in English.
 * @param tamilName The name of the product in Tamil.
 * @author HemanathMuralikrishnan
 */
package com.fssa.agrokart.model;

public class ProductName {

    private String englishName;
    private String tamilName;

    /**
     * Default constructor for creating an instance of ProductName class.
     * Initializes the English and Tamil names with default values.
     */
    public ProductName() {
        // Default constructor
    }

    /**
     * Constructor to create an instance of ProductName class with specified names.
     *
     * @param engName The name of the product in English.
     * @param tamName The name of the product in Tamil.
     */
    public ProductName(String engName, String tamName) {
        this.englishName = engName;
        this.tamilName = tamName;
    }

    /**
     * Get the name of the product in English.
     *
     * @return The name of the product in English.
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Set the name of the product in English.
     *
     * @param englishName The name of the product in English to set.
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * Get the name of the product in Tamil.
     *
     * @return The name of the product in Tamil.
     */
    public String getTamilName() {
        return tamilName;
    }

    /**
     * Set the name of the product in Tamil.
     *
     * @param tamilName The name of the product in Tamil to set.
     */
    public void setTamilName(String tamilName) {
        this.tamilName = tamilName;
    }

    /**
     * Generate a string representation of the product names.
     *
     * @return A formatted string containing the English and Tamil names.
     */
    @Override
    public String toString() {
        return "English Name: " + englishName + ", Tamil Name: " + tamilName;
    }
}
