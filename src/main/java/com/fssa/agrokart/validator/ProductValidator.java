/**
 * This class provides validation methods for various fields and objects related to the Product model.
 * It ensures that the data entered for a product is valid and follows certain rules and patterns.
 *
 * @author HemanathMuralikrishnan
 */
package com.fssa.agrokart.validator;

import java.util.SortedSet;

import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.model.ProductRegexPatterns;
import com.fssa.agrokart.util.StringUtil;

/**
 * A validator class which holds various validation methods for a Product object
 * and its fields.
 */

public class ProductValidator {

	StringUtil stringUtil = new StringUtil();

	
	/**
	 * Validates that an object is not null.
	 *
	 * @param obj     The object to validate.
	 * @param message The error message to throw if the object is null.
	 * @return True if the object is not null.
	 * @throws InvalidInputException If the object is null.
	 */
	public boolean validateObject(Object obj, String message) throws InvalidInputException {

		if (obj == null)
			throw new InvalidInputException(message);

		return true;
	}

//	main validation starts for product

	/**
	 * Validates a Product object.
	 *
	 * @param product The Product object to validate.
	 * @return True if the Product is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateProduct(Product product) throws InvalidInputException {

//		validate the product object is not equal to null
		validateObject(product, ProductValidatorErrors.INVALID_PRODUCT_OBJ);

//		validate the product name
		validateNameObj(product.getName());

//		validate the product image url
		validateImageUrl(product.getImageUrl());

//		validate the product description
		validateDescription(product.getDescription());

//		validate the product nutrition's
		validateNutrObj(product.getNutrition());

//		validate the product available stock
		validateAvbllObj(product.getAvailableStock());

//		validate the product quantities
		validateQtyObj(product.getQuantities(), product.getAvailableStock());

//		if there is no exception, then return true
		return true;

	}
 
//	main validation ends for product

//	validation start for a product name object

	/**
	 * Validates the name object of a Product.
	 *
	 * @param name The ProductName object to validate.
	 * @return True if the ProductName object is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateNameObj(ProductName name) throws InvalidInputException {

//		validate with object validator
		validateObject(name, ProductValidatorErrors.INVALID_NAME_OBJ);

//		send the English name to english name validator
		validateEnglishName(name.getEnglishName());

//		send the tamil name to tamil name validator
		validateTamilName(name.getTamilName());

//		if there is no exception, then return true
		return true;

//		
	}

	// Method to validate the product english name

	/**
	 * Validates the English name of a Product.
	 *
	 * @param engName The English name to validate.
	 * @return True if the English name is valid.
	 * @throws InvalidInputException If the English name is null or does not match
	 *                               the required pattern.
	 */
	public boolean validateEnglishName(String engName) throws InvalidInputException {

		String trimmedInput = stringUtil.trimString(engName);

		if (trimmedInput == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_ENGLISH_NAME_NULL);

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.ENGLISH_NAME);

		if (!isMatch)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_ENGLISH_NAME_PATTERN);

//		if there is no exception, then return true
		return true;

	}

	// Method validate teh product tamil name

	/**
	 * Validates the Tamil name of a Product.
	 *
	 * @param tamName The Tamil name to validate.
	 * @return True if the Tamil name is valid.
	 * @throws InvalidInputException If the Tamil name is null or does not match the
	 *                               required pattern.
	 */
	public boolean validateTamilName(String tamName) throws InvalidInputException {

		String trimmedInput = stringUtil.trimString(tamName);

		if (trimmedInput == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_TAMIL_NAME_NULL);

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.TAMIL_NAME);

		if (!isMatch)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_TAMIL_NAME_PATTERN);

//		if there is no exception, then return true
		return true;

	}

//	validation end for a product name object

//	validation starts for product image url

	/**
	 * Validates the image URL of a Product.
	 *
	 * @param imgUrl The image URL to validate.
	 * @return True if the image URL is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateImageUrl(String imgUrl) throws InvalidInputException {

		String trimmedInput = stringUtil.trimString(imgUrl);

		if (trimmedInput == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_IMAGE_URL_NULL);

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.IMAGE_URL);

		if (!isMatch)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_IMAGE_URL_PATTERN);

//		if there is no exception, then return true
		return true;
	}

//	validation end for product image url

	// validate the product category

	/**
	 * Validates the product category of a Product.
	 *
	 * @param cat The ProductCategory to validate.
	 * @return True if the category is valid.
	 * @throws InvalidInputException If the category is null.
	 */
	public boolean validateCategory(ProductCategory cat) throws InvalidInputException {

//		validate the category is not equal to null
		if (cat == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_CATEGORY);

//		if the category is not equal to null
		return true;
	}

//	validation starts for product description

	// Method to validate the product description

	/**
	 * Validates the description of a Product.
	 *
	 * @param desc The description to validate.
	 * @return True if the description is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateDescription(String desc) throws InvalidInputException {

		String trimmedInput = stringUtil.trimString(desc);

		if (trimmedInput == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_DESCRIPTION_NULL);

		boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.DESCRIPTION);

		if (!isMatch)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_DESCRIPTION_PATTERN);

//		if there is no exception, then return true
		return true;
	}

//	validation end for product description

//	validation starts for product nutrition's

	// Method to validate the product nutrition's

	/**
	 * Validates the nutrition object of a Product.
	 *
	 * @param nutr The ProductNutrition object to validate.
	 * @return True if the ProductNutrition object is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateNutrObj(ProductNutrition nutr) throws InvalidInputException {

//		validate the object is not equal to null
		validateObject(nutr, ProductValidatorErrors.INVALID_NUTR_OBJ);

//		validate the protein num
		validateProteinNutr(nutr.getProteinNum());

//		validate the carbo num
		validateCarboHydratesNutr(nutr.getCarbonNumb());

//		validate the calories
		validateCaloriesNutr(nutr.getKcalNum());

//		if there is no exception, then return true
		return true;

	}

	// validation for product protein nutr

	/**
	 * Validates the protein content in the nutrition of a Product.
	 *
	 * @param num The protein value to validate.
	 * @return True if the protein value is within valid range.
	 * @throws InvalidInputException If the protein value is out of range.
	 */
	public boolean validateProteinNutr(double num) throws InvalidInputException {

		if (num < ProductConstants.MINIMUM_PROTEIN_VALUE || num > ProductConstants.MAXIMUM_PROTEIN_VALUE) {

			throw new InvalidInputException((ProductValidatorErrors.INVALID_PROTEIN_NUTR));
		}

		return true;
	}

	// validate the product carbohydrates

	/**
	 * Validates the carbohydrate content in the nutrition of a Product.
	 *
	 * @param num The carbohydrate value to validate.
	 * @return True if the carbohydrate value is within valid range.
	 * @throws InvalidInputException If the carbohydrate value is out of range.
	 */
	public boolean validateCarboHydratesNutr(double num) throws InvalidInputException {

		if (num < ProductConstants.MINIMUM_CARBOHYDRATES_VALUE || num > ProductConstants.MAXIMUM_CARBOHYDRATES_VALUE) {

			throw new InvalidInputException(ProductValidatorErrors.INVALID_CARBOHYDRATES_NUTR);
		}

		return true;
	}

	// validate the product calories

	/**
	 * Validates the calorie content in the nutrition of a Product.
	 *
	 * @param num The calorie value to validate.
	 * @return True if the calorie value is within valid range.
	 * @throws InvalidInputException If the calorie value is out of range.
	 */
	public boolean validateCaloriesNutr(double num) throws InvalidInputException {

		if (num < ProductConstants.MINIMUM_CALORIES_VALUE || num > ProductConstants.MAXIMUM_CALORIES_VALUE) {

			throw new InvalidInputException(ProductValidatorErrors.INVALID_CALORIES_NUTR);
		}

		return true;
	}

//	validation end for product nutrition's

	/**
	 * Checks if a number is a whole number (integer).
	 *
	 * @param number The number to check.
	 * @return True if the number is a whole number.
	 */
	public boolean isWholeNumber(double number) {
		return Math.floor(number) == number;
	}

//	validation starts for product available stock

	// Method to validate the available stock object

	/**
	 * Validates the available stock object of a Product.
	 *
	 * @param avail The ProductAvailableStock object to validate.
	 * @return True if the ProductAvailableStock object is valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateAvbllObj(ProductAvailableStock avail) throws InvalidInputException {

//		validate the avail stock obj
		validateObject(avail, ProductValidatorErrors.INVALID_AVAILABLE_STOCK_OBJ);

//		validate the available stock num
		validateAvblStock(avail.getNum(), avail.getUnit());

//		if there is no exception, then return true
		return true;
	}

	// Method to validate the weight num

	/**
	 * Validates the available stock number and unit of a Product.
	 *
	 * @param num               The available stock number to validate.
	 * @param productStockUnits The unit of the available stock to validate.
	 * @return True if the available stock number and unit are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateAvblStock(double num, ProductStockUnits productStockUnits) throws InvalidInputException {

//		check the stock unit not equal to null
		if (productStockUnits == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_UNIT);

		if (productStockUnits.toString().equalsIgnoreCase("pkt")
				|| productStockUnits.toString().equalsIgnoreCase("nos")) {

			if (!isWholeNumber(num)) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_PKT_NOS);
			}
		}

//		if the num is lesser than zero or lesser than 20, then throw an exception
		if (num <= ProductConstants.MINIMUM_AVAILABLE_STOCK_QUANTITY)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_NUM);

//		if there is no exception, then return true
		return true;
	}

//	validation ends for product available stock

	// validate the status start

	/**
	 * Validates the status of a Product.
	 *
	 * @param status The ProductStatus to validate.
	 * @return True if the status is valid.
	 * @throws InvalidInputException If the status is null.
	 */
	public boolean validateStatus(ProductStatus status) throws InvalidInputException {

		if (status == null)
			throw new InvalidInputException(ProductValidatorErrors.INVALID_STATUS);

		return true;
	}

//	validation starts for product quantities

	// Method to validate the rupees, unit,quantity

	/**
	 * Validates the quantities object of a Product.
	 *
	 * @param qty   The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @param stock The ProductAvailableStock object associated with the quantities.
	 * @return True if the quantities are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyObj(SortedSet<ProductQuantitiesCategory> qty, ProductAvailableStock stock)
			throws InvalidInputException {

//	check list minimum one qty is added or not
		if (qty == null || qty.isEmpty())
			throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_OBJ);

//		validate the qty object is not equal to null
		for (ProductQuantitiesCategory ele : qty) {

			validateObject(ele, ProductValidatorErrors.INVALID_QUANTITY_CATE_OBJ);
		}

//		if there is no exception, then send the qty list to another method to validate the units,rs,weight
		validateQtyEle(qty, stock);

//		if there is no exception, then return true
		return true;
	}

//	validate the status end

	// Method to validate the unit,rs,weight

	/**
	 * Validates the weights and units of the quantities of a Product.
	 *
	 * @param qty   The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @param stock The ProductAvailableStock object associated with the quantities.
	 * @return True if the quantities' weights and units are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyEle(SortedSet<ProductQuantitiesCategory> qty, ProductAvailableStock stock)
			throws InvalidInputException {

//		check the qty units is not equal to null
		for (ProductQuantitiesCategory ele : qty) {

			if (ele.getUnit() == null) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_UNIT);
			}
		}

//		get the available stock unit

		String stockUnit = stock.getUnit().toString().toLowerCase();

//		and also validate the weight and rs numbs

//		if the selected available stock is in kg,
//		then user can only add kg and gram quantities

		if (stockUnit.equals(ProductStockUnits.KG.toString().toLowerCase())) {

			validateQtyListKg(qty);
			validateQtyWeightKg(qty, stock);
		}

//		if the selected available stock is in pkt,
//		then user can only add pkt quantities

		if (stockUnit.equals(ProductStockUnits.PKT.toString().toLowerCase())) {

			validateQtyListPkt(qty);
			validateQtyWeightPkt(qty, stock);
		}

//		if the select available stock is in nos,
//		then user can only add the no quantities

		if (stockUnit.equals(ProductStockUnits.NOS.toString().toLowerCase())) {

			validateQtyListNos(qty);
			validateQtyWeightNos(qty, stock);
		}

		validateQtyRs(qty);

//		if there is no exception, then return true
		return true;

	}

	// Method to validate the rs

	/**
	 * Validates the rupees (Rs) value of quantities for a Product.
	 *
	 * @param qty The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @return True if the rupees values of the quantities are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyRs(SortedSet<ProductQuantitiesCategory> qty) throws InvalidInputException {

//		iterate through the for each loop
		for (ProductQuantitiesCategory ele : qty) {

//			if any product amount is lesser than 10 rs thrown an exception
			if (ele.getRs() < ProductConstants.MINIMUM_AMOUNT) {
				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_AMOUNT);
			}
		}

//		if there is no exception, then return true
		return true;
	}

	// Method to validate the list of quantities if the available stock is kg

	/**
	 * Validates the unit, rs, and weight values of quantities for a Product with
	 * available stock in kilograms.
	 *
	 * @param qty The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @return True if the quantities' unit and weight values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyListKg(SortedSet<ProductQuantitiesCategory> qty) throws InvalidInputException {

//		if they selected the available stock is kg, then the quantities must be kg or gm
		String unitKG = ProductStockUnits.KG.toString().toLowerCase();
		String unitGM = ProductStockUnits.GM.toString().toLowerCase();

		for (ProductQuantitiesCategory ele : qty) {

			String qtyUnit = ele.getUnit().toString().toLowerCase();

			if (!qtyUnit.equals(unitKG) && !qtyUnit.equals(unitGM)) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_KG);
			}

		}
//		if there is no exception, then return true
		return true;
	}

	// validate the weights of the set

	/**
	 * Validates the weights of quantities for a Product with available stock in
	 * kilograms.
	 *
	 * @param qty   The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @param stock The ProductAvailableStock object associated with the quantities.
	 * @return True if the quantities' weight values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyWeightKg(SortedSet<ProductQuantitiesCategory> qty, ProductAvailableStock stock)
			throws InvalidInputException {

		double weight = stock.getNum() * 1000;

//        validate the weight of the quantity cate
		for (ProductQuantitiesCategory ele : qty) {

			if (ele.getUnit().toString().toLowerCase().equals("kg")) {

				if (ele.getWeight() * 1000 > weight || ele.getWeight() < ProductConstants.MINIMUM_WEIGHT_FOR_KG) {

					throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT);

				}
			} else if (ele.getWeight() > weight || ele.getWeight() < ProductConstants.MINIMUM_WEIGHT_FOR_GRAM) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT);
			}
		}

//        return true if there is no exception
		return true;
	}

	// Method to validate the list of quantities if the available stock is pkt

	/**
	 * Validates the unit values of quantities for a Product with available stock in
	 * packets.
	 *
	 * @param qty The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @return True if the quantities' unit values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyListPkt(SortedSet<ProductQuantitiesCategory> qty) throws InvalidInputException {

//		if they selected the available stock is pkt, then the quantities must be pkt

		String unitPkt = ProductStockUnits.PKT.toString().toLowerCase();

		for (ProductQuantitiesCategory ele : qty) {

			String qtyUnit = ele.getUnit().toString().toLowerCase();

			if (!qtyUnit.equals(unitPkt)) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_PKT);
			}
		}

//		if there is no exception, then return true
		return true;
	}

	// validate the weights of the set

	/**
	 * Validates the weights of quantities for a Product with available stock in
	 * packets.
	 *
	 * @param qty   The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @param stock The ProductAvailableStock object associated with the quantities.
	 * @return True if the quantities' weight values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyWeightPkt(SortedSet<ProductQuantitiesCategory> qty, ProductAvailableStock stock)
			throws InvalidInputException {

		double weight = stock.getNum();

		for (ProductQuantitiesCategory ele : qty) {

			if (!isWholeNumber(ele.getWeight())) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGTH_PKT_NOS);

			}

			if (ele.getWeight() > weight || ele.getWeight() < ProductConstants.MINIMUM_WEIGHT_FOR_PKT) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT);

			}
		}

//        no exception return true
		return true;
	}

	// Method to validate the list of quantities if the available stock is nos

	/**
	 * Validates the unit values of quantities for a Product with available stock in
	 * pieces.
	 *
	 * @param qty The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @return True if the quantities' unit values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyListNos(SortedSet<ProductQuantitiesCategory> qty) throws InvalidInputException {

//		if they selected the available stock is nos, then the quantities must be nos

		String unitNos = ProductStockUnits.NOS.toString().toLowerCase();

		for (ProductQuantitiesCategory ele : qty) {

			String qtyUnit = ele.getUnit().toString().toLowerCase();

			if (!qtyUnit.equals(unitNos)) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_NOS);
			}
		}

//		if there is no exception, then return true
		return true;
	}

	// validate the weights of set

	/**
	 * Validates the weights of quantities for a Product with available stock in
	 * pieces.
	 *
	 * @param qty   The SortedSet of ProductQuantitiesCategory objects to validate.
	 * @param stock The ProductAvailableStock object associated with the quantities.
	 * @return True if the quantities' weight values are valid.
	 * @throws InvalidInputException If any validation fails.
	 */
	public boolean validateQtyWeightNos(SortedSet<ProductQuantitiesCategory> qty, ProductAvailableStock stock)
			throws InvalidInputException {

		double weight = stock.getNum();

		for (ProductQuantitiesCategory ele : qty) {

			if (!isWholeNumber(ele.getWeight())) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGTH_PKT_NOS);

			}

			if (ele.getWeight() > weight || ele.getWeight() < ProductConstants.MINIMUM_WEIGHT_FOR_NOS) {

				throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_WEIGHT);

			}
		}

//        no exception return true
		return true;
	}

//	validation ends for product quantities

}
