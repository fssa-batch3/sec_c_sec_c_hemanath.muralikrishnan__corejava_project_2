package com.fssa.agrokart.validator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.exception.*;

/**
 * A class which holds the both valid and invalid test cases for product model
 * object validator
 *
 * @author HemanathMuralikrishnan
 */
class TestProductValidator {

	// creating new instance of product validator
	ProductValidator validate = new ProductValidator();

//	creating a one product object to validate with the main validation

	public Product getProduct() {

		String desc = "Green apples are a boon to those who wish to shed the extra fat in their body. This fruit has low calories and rich in fiber that helps you to fight the hunger pangs when you have it early morning in an empty stomach. It is low in sodium, sugar, and fat that helps it to boost the process of calorie burning to achieve your weight loss goals much faster.";

		Product product = new Product();

//		name
		ProductName name = new ProductName();
		name.setEnglishName("Apple Green");
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
		nutr.setCarbonNumb(1);
		nutr.setKcalNum(95);
		product.setNutrition(nutr);

//		available stock
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(21);
		stock.setUnit(ProductStockUnits.KG);
		product.setAvailableStock(stock);

//		quantites
		TreeSet<ProductQuantitiesCategory> set = new TreeSet<>();
		set.add(new ProductQuantitiesCategory(1, ProductStockUnits.KG, 200));
		set.add(new ProductQuantitiesCategory(250, ProductStockUnits.GM, 100));
		product.setQuantities(set);

//		status
		product.setStatus(ProductStatus.AVAILABLE);

		return product;
	}

	// test the main method with valid input
	@Test
	void testMainMethodWithValid() {

		try {
			assertTrue(validate.validateProduct(getProduct()));
		} catch (InvalidInputException e) {
			fail(e);
		}

	}

	// test the main method with invalid input null
	@Test
	void testMainMethodWithInvalidNull() {

		Product product = null;

		try {
			validate.validateProduct(product);
		} catch (InvalidInputException e) {
			assertEquals(ProductValidatorErrors.INVALID_PRODUCT_OBJ, e.getMessage());
		}

	}

//	test starts for a name object

	// test the name object with valid input
	@Test
	void testValidateNameObj() {

		try {

			assertTrue(validate.validateNameObj(getProduct().getName()));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	// test the name object with null input
	@Test
	void testValidateNameObjWithNull() {

		try {
			validate.validateNameObj(null);

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_NAME_OBJ, e.getMessage());

		}
	}

	// test the english name with different valid inputs
	@Test
	void testValidateEngNameValid() {

		String[] validProductNames = { "Apple Green", "Avocado", "Blueberry", "Dragon Fruit", "Kiwi Green",
				"Pears Green", "Strawberry", "Broccoli", "Butter Beans", "Capsicum Red", "Celery", "Chinese Cabbage",
				"Country Cucumber", "Lettuce Green", "Leeks", "Lettuce Iceberg", "Red Cabbage", "Lettuce Red", "Turnip",
				"Capsicum Yellow", "Zucchini Green", "Zucchini yellow", "Apple Royal Gala", "Apple Shimla Big",
				"Apple Shimla Medium", "Baby Orange", "Banana Hill", "Coconut Flower", "Custard Apple", "Grapes Paneer",
				"Guava", "Mango Totapuri Raw", "Orange Australia", "Pomegranate Medium", "Sapota Paal",
				"Tender Coconut", "Watermelon Stripes", "Bajji Chilli", "Bottle Gourd", "Big Onion", "Brinjal Ujala",
				"Brinjal White", "Bullet Chilli", "Capsicum Green", "Cluster Beans", "Coconut Large", "Cauliflower",
				"Cucumber Sambar", "Potato Agra", "Ridge Gourd", "Sweet Corn", "Tomato Local", "Agathi Keerai",
				"Arai Keerai", "Banana Leaf", "Betel", "Curry Leaves", "Karisalanganni Keerai", "Manathakkali Keerai",
				"Mint Leaves", "Mudakathan Keerai", "Mulai Keerai", "Moringa Leaves", "Paruppu Keerai",
				"Pasalai Keerai", "Pirandai", "Pulicha Keerai", "Siru Keerai", "Thoothuvalai", "Vallarai",
				"Cheppan Kilangu", "Karunai Kilangu", "Pidi Karunai", "Sweet Potato", "Apple Indian" };

		for (String name : validProductNames) {

			try {
				assertTrue(validate.validateEnglishName(name));
			} catch (InvalidInputException e) {
				fail(e);
			}
		}

	}

//	test the english name with different null and empty inputs
	@Test
	void testValidateEngNameWithNull() {

		String[] invalidProductNames = { null, " ", "", "     " };

		for (String name : invalidProductNames) {

			try {

				validate.validateEnglishName(name);

				fail("Product name null validation failed");

			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_ENGLISH_NAME_NULL, e.getMessage());

			}
		}

	}

	// test the english name with different invalid inputs
	@Test
	void testValidateEngNameInvalid() {

		String[] invalidProductNames = { "123", "@#$%", "A!",
				"ProductNameWithNoSpacesButExceedsTheCharacterLimit1234567890", "Product Name with 1 number",
				"Product Name with @ symbol", "Invalid_product_name_with_underscores", "Product-Name-with-dashes",
				"Product Name with an extra long string that exceeds the maximum allowed characters",
				"Product Name with multiple    spaces", "Invalid_product_name_with_underscores",
				"Product-Name-with-dashes", "Product\tName\tWith\tTabs" };

		for (String name : invalidProductNames) {

			try {

				validate.validateEnglishName(name);

				fail("Product name regex pattern validation failed");

			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_ENGLISH_NAME_PATTERN, e.getMessage());

			}
		}

	}

	// test the tamil name with different valid inputs
	@Test
	void testValidateTamilNameValid() {

		String[] validProductNames = { "பச்சை ஆப்பிள்", "அவோகேடோ", "கிவி", "டிராகன் பழம்", "கிவி", "பச்சை பேரிக்காய்",
				"ஸ்ட்ராபெர்ரி", "பச்சை பூக்கோசு", "ப. பீன்ஸ்", "சிவப்பு குடை மிளகாய்", "செலரி", "சீன முட்டைக்கோஸ்",
				"நாட்டு வெள்ளரிக்காய்", "பச்சை லீட்டஸ் கீரை", "லீக்ஸ்", "ஐஸ்பெர்க் லீட்டஸ் கீ", "சிவப்பு கோஸ்",
				"சிவப்பு லீட்டஸ் கீரை", "டர்னிப்", "மஞ்சள் குடைமிளகாய்", "சீமை சுரைக்காய்", "சீமை சுரைக்காய்",
				"ஆப்பிள் ராயல் காலா", "சிம்லா ஆப்பிள்", "சிம்லா ஆப்பிள்", "மாண்டரின் ஆரஞ்சு", "மலை வாழைப்பழம்",
				"தென்னம்பூ", "சீத்தாப்பழம்", "பன்னீர் திராட்சை", "கொய்யா பழம்", "கிளிமூக்கு மாங்காய்",
				"ஆஸ்திரேலியா ஆரஞ்சு", "மீடியம் மாதுளை", "பால் சப்போட்டா", "இளநீர்", "வரி தர்பூசணி", "பஜ்ஜி மிளகாய்",
				"சுரைக்காய்", "பெரிய வெங்காயம்", "உஜாலா கத்தரிக்காய்", "வெள்ளை கத்தரிக்காய்", "குண்டு மிளகாய்",
				"பச்சை குடை மிளகாய்", "கொத்தவரங்காய்", "தேங்காய்", "காலிஃபிளவர்", "சாம்பார் வெள்ளரிக்கா",
				"ஆக்ரா உருளைக்கிழங்கு", "பீர்க்கங்காய்", "இனிப்பு சோளம்", "நாட்டு தக்காளி", "அகத்தி கீரை", "அரைக்கீரை",
				"வாழை இலை", "வெற்றிலை", "கறிவேப்பிலை", "கரிசலாங்கண்ணி கீரை", "மனத்தக்காளி கீரை", "புதினா",
				"முடக்கத்தான் கீரை", "முளைக்கீரை", "முருங்கைக்கீரை", "பருப்பு கீரை", "பசலைக்கீரை", "பிரண்டை",
				"புளிச்ச கீரை", "சிறுக்கீரை", "தூதுவளை", "வல்லாரை", "சேபங்கிழங்கு", "கருணை கிழங்கு", "பிடி கருணை",
				"சர்க்கரைவள்ளி", "ஆப்பிள் இந்தியன்" };

		for (String name : validProductNames) {

			try {

				assertTrue(validate.validateTamilName(name));
			} catch (InvalidInputException e) {

				fail(e);
			}

		}
	}

	// test the tamil with different null inputs
	@Test
	void testValidateTamilNameWithNull() {

		try {

			String[] invalidProductNames = { " ", "   ", "", null };

			for (String name : invalidProductNames) {

				validate.validateTamilName(name);

				fail("Product tamil name null validation failed.");

			}

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_TAMIL_NAME_NULL, e.getMessage());
		}
	}

	// test the tamil with different invalid inputs
	@Test
	void testValidateTamilNameInvalid() {

		String name = "பச்சை   .    பேரிக்காய்";

		try {

			validate.validateTamilName(name);

			fail("Product tamil name regex pattern validation failed.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_TAMIL_NAME_PATTERN, e.getMessage());
		}
	}

//	test ends for a name object

//	test starts for image url

//	test the image url with different valid inputs

	@Test
	void testImageUrlWithValid() {

		String[] validImageUrl = { "https://freeimghost.net/images/2023/03/01/apple-green.jpeg",
				"https://freeimghost.net/images/2023/03/01/avacado.png",
				"https://freeimghost.net/images/2023/03/01/blueberry.jpeg",
				"https://freeimghost.net/images/2023/03/01/dragon-fruit.png",
				"https://freeimghost.net/images/2023/03/01/kiwi-green.png",
				"https://freeimghost.net/images/2023/03/01/pears-green.png",
				"https://freeimghost.net/images/2023/03/01/strawbeery.jpeg",
				"https://freeimghost.net/images/2023/03/01/broccoli.png",
				"https://freeimghost.net/images/2023/03/01/butter-beans.png",
				"https://freeimghost.net/images/2023/03/01/capsicum-red.png",
				"https://freeimghost.net/images/2023/03/01/celery.png",
				"https://freeimghost.net/images/2023/03/01/chinesecabbage.png",
				"https://freeimghost.net/images/2023/03/01/countrycucumber.png",
				"https://freeimghost.net/images/2023/03/01/greenlettuce.png",
				"https://freeimghost.net/images/2023/03/01/leeks.png",
				"https://freeimghost.net/images/2023/03/01/lettuce-iceberg.jpeg",
				"https://freeimghost.net/images/2023/03/01/redcabbage.png",
				"https://freeimghost.net/images/2023/03/01/redlettuce.png",
				"https://freeimghost.net/images/2023/03/01/turnip.png",
				"https://freeimghost.net/images/2023/03/01/yellowcapsicum.png",
				"https://freeimghost.net/images/2023/03/01/Zucchini-Green.png",
				"https://freeimghost.net/images/2023/03/01/Zucchini-yellow.jpeg",
				"https://freeimghost.net/images/2023/03/01/apple-royal-gala.jpeg",
				"https://freeimghost.net/images/2023/03/01/apple-shimala-medium.png",
				"https://freeimghost.net/images/2023/03/01/apple-shimala.png",
				"https://freeimghost.net/images/2023/03/01/baby_orange.png",
				"https://freeimghost.net/images/2023/03/01/banna-hill.jpeg",
				"https://freeimghost.net/images/2023/03/01/cocunt_flower.png",
				"https://freeimghost.net/images/2023/03/01/custard-apple.png",
				"https://freeimghost.net/images/2023/03/01/grapess-panner.jpeg",
				"https://freeimghost.net/images/2023/03/01/guva.png",
				"https://freeimghost.net/images/2023/03/01/Mango-Totapuri-Raw.png",
				"https://freeimghost.net/images/2023/03/01/orange-austrialia.jpeg",
				"https://freeimghost.net/images/2023/03/01/Pomegranate-Medium.png",
				"https://freeimghost.net/images/2023/03/01/sapota.jpeg",
				"https://freeimghost.net/images/2023/03/01/tender-cocunt.jpeg",
				"https://freeimghost.net/images/2023/03/01/watermeleon.png",
				"https://freeimghost.net/images/2023/03/01/baji-chilli.jpeg",
				"https://freeimghost.net/images/2023/03/01/battle_guard.jpeg",
				"https://freeimghost.net/images/2023/03/01/big-onion.png",
				"https://freeimghost.net/images/2023/03/01/brinjal_ujala.jpeg",
				"https://freeimghost.net/images/2023/03/01/brinjal_white.png",
				"https://freeimghost.net/images/2023/03/01/bullent-chilli.jpeg",
				"https://freeimghost.net/images/2023/03/01/capsium-green.jpeg",
				"https://freeimghost.net/images/2023/03/01/cluster_beans.png",
				"https://freeimghost.net/images/2023/03/01/cocunt-large.png",
				"https://freeimghost.net/images/2023/03/01/couiflower.png",
				"https://freeimghost.net/images/2023/03/01/cucmber-sambaar.jpeg",
				"https://freeimghost.net/images/2023/03/01/potato.jpeg",
				"https://freeimghost.net/images/2023/03/01/ridge_guard.jpeg",
				"https://freeimghost.net/images/2023/03/01/sweet_corn.jpeg",
				"https://freeimghost.net/images/2023/03/01/tomato_local.png",
				"https://freeimghost.net/images/2023/03/01/agathi-keerai.png",
				"https://freeimghost.net/images/2023/03/01/ARAIKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/BANANALEAF.png",
				"https://freeimghost.net/images/2023/03/01/bettel.jpeg",
				"https://freeimghost.net/images/2023/03/01/CURRYLEAVES.png",
				"https://freeimghost.net/images/2023/03/01/Karisalanganni-Keerai.png",
				"https://freeimghost.net/images/2023/03/01/MANATHAKALLIKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/MINTLEAVES.png",
				"https://freeimghost.net/images/2023/03/01/mudakathan.png",
				"https://freeimghost.net/images/2023/03/01/MULAIKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/MURUNGAIKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/paruup-keerai.png",
				"https://freeimghost.net/images/2023/03/01/PASALAIKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/pirandai.png",
				"https://freeimghost.net/images/2023/03/01/pulichai-keerai.png",
				"https://freeimghost.net/images/2023/03/01/SIRUKEERAI.png",
				"https://freeimghost.net/images/2023/03/01/thoothuvalai.png",
				"https://freeimghost.net/images/2023/03/01/vallarai.png",
				"https://freeimghost.net/images/2023/03/01/chippan-kelangu.jpeg",
				"https://freeimghost.net/images/2023/03/01/karunai-kilangu.jpeg",
				"https://freeimghost.net/images/2023/03/01/pidikarunai.png",
				"https://freeimghost.net/images/2023/03/01/sweet-potato.jpeg",
				"https://freeimghost.net/images/2023/03/01/apple_india.png" };

		for (String url : validImageUrl) {

			try {

				assertTrue(validate.validateImageUrl(url));
			} catch (InvalidInputException e) {

				fail(e);
			}

		}

	}

//	test the image url with differen null inputs
	@Test
	void testImageUrlWithNullInputs() {

		String[] invalidImageUrls = { null, "   ", "" };

		for (String url : invalidImageUrls) {

			try {

				validate.validateImageUrl(url);

			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_IMAGE_URL_NULL, e.getMessage());

			}

		}

	}

//	test the image url with different invalid inputs

	@Test
	void testImageUrlWithInvalid() {

		String[] invalidImageUrls = { "http://example.com/image", "https://example.com/image.",
				"ftp://example.com/image.pdf", "htp://example.com/image.jpg", "http:/example.com/image.jpg",
				"http://example.com/image.png?param=value", "https://example.com/image;param=value",
				"file:///C:/path/to/image.jpg", "https://example.com/image.mp3", "ftp://example.com/image_png" };

		for (String url : invalidImageUrls) {

			try {

				validate.validateImageUrl(url);

			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_IMAGE_URL_PATTERN, e.getMessage());

			}

		}

	}

//	test ends for image url

//	test starts for product category

	@Test
	void testProductCategoryValid() {

		try {

			assertTrue(validate.validateCategory(ProductCategory.EXOTIC_FRUITS));
			assertTrue(validate.validateCategory(ProductCategory.EXOTIC_VEGGIES));
			assertTrue(validate.validateCategory(ProductCategory.FRESH_FRUITS));
			assertTrue(validate.validateCategory(ProductCategory.FRESH_VEGGIES));
			assertTrue(validate.validateCategory(ProductCategory.LEAFY_GREEN));
			assertTrue(validate.validateCategory(ProductCategory.TUBERS));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	@Test
	void testCategoryInvalid() {

		try {

			validate.validateCategory(null);

			fail("Product category null validation failed.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_CATEGORY, e.getMessage());
		}

	}

//	test end for product category

	// test starts for product description
	@Test
	void testValidateDescWithValid() {

		String[] validInputs = { "This is a valid input.", "Hello, world", "1234567890", "A simple sentence.",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu sapien in ipsum vulputate eleifend. Nulla convallis lorem justo, nec cursus nisi fermentum eget.", };

		for (String desc : validInputs) {

			try {

				assertTrue(validate.validateDescription(desc));

			} catch (InvalidInputException e) {

				fail(e);
			}

		}

	}

//	test the desc with different null inputs
	@Test
	void testValidateDescWithNullInputs() {

		String[] invalidDescriptions = { null, "     ", "          " };

		for (String desc : invalidDescriptions) {

			try {

				validate.validateDescription(desc);

				fail("Product description null validation failed.");
			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_DESCRIPTION_NULL, e.getMessage());
			}

		}
	}

	@Test
	void testValidateDescWithInvalid() {

		String[] invalidDescriptions = { "Short", "Invalid Description with special characters: ©" };

		for (String desc : invalidDescriptions) {

			try {

				validate.validateDescription(desc);

			} catch (InvalidInputException e) {

				assertEquals(ProductValidatorErrors.INVALID_DESCRIPTION_PATTERN, e.getMessage());
			}

		}

	}

//	test ends for product description

//	test starts for nutrition's

	// test the nutr with null object
	@Test
	void testValidateNutrWithNull() {

		ProductNutrition nutr = null;

		try {

			validate.validateNutrObj(nutr);

			fail("Nutr object null validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_NUTR_OBJ, e.getMessage());
		}

	}

	// test the nutr with a valid object
	@Test
	void testValidateNutrObj() {

		ProductNutrition nutr = new ProductNutrition(10, 15.5, 100);

		try {

			assertTrue(validate.validateNutrObj(nutr));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	// test the nutr with invalid numbs
	@Test
	void testValidateProteinNutrMin() {

		ProductNutrition nutr = new ProductNutrition(-1, 10, 10);

		try {

			validate.validateNutrObj(nutr);

			fail("Protein min validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_PROTEIN_NUTR, e.getMessage());
		}
	}

	@Test
	void testValidateProteinNutrMax() {

		ProductNutrition nutr = new ProductNutrition(20, 10, 10);

		try {

			validate.validateNutrObj(nutr);

			fail("Protein max validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_PROTEIN_NUTR, e.getMessage());
		}

	}

	@Test
	void testValidateCarboNutrMin() {

		ProductNutrition nutr = new ProductNutrition(10, -1, 10);

		try {

			validate.validateNutrObj(nutr);

			fail("Carbohydrates min validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_CARBOHYDRATES_NUTR, e.getMessage());
		}
	}

	@Test
	void testValidateCarboNutrMax() {

		ProductNutrition nutr = new ProductNutrition(10, 60, 100);

		try {

			validate.validateNutrObj(nutr);

			fail("Carbohydrates max validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_CARBOHYDRATES_NUTR, e.getMessage());
		}
	}

	@Test
	void testValidateKcalNutrMin() {

		ProductNutrition nutr = new ProductNutrition(10, 40, -5);

		try {

			validate.validateNutrObj(nutr);

			fail("Calories min validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_CALORIES_NUTR, e.getMessage());
		}

	}

	@Test
	void testValidateKcalNutrMax() {

		ProductNutrition nutr = new ProductNutrition(10, 40, 205);

		try {

			validate.validateNutrObj(nutr);

			fail("Calories max validation failed");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_CALORIES_NUTR, e.getMessage());
		}

	}

//	test ends for nutrition's

//	test starts of available stock

	// test the available stock with null input
	@Test
	void testValidateAvailObjWithNull() {

		ProductAvailableStock stock = null;

		try {
			validate.validateAvbllObj(stock);

			fail("Available stock null validation failed.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_OBJ, e.getMessage());
		}
	}

	// test the available stock with valid input
	@Test
	void testValidateAvailObj() {

//		ProductAvailableStock stock1 = new ProductAvailableStock(23, ProductStockUnits.KG);
		ProductAvailableStock stock2 = new ProductAvailableStock(24, ProductStockUnits.PKT);
//		ProductAvailableStock stock3 = new ProductAvailableStock(25, ProductStockUnits.NOS);

		try {

//			assertTrue(validate.validateAvbllObj(stock1));
			assertTrue(validate.validateAvbllObj(stock2));
//			assertTrue(validate.validateAvbllObj(stock3));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	// test the available stock with lesser the min stock
	@Test
	void testValidateAvailObjMinNum() {

		ProductAvailableStock stock1 = new ProductAvailableStock(18, ProductStockUnits.KG);

		try {

			validate.validateAvbllObj(stock1);

			fail("Available stock min num validation failed.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_NUM, e.getMessage());
		}

	}

	// test the available stock with invalid inputs
	@Test
	void testValidateAvailObjWithNullUnit() {

		ProductAvailableStock stock2 = new ProductAvailableStock(25, null);

		try {

			validate.validateAvbllObj(stock2);

			fail("Available stock null unit validation failed.");

		} catch (InvalidInputException e) {
			assertEquals(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_UNIT, e.getMessage());
		}

	}

	@Test
	void testValidateAvailObjWithDecForNos() {

		ProductAvailableStock stock1 = new ProductAvailableStock(22.5, ProductStockUnits.NOS);

		try {

			validate.validateAvbllObj(stock1);

			fail("Available stock NOS whole number validation failed.");

		} catch (InvalidInputException e) {
			assertEquals(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_PKT_NOS, e.getMessage());
		}

	}

	@Test
	void testValidateAvailObjWithDecForPkt() {

		ProductAvailableStock stock1 = new ProductAvailableStock(25.7, ProductStockUnits.PKT);

		try {

			validate.validateAvbllObj(stock1);

			fail("Available stock PKT whole number validation failed.");

		} catch (InvalidInputException e) {
			assertEquals(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_PKT_NOS, e.getMessage());
		}

	}

//	test ends for available stock 

//	test starts for product status

	// test status with valid inputs
	@Test
	void testValidateStatusWithValid() {

		try {

			assertTrue(validate.validateStatus(ProductStatus.AVAILABLE));
			assertTrue(validate.validateStatus(ProductStatus.NOT_AVAILABLE));
		} catch (InvalidInputException e) {
			fail(e);

		}

	}

	// test status with invalid inputs
	@Test
	void testValidateStatusWithInvalid() {

		try {

			validate.validateStatus(null);

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_STATUS, e.getMessage());

		}
	}
//	test ends for product status

//	test starts for product quantities

	// test the product quantities with valid inputs
	@Test
	void testValidateQtyObjWithValidKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.KG, 200));
		qty.add(new ProductQuantitiesCategory(250, ProductStockUnits.GM, 100));

		try {

			assertTrue(validate.validateQtyObj(qty, stock));
		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	@Test
	void testValidateQtyObjWithValidPkt() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.PKT, 100));

		try {

			assertTrue(validate.validateQtyObj(qty, stock));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	@Test
	void testValidateQtyObjWithValidNos() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.NOS, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 100));

		try {

			assertTrue(validate.validateQtyObj(qty, stock));

		} catch (InvalidInputException e) {

			fail(e);
		}

	}

	// test the product quantities with invalid inputs
	@Test
	void testValidateQtyObjWithNull() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);
		try {

			validate.validateQtyObj(null, stock);

			fail("Failed to validate qty cate with null object.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_CATE_OBJ, e.getMessage());
		}

		assertThrows(InvalidInputException.class, () -> validate.validateQtyObj(null, stock));
	}

//	test the qty cate with null
	@Test
	void testValidateQtyCateWithNull() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, null, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 100));

		try {

			validate.validateQtyObj(qty, stock);

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_CATE_UNIT, e.getMessage());
		}

	}

//	test the rs lesser than ten
//	test the qty cate with null
	@Test
	void testValidateQtyCateRsLesser() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.NOS, 5));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 100));

		try {

			validate.validateQtyObj(qty, stock);

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_CATE_AMOUNT, e.getMessage());
		}

	}

	@Test
	void testValidateQtyObjWithInvalidKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.KG, 100));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with invalid units for kg.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_FOR_KG, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithInvalidPkt() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 100));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with invalid units for pkt.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_FOR_PKT, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithInvalidNos() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.PKT, 100));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with invalid units for nos.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_FOR_NOS, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithMinKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(-1, ProductStockUnits.KG, 200));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.GM, 200));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with kg minus value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithMaxKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(26, ProductStockUnits.KG, 26));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.GM, 200));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with kg max value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithMingm() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantitiesCategory(90, ProductStockUnits.GM, 90));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with gm minus value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithMaxgm() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantitiesCategory(26000, ProductStockUnits.GM, 25000));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with gm max value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}

	}

	@Test
	void testValidateQtyObjWithMinNOS() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(-1, ProductStockUnits.NOS, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with nos min value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}
	}

	@Test
	void testValidateQtyObjWithMaxNOS() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(30, ProductStockUnits.NOS, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with nos max value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}
	}

	@Test
	void testValidateQtyObjWithDecNOS() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2.5, ProductStockUnits.NOS, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.NOS, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with dec for nos");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGTH_PKT_NOS, e.getMessage());

		}
	}

	@Test
	void testValidateQtyObjWithMinPKT() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(-1, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.PKT, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with pkt min value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}
	}

	@Test
	void testValidateQtyObjWithMaxPKT() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(30, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.PKT, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with pkt max value.");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT, e.getMessage());

		}
	}

	@Test
	void testValidateQtyObjWithDecPKT() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantitiesCategory> qty = new TreeSet<>();
		qty.add(new ProductQuantitiesCategory(2.5, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantitiesCategory(10, ProductStockUnits.PKT, 20));

		try {

			validate.validateQtyObj(qty, stock);

			fail("Failed to validate the qty cate with dec for nos");

		} catch (InvalidInputException e) {

			assertEquals(ProductValidatorErrors.INVALID_QUANTITY_WEIGTH_PKT_NOS, e.getMessage());

		}
	}
//	test ends for product quantities

}
