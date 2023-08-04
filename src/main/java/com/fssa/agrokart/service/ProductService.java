package com.fssa.agrokart.service;

import com.fssa.agrokart.validator.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.TreeSet;

import com.fssa.agrokart.dao.*;
import com.fssa.agrokart.enums.ProductCategory;
import com.fssa.agrokart.enums.ProductStatus;
import com.fssa.agrokart.enums.ProductStockUnits;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.exceptions.*;

// this class holds the product CRUD service

public class ProductService {

//	initiliazing the validator and DAO
	ProductValidator validator = new ProductValidator();
	ProductDAO productDAO = new ProductDAO();

//	Service to insert the product into the table
	public boolean addProduct(Product product) throws InvalidProductDataException, ProductDAOException {

//		sending the product to validate the details
		if (validator.validateProduct(product)) {

//			if all details are valid then insert into the database
			productDAO.insertProduct(product);
		}

//		if there is no exception then return true
		return true;
	}

//	Service to read the product prodcut by name
	public Product readProductByName(String name) throws ProductDAOException {

		Product product = productDAO.readProductByName(name);

//		if there is no exception then return true
		return product;
	}

//	read all the prodcuts by method
	public List<Product> readAllProducts() throws ProductDAOException {

		List<Product> productList = productDAO.getAllProducts();

		for (Product ele : productList) {

			System.out.println(ele);
		}

//		if there is no exceptoin then return true
		return productList;
	}

//	delete the proudct by id method
	public boolean deleteProductById(int id) throws ProductDAOException {

		productDAO.deleteProduct(id);

//		if the deleteion is successfull then return true
		return true;

	}

//	service to update the product
	public boolean updateProductById(int id, Product product) throws InvalidProductDataException, ProductDAOException {

//		sending the product to validate the details
		if (validator.validateProduct(product)) {

//			if all details are valid then insert into the database
			productDAO.updateProductById(id, product);
		}

//		if there is no exception then return true
		return true;
	}

}
