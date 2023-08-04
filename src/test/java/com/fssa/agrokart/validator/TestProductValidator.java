package com.fssa.agrokart.validator;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.exceptions.*;
import com.fssa.agrokart.enums.*;
 
public class TestProductValidator {
 
//	creating new instace of product validator
	ProductValidator val = new ProductValidator();

//	creating a one product object to validate with main validation

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
		ProductNutritions nutr = new ProductNutritions();
		nutr.setProteinNum(1);
		nutr.setCarboNum(1);
		nutr.setCarboNum(95);
		product.setNutritions(nutr);

//		available stock
		ProductAvailableStock stock = new ProductAvailableStock();
		stock.setNum(21);
		stock.setUnit(ProductStockUnits.KG);
		product.setAvailableStock(stock);

//		quantites
		TreeSet<ProductQuantites> set = new TreeSet<>();
		set.add(new ProductQuantites(1, ProductStockUnits.KG, 200));
		set.add(new ProductQuantites(250, ProductStockUnits.GM, 100));
		product.setQuantites(set);

//		status
		product.setStatus(ProductStatus.AVAILABLE);

//		creation date and time
		product.setCreationDate(LocalDate.now());
		product.setCreationTime(LocalTime.now());

//		update date and time
		product.setUpdatedDate(LocalDate.now());
		product.setUpdatedTime(LocalTime.now());

		return product;
	}

//	test the main method with valid input
	@Test
	void testMainMethodWithValid() {

		assertDoesNotThrow(() -> val.validateProduct(getProduct()));
	}

//	test the main method with invalid input null
	@Test
	void testMainMethodWithInvalidNull() {

		Product product = null;

		assertThrows(InvalidProductDataException.class, () -> val.validateProduct(product));
	}

//	test starts for name object

//	test the name object with valid input
	@Test
	void testValidateNameObj() {

		assertDoesNotThrow(() -> val.validateNameObj(getProduct().getName()));
	}

//	test the name object with null input
	@Test
	void testValidateNameObjWithNull() {

		assertThrows(InvalidProductDataException.class, () -> val.validateNameObj(null));
	}

//	test the english name with different valid inputs
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

			assertDoesNotThrow(() -> val.validateEnglishName(name));
		}

	}

//	test the english name with different invalid inputs
	@Test
	void testValidateEngNameInvalid() {

		String[] invalidProductNames = { null, "123", "@#$%", " ", "", "A!",
				"ProductNameWithNoSpacesButExceedsTheCharacterLimit1234567890", "Product Name with 1 number",
				"Product Name with @ symbol", "Invalid_product_name_with_underscores", "Product-Name-with-dashes",
				"     ", "Product Name with an extra long string that exceeds the maximum allowed characters",
				"Product Name with multiple    spaces", "Invalid_product_name_with_underscores",
				"Product-Name-with-dashes", "Name\nWith\nNew\nLines", "Product\tName\tWith\tTabs" };

		for (String name : invalidProductNames) {

			assertThrows(InvalidProductDataException.class, () -> val.validateEnglishName(name));
		}

	}

//	test the tamil name with different valid inputs
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

			assertDoesNotThrow(() -> val.validateTamilName(name));
		}
	}

//	test the tamil with different invalid inputs
	@Test
	void testValidateTamilNameInvalid() {

		String[] invalidProductNames = { "பச்சை   .    பேரிக்காய்", " ", "   ", "", null };

		for (String name : invalidProductNames) {

			assertThrows(InvalidProductDataException.class, () -> val.validateTamilName(name));

		}
	}

//	test ends for name object

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

			assertDoesNotThrow(() -> val.validateImageUrl(url));
		}

	}

//	test the image url with different invalid inputs

	@Test
	void testImageUrlWithInvalid() {

		String[] invalidImageUrls = { null, "        ", "http://example.com/image", "https://example.com/image.",
				"ftp://example.com/image.pdf", "htp://example.com/image.jpg", "http:/example.com/image.jpg",
				"http://example.com/image.png?param=value", "https://example.com/image;param=value",
				"file:///C:/path/to/image.jpg", "https://example.com/image.mp3", "ftp://example.com/image_png" };

		for (String url : invalidImageUrls) {

			assertThrows(InvalidProductDataException.class, () -> val.validateImageUrl(url));

		}

	}

//	test ends for image url

//	test starts for product category

	@Test
	void testProductCategoryValid() {

		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.EXOTIC_FRUITS));
		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.EXOTIC_VEGGIES));
		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.FRESH_FRUITS));
		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.FRESH_VEGGIES));
		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.LEAFY_GREEN));
		assertDoesNotThrow(() -> val.validateCategory(ProductCategory.TUBERS));
	}

	@Test
	void testCategoryInvalid() {

		assertThrows(InvalidProductDataException.class, () -> val.validateCategory(null));
	}

//	test end for product category

//	test starts for product description
	@Test
	void testValidateDescWithValid() {

		String[] validInputs = { "This is a valid input.", "Hello, world!", "1234567890", "A simple sentence.",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu sapien in ipsum vulputate eleifend. Nulla convallis lorem justo, nec cursus nisi fermentum eget.",
				"Line 1\nLine 2", "The quick brown fox jumps\nover the lazy dog." };

		for (String desc : validInputs) {

			assertDoesNotThrow(() -> val.validateDescription(desc));
		}

	}

	@Test
	void testValidateDescWithInvalid() {

		String[] invalidDescriptions = { null, "     ", "Short", "          ",
				"Invalid Description with special characters: ©" };

		for (String desc : invalidDescriptions) {

			assertThrows(InvalidProductDataException.class, () -> val.validateDescription(desc));
		}

	}

//	test ends for product description

//	test starts for nutritions

//	test the nutr with null object
	@Test
	void testValidateNutrWithNull() {

		ProductNutritions nutr = null;

		assertThrows(InvalidProductDataException.class, () -> val.validateNutrObj(nutr));
	}

//	test the nutr with valid object
	@Test
	void testValidateNutrObj() {

		ProductNutritions nutr = new ProductNutritions(20, 15.5, 100);

		assertDoesNotThrow(() -> val.validateNutrObj(nutr));

	}

//	test the nutr with invalid nums
	@Test
	void testValidateNutrInvalid() {

		ProductNutritions nutr = new ProductNutritions(-1, -2, 0);

		assertThrows(InvalidProductDataException.class, () -> val.validateNutrObj(nutr));
	}

//	test ends for nutritions

//	test starts of available stock

//	test the available stock with null input
	@Test
	void testValidateAvailObjWithNull() {

		ProductAvailableStock stock = null;

		assertThrows(InvalidProductDataException.class, () -> val.validateAvailObj(stock));
	}

//	test the available stock with valid input
	@Test
	void testValidateAvailObj() {

		ProductAvailableStock stock1 = new ProductAvailableStock(25, ProductStockUnits.KG);
		ProductAvailableStock stock2 = new ProductAvailableStock(25, ProductStockUnits.PKT);
		ProductAvailableStock stock3 = new ProductAvailableStock(25, ProductStockUnits.NOS);

		assertDoesNotThrow(() -> val.validateAvailObj(stock1));
		assertDoesNotThrow(() -> val.validateAvailObj(stock2));
		assertDoesNotThrow(() -> val.validateAvailObj(stock3));

	}

//	test the available stock with invalid inputs
	@Test
	void testValidateAvailObjWithInvalid() {

		ProductAvailableStock stock1 = new ProductAvailableStock(18, ProductStockUnits.KG);
		ProductAvailableStock stock2 = new ProductAvailableStock(25, null);

		assertThrows(InvalidProductDataException.class, () -> val.validateAvailObj(stock1));
		assertThrows(InvalidProductDataException.class, () -> val.validateAvailObj(stock2));
	}

//	test ends for available stock

//	test starts for product status

//	test status with valid inputs
	@Test
	void testValidateStatusWithValid() {

		assertDoesNotThrow(() -> val.validateStatus(ProductStatus.AVAILABLE));
		assertDoesNotThrow(() -> val.validateStatus(ProductStatus.NOT_AVAILABLE));

	}

//	test status with invalid inputs
	@Test
	void testValidateStatusWithInvalid() {

		assertThrows(InvalidProductDataException.class, () -> val.validateStatus(null));
	}
//	test ends for product status

//	test starts for created date and time

//	test with valid created date
	@Test
	void testValidateCreatedDateValid() {

		assertDoesNotThrow(() -> val.validateCreatedDate(LocalDate.now()));
	}

//	test with valid created time
	@Test
	void testValidateCreatedTimeValid() {

		assertDoesNotThrow(() -> val.validateCreatedTime(LocalTime.now()));
	}

//	test the created date invalid inputs
	@Test
	void testValidateCreatedDateInvalid() {

		LocalDate[] dates = { null, LocalDate.of(2023, 8, 31), LocalDate.now().plusDays(1),
				LocalDate.of(2022, 11, 04) };

		for (LocalDate date : dates) {

			assertThrows(InvalidProductDataException.class, () -> val.validateCreatedDate(date));
		}
	}

//	test the created time with invalid inputs
	@Test
	void testValidateCreatedTimeInvalid() {

		assertThrows(InvalidProductDataException.class, () -> val.validateCreatedTime(null));

	}

//	test end for created date and time

//	test starts for update date and time

//	test the updated date with valid inputs
	@Test
	void testValidateUpdateDateValid() {

		LocalDate[] dates = { LocalDate.now(), LocalDate.of(2023, 8, 3), LocalDate.of(2023, 8, 4) };
		LocalDate createdDate = LocalDate.now();

		for (LocalDate date : dates) {

			assertDoesNotThrow(() -> val.validateUpdatedDate(date, createdDate));
		}
	}

//	test the updated time with valid inputs
	@Test
	void testValidateUpdateTimeValid() {

		assertDoesNotThrow(
				() -> val.validateUpdatedTime(LocalTime.now(), LocalTime.now(), LocalDate.now(), LocalDate.now()));
	}

//	test the updated date with invalid inputs
	@Test
	void testValidateUpdateDateInvalid() {

		assertThrows(InvalidProductDataException.class,
				() -> val.validateUpdatedDate(LocalDate.now().minusDays(1), LocalDate.now()));
	}

	@Test
	void testValidateUpdateDateNull() {

		assertThrows(InvalidProductDataException.class, () -> val.validateUpdatedDate(null, LocalDate.now()));
	}

//	test the updated time with invalid inputs
	@Test
	void testValidateUpdateTimeNull() {

		assertThrows(InvalidProductDataException.class,
				() -> val.validateUpdatedTime(null, LocalTime.now(), LocalDate.now(), LocalDate.now()));
	}

	@Test
	void testValidateUpdateTInvalid() {

		assertThrows(InvalidProductDataException.class, () -> val.validateUpdatedTime(LocalTime.now().minusHours(2),
				LocalTime.now(), LocalDate.now(), LocalDate.now()));

	}

//	test ends for updated date and time

//	test starts for product quantities

//	test the product quantities with valid inputs
	@Test
	void testValidateQtyObjWithValidKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.KG, 200));
		qty.add(new ProductQuantites(250, ProductStockUnits.GM, 100));

		assertDoesNotThrow(() -> val.validateQtyObj(qty, stock));

	}

	@Test
	void testValidateQtyObjWithValidPkt() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantites(10, ProductStockUnits.PKT, 100));

		assertDoesNotThrow(() -> val.validateQtyObj(qty, stock));

	}

	@Test
	void testValidateQtyObjWithValidNos() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.NOS, 20));
		qty.add(new ProductQuantites(10, ProductStockUnits.NOS, 100));

		assertDoesNotThrow(() -> val.validateQtyObj(qty, stock));

	}

//	test the product quantities with invalid inputs
	@Test
	void testValidateQtyObjWithNull() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		assertThrows(InvalidProductDataException.class, () -> val.validateQtyObj(null, stock));
	}

	@Test
	void testValidateQtyObjWithInvalidKg() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.KG);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.PKT, 20));
		qty.add(new ProductQuantites(10, ProductStockUnits.KG, 100));

		assertThrows(InvalidProductDataException.class, () -> val.validateQtyObj(qty, stock));

	}

	@Test
	void testValidateQtyObjWithInvalidPkt() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.PKT);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantites(10, ProductStockUnits.NOS, 100));

		assertThrows(InvalidProductDataException.class, () -> val.validateQtyObj(qty, stock));

	}

	@Test
	void testValidateQtyObjWithInvalidNos() {

		ProductAvailableStock stock = new ProductAvailableStock(25, ProductStockUnits.NOS);

		TreeSet<ProductQuantites> qty = new TreeSet<>();
		qty.add(new ProductQuantites(2, ProductStockUnits.KG, 20));
		qty.add(new ProductQuantites(10, ProductStockUnits.PKT, 100));

		assertThrows(InvalidProductDataException.class, () -> val.validateQtyObj(qty, stock));

	}
//	test ends for proudct quantities

}
