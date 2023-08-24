package com.fssa.agrokart.service;

import com.fssa.agrokart.dao.ProductDAO;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.validator.ProductValidator;

import java.util.List;


/**
 * A class which holds the services for product model object
 * It acts has mediator between validator and DAO class
 *
 * @author HemanathMuralikrishnan
 */

// this class holds the product CRUD service

public class ProductService {

	
//	TODO: handle the validator exception and daoexception as service exception catch the exception and print
//	with message or stack trace
	
    //	initializing the validator and DAO
    ProductValidator validator = new ProductValidator();
    ProductDAO productDAO = new ProductDAO();

    //	Service to insert the product into the table
    public boolean addProduct(Product product) throws InvalidInputException, DAOException {

//		sending the product to validate the details
        if (validator.validateProduct(product)) {

//			if all details are valid, then insert into the database
            productDAO.insertProduct(product);
        }

//		if there is no exception, then return true
        return true;
    }

    //	Service to read the product by name
    public Product readProductByName(String name) throws DAOException {

        //		if there is no exception, then return true
        return productDAO.readProductByName(name);
    }

    //	read all the products by method
    public List<Product> readAllProducts() throws DAOException {

        //		if there is no exception, then return true
        return productDAO.getAllProducts();
    }

    //	delete the product by id method
    public boolean deleteProductById(int id) throws DAOException {

        productDAO.deleteProduct(id);

//		if the deletion is successfully then return true
        return true;

    }

    //	service to update the product
    public boolean updateProductById(int id, Product product) throws InvalidInputException, DAOException {

//		sending the product to validate the details
        if (validator.validateProduct(product)) {

//			if all details are valid, then insert into the database
            productDAO.updateProductById(id, product);
        }

//		if there is no exception, then return true
        return true;
    }

}
