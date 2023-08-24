package com.fssa.agrokart.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.TreeSet;

import com.fssa.agrokart.enums.ProductCategory;
import com.fssa.agrokart.enums.ProductStatus;
import com.fssa.agrokart.enums.ProductStockUnits;
import com.fssa.agrokart.exception.*;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductAvailableStock;
import com.fssa.agrokart.model.ProductName;
import com.fssa.agrokart.model.ProductNutrition;
import com.fssa.agrokart.model.ProductQuantitiesCate;
import com.fssa.agrokart.util.*;

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

		String desc = "Green apples are a boon to those who wish to shed the extra fat in their body. This fruit has low calories and rich in fiber that helps you to fight the hunger pangs when you have it early morning in an empty stomach. It is low in sodium, sugar, and fat that helps it to boost the process of calorie burning to achieve your weight loss goals much faster.";

		Product product = new Product();

//		name
		ProductName name = new ProductName();
		name.setEnglishName("Hemanath");
		name.setTamilName("பச்சை ஆப்பிள்");
		product.setName(name);

//		image
		product.setImageUrl("https://freeimghost.net/images/2023/03/01/apple-green.jpeg");

//		category
		product.setCategory(ProductCategory.EXOTIC_FRUITS);

		// description
		product.setDescription(desc);

//		nutritions
		ProductNutrition nutr = new ProductNutrition();
		nutr.setProteinNum(1);
		nutr.setCarbonNumb(25);
		nutr.setCarbonNumb(95);
		product.setNutrition(nutr);

//		available stock
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(25);
		stock.setUnit(ProductStockUnits.KG);
		product.setAvailableStock(stock);

//		quantites
		TreeSet<ProductQuantitiesCate> set = new TreeSet<>();
		set.add(new ProductQuantitiesCate(1, ProductStockUnits.KG, 150));
		set.add(new ProductQuantitiesCate(350, ProductStockUnits.GM, 50));
		product.setQuantities(set);

//		status
		product.setStatus(ProductStatus.NOT_AVAILABLE);

		return product;
	}

//    test cases for valid

	// test the insert product service with valid
	@Test
	void testInsertProductValid() {

		assertDoesNotThrow(() -> service.addProduct(getProduct()));
	}

	// test the read product by name with valid
	@Test
	void testReadProductByNameValid() {

		logger.info(assertDoesNotThrow(() -> service.readProductByName("Apple Green")));

	}

	// test the read all products by
	@Test
	void testReadAllProducts() {

		try {
			List<Product> productList = service.readAllProducts();

			assertFalse(productList.isEmpty());

			for (Product ele : productList) {

				logger.info(ele);
			}

		} catch (DAOException e) {

			fail("Read all products failed");
		}
	}

	// test the product update
	@Test
	void testUpdateProductServiceWithValid() {

		assertDoesNotThrow(() -> service.updateProductById(1, getProduct()));
	}

	// test the product delete
	@Test
	void testDeleteProductById() {

		assertDoesNotThrow(() -> service.deleteProductById(1));
	}
//test cases end for valid

//    test cases start of invalid

	// test the insert product method with null product
	@Test
	void testInsertProductWithNUll() {

		assertThrows(InvalidInputException.class, () -> service.addProduct(null));
	}

	// test the readbyname method with invalid product name
	@Test
	void testReadByNameInvalid() {
		try {
			assertNull(service.readProductByName("Hemanath"));
		} catch (DAOException e) {
			fail("Read name by Invalid test cases failed");
		}
	}

	// test the delete the method with invalid product it
	@Test
	void testDeleteProductInvalidId() {

		assertThrows(DAOException.class, () -> service.deleteProductById(-1));
	}

	@Test
	void testUpdateProductWithNUll() {

		assertThrows(InvalidInputException.class, () -> service.updateProductById(2, null));
	}

//    test caees end for invalid

}
