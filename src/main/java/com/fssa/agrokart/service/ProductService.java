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

    //	initializing the validator and DAO
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

    //	Service to read the product by name
    public Product readProductByName(String name) throws ProductDAOException {

        Product product = productDAO.readProductByName(name);

//		if there is no exception then return true
        return product;
    }

    //	read all the products by method
    public List<Product> readAllProducts() throws ProductDAOException {

        List<Product> productList = productDAO.getAllProducts();

//		if there is no exception then return true
        return productList;
    }

    //	delete the product by id method
    public boolean deleteProductById(int id) throws ProductDAOException {

        productDAO.deleteProduct(id);

//		if the deletion is successfully then return true
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
