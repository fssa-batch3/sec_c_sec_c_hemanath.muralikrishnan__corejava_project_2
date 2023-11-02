package com.fssa.agrokart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductAvailableStock;
import com.fssa.agrokart.model.ProductCategory;
import com.fssa.agrokart.model.ProductName;
import com.fssa.agrokart.model.ProductNutrition;
import com.fssa.agrokart.model.ProductQuantitiesCategory;
import com.fssa.agrokart.model.ProductStatus;
import com.fssa.agrokart.model.ProductStockUnits;
import com.fssa.agrokart.util.Logger;
import com.fssa.agrokart.validator.ProductValidatorErrors;
import com.fssa.agrokart.dao.ProductDAOErrors;

/**
 * A class which holds the valid and invalid test cases for product CRUD
 * services
 *
 * @author HemanathMuralikrishnan
 */
class TestProductService {

	// create instance of product service class
	ProductService service = new ProductService();
	// logger class to print the products
	Logger logger = new Logger();

	// creating new product
	public Product getProduct() {

		String desc = "The low calorific value of bottle gourd makes it very beneficial for individuals who are on a weight loss regime and would like to get rid of some extra pounds. One can enjoy bottle gourd without the guilt of excessive calorie load as 100 grams of bottle gourd only provides 14 calories of energy.";

		Product product = new Product();

//		name
		ProductName name = new ProductName();
		name.setEnglishName("Bottle Gourd");
		name.setTamilName("சுரைக்காய்");
		product.setName(name);

//		image
		product.setImageUrl("https://freeimghost.net/images/2023/03/01/battle_guard.jpeg");

//		category
		product.setCategory(ProductCategory.FRESH_VEGGIES);

		// description
		product.setDescription(desc);

//		nutritions
		ProductNutrition nutr = new ProductNutrition();
		nutr.setProteinNum(1);
		nutr.setCarbonNumb(25);
		nutr.setKcalNum(95);
		product.setNutrition(nutr);

//		available stock
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(25);
		stock.setUnit(ProductStockUnits.KG);
		product.setAvailableStock(stock);

//		quantites
		TreeSet<ProductQuantitiesCategory> set = new TreeSet<>();
		set.add(new ProductQuantitiesCategory(1, ProductStockUnits.KG, 150));
		set.add(new ProductQuantitiesCategory(350, ProductStockUnits.GM, 50));
		product.setQuantities(set);

//		status
		product.setStatus(ProductStatus.AVAILABLE);

		return product;
	}

//    test cases for valid

//	// test the insert product service with valid
//	@Test
//	@Order(1)
//	void testInsertProductValid() throws ServiceException {
//
//		try {
//
//			assertTrue(service.addProduct(getProduct()));
//
//		} catch (ServiceException e) {
//
//			fail(e);
//		}
//
//	}

	// test the read product by name with valid
	@Test
	@Order(2)
	void testReadProductByNameValid() {

		try {
//			Product product = service.readProductByName("Hemanath");
			Product product = service.readProductByName("மீடியம் மாதுளை");
			assertNotNull(product);
			logger.info(product);
		} catch (ServiceException e) {
			fail(e);
		}

	}

	// test the read all products by
	@Test
	@Order(3)
	void testReadAllProducts() {

		try {
			List<Product> productList = service.readAllProducts();

			assertFalse(productList.isEmpty());

			for (Product ele : productList) {

				logger.info(ele);
			}

		} catch (ServiceException e) {

			fail(e);
		}
	}

	// test the product update
	@Test
	@Order(4)
	void testUpdateProductServiceWithValid() {

		Product updateProduct = getProduct();
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(40);
		stock.setUnit(ProductStockUnits.KG);
		updateProduct.setAvailableStock(stock);

		try {

			assertTrue(service.updateProductById(2, updateProduct));
		} catch (ServiceException e) {
			fail(e);
		}

	}

	// test the product delete
	@Test
	@Order(5)
	void testDeleteProductById() {

		try {

			assertTrue(service.deleteProductById(9));
		} catch (ServiceException e) {

			fail(e);
		}
	}

	@Test
	void testReadProductByIdValid() {

		int id = 2;

		try {

			assertNotNull(service.readProductById(id));
		} catch (ServiceException e) {

			fail(e);
		}
	}

	@Test
	void testReadProductByIdInvalid() {

		int id = 20;

		try {

			service.readProductById(id);
		} catch (ServiceException e) {

			assertEquals("Product with ID " + id + " not found.", e.getMessage());
		}
	}

	@Test
	void testUpdateStatusWithValid() {

		int id = 11;
		String status = "AVAILABLE";

		try {

			assertTrue(service.updateProductStatus(status, id));
		} catch (ServiceException e) {

			fail(e);
		}

	}
//test cases end for valid

//    test cases start of invalid

	@Test
	void testAddProductWithNull() {

		try {

			service.addProduct(null);
			fail("Product null validation failed");
		} catch (ServiceException e) {

			assertEquals(ProductValidatorErrors.INVALID_PRODUCT_OBJ, e.getMessage());
		}
	}

	@Test
	void testAddProductExistsEngName() {

		String newEnglishName = "Pulicha Keerai";
		ProductName name = new ProductName();
		name.setEnglishName(newEnglishName);
		name.setTamilName("பச்சை ஆப்பிள்");

		Product newProduct = getProduct();
		newProduct.setName(name);

		try {

			service.addProduct(newProduct);
			fail("Add product already english name exsist validation failed");
		} catch (ServiceException e) {

			assertEquals("A product with English name " + newEnglishName + " already exists.", e.getMessage());
		}

	}

	@Test
	void testAddProductExistsTamName() {

		String newTamilName = "புளிச்ச கீரை";
		ProductName name = new ProductName();
		name.setEnglishName("Watermeleon");
		name.setTamilName(newTamilName);

		Product newProduct = getProduct();
		newProduct.setName(name);

		try {

			service.addProduct(newProduct);
			fail("Add product already english tamil name exsist validation failed");
		} catch (ServiceException e) {
			assertEquals("A product with Tamil name " + newTamilName + " already exists.", e.getMessage());
		}

	}

	// test the readbyname method with invalid product name
	@Test
	void testReadByNameInvalid() {
		String name = "Watermeleon";
		try {
			service.readProductByName(name);
			fail("Invaid product read by name failed.");
		} catch (ServiceException e) {
			assertEquals("Product not found with the name: " + name + ".", e.getMessage());
		}
	}

	// test the delete the method with invalid product it
	@Test
	void testDeleteProductInvalidId() {

		int id = -1;

		try {
			service.deleteProductById(id);
			fail("Product delete with invalid id failed");

		} catch (ServiceException e) {

			assertEquals("Product with ID " + id + " not found.", e.getMessage());
		}

	}

	@Test
	void testUpdateProductWithNUll() {

		try {

			service.updateProductById(2, null);
			fail("Update with null product failed");
		} catch (ServiceException e) {

			assertEquals(ProductValidatorErrors.INVALID_PRODUCT_OBJ, e.getMessage());
		}

	}

	@Test
	void testUpdateProductWithInvalidId() {

		int id = -1;

		try {

			service.updateProductById(id, getProduct());
			fail("Product validation with invalid id failed");
		} catch (ServiceException e) {

			assertEquals("Product with ID " + id + " not found.", e.getMessage());
		}

	}

	@Test
	void testUpdateStatusWithInvalidId() {

		int id = 25;
		String status = "AVAILABLE";

		try {

			service.updateProductStatus(status, id);
		} catch (ServiceException e) {

			assertEquals("Product with ID " + id + " not found.", e.getMessage());
		}

	}

	@Test
	void testUpdateStatusWithInvalidStatus() {

		int id = 6;
		String status = "PENDING";

		try {

			service.updateProductStatus(status, id);
		} catch (ServiceException e) {

			assertEquals(ProductDAOErrors.UPDATE_STATUS_ERROR, e.getMessage());
		}

	}
//    test caees end for invalid

}
