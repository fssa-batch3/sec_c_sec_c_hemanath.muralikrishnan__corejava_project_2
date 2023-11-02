package com.fssa.agrokart.service;

import com.fssa.agrokart.dao.ProductDAO;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.util.ExceptionLoggerUtil;
import com.fssa.agrokart.validator.ProductValidator;

import java.util.List;
import java.util.Map;

/**
 * Service class that provides operations related to the Product model object.
 * This class acts as a mediator between the validator and DAO classes,
 * providing methods to add, read, update, and delete products.
 *
 * @see com.fssa.agrokart.dao.ProductDAO for data access operations related to
 *      products.
 * @see com.fssa.agrokart.exception.InvalidInputException for input validation
 *      errors.
 * @see com.fssa.agrokart.exception.DAOException for data access errors.
 * @see com.fssa.agrokart.exception.ServiceException for service-related errors.
 * @see com.fssa.agrokart.model.Product for the product model.
 * @see com.fssa.agrokart.validator.ProductValidator for product data
 *      validation.
 */
public class ProductService {

	// Initializing the validator and DAO
	ProductValidator validator = new ProductValidator();
	ProductDAO productDAO = new ProductDAO();

	/**
	 * Service method to add a product into the database.
	 *
	 * @param product The product to be added.
	 * @return True if the product is added successfully, false otherwise.
	 * @throws ServiceException If there is an error in validating the product or
	 *                          performing the operation.
	 */
	/**
	 * Service method to add a product into the database.
	 *
	 * @param product The product to be added.
	 * @return True if the product is added successfully, false otherwise.
	 * @throws ServiceException If there is an error in validating the product or
	 *                          performing the operation.
	 */
	public boolean addProduct(Product product) throws ServiceException {

		try {
			// Validate the product details

			if (validator.validateProduct(product)) {

				// Check if a product with the new English name already exists
				String newEnglishName = product.getName().getEnglishName();

				if (productDAO.isProductWithNameExists(newEnglishName, product.getSeller().getId())) {
					throw new ServiceException("A product with English name " + newEnglishName + " already exists.");
				}

				// Check if a product with the new Tamil name already exists
				String newTamilName = product.getName().getTamilName();

				if (productDAO.isProductWithNameExists(newTamilName, product.getSeller().getId())) {
					throw new ServiceException("A product with Tamil name " + newTamilName + " already exists.");
				}

				// If details are valid and no duplicate name, insert into the database
				return productDAO.insertProduct(product);
			}

		} catch (InvalidInputException | DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Service method to read a product by its name.
	 *
	 * @param name The name of the product.
	 * @return The product with the given name.
	 * @throws ServiceException If there is an error in performing the operation or
	 *                          the product is not found.
	 */
	public Product readProductByName(String name) throws ServiceException {

		Product product = null;
		try {
			product = productDAO.readProductByName(name);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

		if (product == null) {
			throw new ServiceException("Product not found with the name: " + name + ".");
		}

		return product;
	}

	/**
	 * Retrieves a product from the database based on the given product ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return A Product object containing the details of the retrieved product.
	 * @throws ServiceException If there's an issue with retrieving the product or
	 *                          if the product does not exist.
	 */
	public Product readProductById(int id) throws ServiceException {
		Product product = null;

		try {
			// Check if the product with the given ID exists
			if (!productDAO.isProductWithIdExists(id)) {
				throw new ServiceException("Product with ID " + id + " not found.");
			}

			// Retrieve the product from the database
			product = productDAO.readProductById(id);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

		return product;
	}

	/**
	 * Service method to read all products from the database.
	 *
	 * @return A list of all products in the database.
	 * @throws ServiceException If there is an error in performing the operation or
	 *                          no products are found.
	 */
	public List<Product> readAllProducts() throws ServiceException {
		try {
			return productDAO.getAllProducts();
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Service method to delete a product by its ID.
	 *
	 * @param id The ID of the product to be deleted.
	 * @return True if the product is deleted successfully, false otherwise.
	 * @throws ServiceException If there is an error in performing the operation.
	 */
	public boolean deleteProductById(int id) throws ServiceException {
		try {
			// Check if the product with the given ID exists
			if (!productDAO.isProductWithIdExists(id)) {
				throw new ServiceException("Product with ID " + id + " not found.");
			}

			return productDAO.deleteProduct(id);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Service method to update a product by its ID.
	 *
	 * @param id      The ID of the product to be updated.
	 * @param product The updated product data.
	 * @return True if the product is updated successfully, false otherwise.
	 * @throws ServiceException If there is an error in validating the product or
	 *                          performing the operation.
	 */
	public boolean updateProductById(int id, Product product) throws ServiceException {
		try {
			// Check if the product with the given ID exists
			if (!productDAO.isProductWithIdExists(id)) {
				throw new ServiceException("Product with ID " + id + " not found.");
			}

			// Validate the product details
			if (validator.validateProduct(product)) {
				// If details are valid, update the product in the database
				return productDAO.updateProductById(id, product);
			}

		} catch (InvalidInputException | DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
		return false;
	}

	/**
	 * Updates the status of a product with the given name and ID.
	 *
	 * @param name The name of the product whose status needs to be updated.
	 * @param id   The ID of the product to be updated.
	 * @return {@code true} if the product status was successfully updated,
	 *         {@code false} otherwise.
	 * @throws ServiceException If there is an issue with updating the product
	 *                          status.
	 */

	public boolean updateProductStatus(String name, int id) throws ServiceException {

		try {

			if (!productDAO.isProductWithIdExists(id)) {

				throw new ServiceException("Product with ID " + id + " not found.");
			}

			return productDAO.updateProductStatus(name, id);

		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

	public Map<Integer, Integer> getCatCounts() throws ServiceException {

		try {

			return productDAO.getCategoryCount();
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

}
