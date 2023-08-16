/**
 *
 */
package com.fssa.agrokart.model;
import java.time.LocalDateTime;
import java.util.*;

import com.fssa.agrokart.enums.*;

/**
 * product object model
 * com.fssa.agrokart.model.Product - Class representing a product in the
 * Agrokart application.
 * This class defines the structure of a product object and its
 * associated attributes. Each product in the Agrokart application is
 * represented by an instance of this class.
 *
 * @author HemanathMuralikrishnan
 */
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

//	Default constructor

    public Product() {
        // default constructor to get instance the object
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