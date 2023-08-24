/**
 * Class representing a product in the Agrokart application.
 * This class defines the structure of a product object and its associated attributes.
 * Each product in the Agrokart application is represented by an instance of this class.
 *
 * <p> The class includes attributes such as ID, name, image URL, category, description, nutrition information,
 * available stock, quantities, status, and creation/update date and time.
 *
 * <p> It provides getter and setter methods for these attributes.
 *
 * <p> The toString method generates a formatted string representation of the product, including its details.
 *
 * @param id The unique identifier for the product.
 * @param name The name of the product.
 * @param imageUrl The URL of the product image.
 * @param category The category to which the product belongs.
 * @param description A brief description of the product.
 * @param nutrition Nutritional information of the product.
 * @param availableStock Availability status of the product.
 * @param quantities The sorted set of quantity categories for the product.
 * @param status The current status of the product.
 * @param createdDateTime The date and time when the product was created.
 * @param updateDateTime The date and time when the product was last updated.
 * @return A formatted string containing the product details.
 * @author HemanathMuralikrishnan
 */


package com.fssa.agrokart.model;

import java.time.LocalDateTime;
import java.util.*;

import com.fssa.agrokart.enums.*;

public class Product {

    private int id;
    private ProductName name;
    private String imageUrl;
    private ProductCategory category;
    private String description;
    private ProductNutrition nutrition;
    private ProductAvailableStock availableStock;
    private SortedSet<ProductQuantitiesCate> quantities;
    private ProductStatus status;
    private LocalDateTime createdDateTime;

    private LocalDateTime updateDateTime;

    /**
     * Default constructor to create an instance of the Product class.
     * This constructor initializes the object with default values.
     */
    public Product() {
        // Default constructor to create an instance of the object
    }

    public ProductName getName() {
        return name;
    }

    public void setName(ProductName name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductNutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(ProductNutrition nutrition) {
        this.nutrition = nutrition;
    }

    public ProductAvailableStock getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(ProductAvailableStock availableStock) {
        this.availableStock = availableStock;
    }

    public SortedSet<ProductQuantitiesCate> getQuantities() {
        return quantities;
    }

    public void setQuantities(SortedSet<ProductQuantitiesCate> quantities) {
        this.quantities = quantities;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product ID: ").append(id).append("\n");
        sb.append("Product Name: ").append(name).append("\n");
        sb.append("Image URL: ").append(imageUrl).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Nutrition's: ").append(nutrition).append("\n");
        sb.append("Available Stock: ").append(availableStock).append("\n");

        // Append Quantities
        sb.append("Quantities: [");
        for (ProductQuantitiesCate quantity : quantities) {
            sb.append(quantity).append(", ");
        }
        // Remove the last comma and space if quantities are present
        if (!quantities.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]\n");

        sb.append("Status: ").append(status).append("\n");
        sb.append("Created Date and Time: ").append(createdDateTime).append("\n");
        sb.append("Updated Date and Time:  ").append(updateDateTime).append("\n");


        return sb.toString();
    }

}