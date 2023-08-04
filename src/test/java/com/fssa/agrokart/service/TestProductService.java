package com.fssa.agrokart.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.TreeSet;
import com.fssa.agrokart.enums.ProductCategory;
import com.fssa.agrokart.enums.ProductStatus;
import com.fssa.agrokart.enums.ProductStockUnits;
import com.fssa.agrokart.exceptions.*;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductAvailableStock;
import com.fssa.agrokart.model.ProductName;
import com.fssa.agrokart.model.ProductNutritions;
import com.fssa.agrokart.model.ProductQuantites;
import com.fssa.agrokart.util.*;

public class TestProductService {

//	create instance of product service class
	ProductService service = new ProductService();

//	logger class to print the products
	Logger logger = new Logger();

//	creating new product 
	public Product getProduct() {

		String desc = "Blueberries contain vitamins, minerals, and antioxidants that provide notable health benefits. For example, blueberries are rich in vitamin K, which plays an important role in promoting heart health. The vitamin is also important to bone health and blood clotting.";

		Product product = new Product();

//		name
		ProductName name = new ProductName();
		name.setEnglishName("John Wick");
		name.setTamilName("கிவி");
		product.setName(name);

//		image
		product.setImageUrl("https://freeimghost.net/images/2023/03/01/blueberry.jpeg");

//		category
		product.setCategory(ProductCategory.EXOTIC_FRUITS);

		// description
		product.setDescription(desc);

//		nutritions
		ProductNutritions nutr = new ProductNutritions();
		nutr.setProteinNum(17);
		nutr.setCarboNum(11);
		nutr.setCarboNum(42);
		product.setNutritions(nutr);

//		available stock
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(50);
		stock.setUnit(ProductStockUnits.KG);
		product.setAvailableStock(stock);

//		quantites
		TreeSet<ProductQuantites> set = new TreeSet<>();
		set.add(new ProductQuantites(5, ProductStockUnits.KG, 150));
//		set.add(new ProductQuantites(400, ProductStockUnits.GM, 75));
		product.setQuantites(set);

//		status
		product.setStatus(ProductStatus.NOT_AVAILABLE);

//		creation date and time
		product.setCreationDate(LocalDate.now());
		product.setCreationTime(LocalTime.now());

//		update date and time
		product.setUpdatedDate(LocalDate.now());
		product.setUpdatedTime(LocalTime.now());

		return product;
	}

//	test the insert product service with valid
	@Test
	void testInsertProductValid() {

		assertDoesNotThrow(() -> service.addProduct(getProduct()));
	}

//	test the read product by name with valid
	@Test
	void testReadProductByNameValid() {

		logger.info(assertDoesNotThrow(() -> service.readProductByName("Blueberry")));

	} 

//	test the read all products by 
	@Test
	void testReadAllProducts() {

		try {
			List<Product> productList = service.readAllProducts();

			assertTrue(productList.size() > 0);

			for (Product ele : productList) {

				logger.info(ele);
			}

		} catch (ProductDAOException e) {
			e.printStackTrace();
		}
	}

//	test the product delete
	@Test
	void testDeleteProductById() {

		assertDoesNotThrow(() -> service.deleteProductById(1));
	}

//	test the product update
	@Test
	void testUpdateProductServiceWithValid() {

		assertDoesNotThrow(() -> service.updateProductById(1, getProduct()));
	}

}
