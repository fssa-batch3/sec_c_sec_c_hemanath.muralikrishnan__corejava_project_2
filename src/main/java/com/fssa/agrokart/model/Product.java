/**
 * 
 */
package com.fssa.agrokart.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import com.fssa.agrokart.enums.*;

/**
 * @author HemanathMuralikrishn product object model
 * 
 *         com.fssa.agrokart.model.Product - Class representing a product in the
 *         Agrokart application.
 * 
 *         This class defines the structure of a product object and its
 *         associated attributes. Each product in the Agrokart application is
 *         represented by an instance of this class.
 * 
 */
public class Product {

	private int id;
	private ProductName name;
	private String imageUrl;
	private ProductCategory category;
	private String description;
	private ProductNutritions nutritions;
	private ProductAvailableStock availableStock;
	private TreeSet<ProductQuantites> quantites;
	private ProductStatus status;
	private LocalDate creationDate;
	private LocalTime creationTime;
	private LocalDate updatedDate;
	private LocalTime updatedTime;

//	Default constructor

	public Product() {

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

	public ProductNutritions getNutritions() {
		return nutritions;
	}

	public void setNutritions(ProductNutritions nutritions) {
		this.nutritions = nutritions;
	}

	public ProductAvailableStock getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(ProductAvailableStock availableStock) {
		this.availableStock = availableStock;
	}

	public TreeSet<ProductQuantites> getQuantites() {
		return quantites;
	}

	public void setQuantites(TreeSet<ProductQuantites> quantites) {
		this.quantites = quantites;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public LocalTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Product ID: ").append(id).append("\n");
		sb.append("Product Name: ").append(name).append("\n");
		sb.append("Image URL: ").append(imageUrl).append("\n");
		sb.append("Category: ").append(category).append("\n");
		sb.append("Description: ").append(description).append("\n");
		sb.append("Nutritions: ").append(nutritions).append("\n");
		sb.append("Available Stock: ").append(availableStock).append("\n");

		// Append Quantities
		sb.append("Quantities: [");
		for (ProductQuantites quantity : quantites) {
			sb.append(quantity).append(", ");
		}
		// Remove the last comma and space if quantities are present
		if (!quantites.isEmpty()) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]\n");

		sb.append("Status: ").append(status).append("\n");
		sb.append("Creation Date: ").append(creationDate).append("\n");
		sb.append("Creation Time: ").append(creationTime).append("\n");
		sb.append("Updated Date: ").append(updatedDate).append("\n");
		sb.append("Updated Time: ").append(updatedTime).append("\n");

		return sb.toString();
	}

}