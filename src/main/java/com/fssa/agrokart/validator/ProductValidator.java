/**
 * 
 */
package com.fssa.agrokart.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import java.util.TreeSet;
import com.fssa.agrokart.exceptions.InvalidProductDataException;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.enums.*;

/**
 * An validator class which holds the all types of validator for product object
 * and fields
 * 
 * @author HemanathMuralikrishn
 * 
 */

public class ProductValidator {

//	method will validate the object is not equal not null
	public boolean validateObject(Object obj, String field) throws InvalidProductDataException {

//		if the object is null then thrown an exception

		if (obj == null)
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_OBJECT);

//		if the object is not equal to null then return true
		return true;
	}

//	method will validate the current instance of class or not
	public boolean validateClass(Object obj, String field) throws InvalidProductDataException {

//		if the object is not current instance of class then throw an exception
		if (obj instanceof Product || obj instanceof ProductName || obj instanceof ProductNutritions
				|| obj instanceof ProductAvailableStock || obj instanceof ProductQuantites) {

//			if the object is current instance of class then return true
			return true;

		}

		throw new InvalidProductDataException(
				ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_INSTANCE_CLASS);
	}

	// Method to trim a string, returns null if the input is null or empty after
	// trimming
	private String trimString(String input) {
		if (input == null || input.trim().isEmpty()) {
			return null;
		}
		return input.trim();
	}

	// Method to validate the string is not equal to null or empty string after
	// trimming
	public boolean validateString(String input, String field) throws InvalidProductDataException {

//		calling the trim string method to trim the string
		String trimmedInput = trimString(input);

//		if the trimmed input is equal to null then throw an exception
		if (trimmedInput == null)
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_STRING);

//		if the input string is not equal to null or empty string then return true
		return true;
	}

//	Method to validate the string with regular expression
	public boolean validateStringWithRegex(String input, String pattern, String field)
			throws InvalidProductDataException {

//		calling the trim string method to trim the string
		String trimmedInput = trimString(input);

//		matching the input with the given regex pattern 
//		if it matches then matches method return true
//		otherwise it will return false
		boolean isMatch = Pattern.matches(pattern, trimmedInput);

//		if the input doesn't matches the pattern then thrown exception
		if (!isMatch)
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.PATTERN_MISMATCH_MESSAGE);

//		if the input matches the regex pattern then return true
		return true;

	}

//	main validation starts for product

	public boolean validateProduct(Product product) throws InvalidProductDataException {

//		validate the product object is not equal to null
		validateObject(product, ProductFieldNames.PRODUCT);

//		validate the product object is instance of product class
		validateClass(product, ProductFieldNames.PRODUCT);

//		validate the product name
		validateNameObj(product.getName());

//		validate the prodcut image url
		validateImageUrl(product.getImageUrl());

//		validate the product description
		validateDescription(product.getDescription());

//		validate the product nutr
		validateNutrObj(product.getNutritions());

//		validate the product available stock
		validateAvailObj(product.getAvailableStock());

//		validate the product quantities
		validateQtyObj(product.getQuantites(), product.getAvailableStock());

//		validate the product creation date
		validateCreatedDate(product.getCreationDate());

//		validate the product creation time
		validateCreatedTime(product.getCreationTime());

//		validate the product updated date
		validateUpdatedDate(product.getUpdatedDate(), product.getCreationDate());

//		validate the product updated time
		validateUpdatedTime(product.getUpdatedTime(), product.getCreationTime(), product.getCreationDate(),
				product.getUpdatedDate());

//		if there is no exception then return true
		return true;

	}

//	main validation ends for product

//	validation start for product name object

//	Method to validate the name
	public boolean validateNameObj(ProductName name) throws InvalidProductDataException {

//		validate with object validator
		validateObject(name, ProductFieldNames.NAME_OBJ);

//		validate the instance of class
		validateClass(name, ProductFieldNames.NAME_OBJ);

//		send the English name to english name validator
		validateEnglishName(name.getEnglishName());

//		send the tamil name to tamil name validator
		validateTamilName(name.getTamilName());

//		if there is no exception then return true
		return true;

//		
	}

//	Method to validate the product english name
	public boolean validateEnglishName(String engName) throws InvalidProductDataException {

//		validate with string validator
		validateString(engName, ProductFieldNames.ENGLISH_NAME);

//		validate with regex pattern validator
		validateStringWithRegex(engName, RegexPatterns.ENGLISH_NAME, ProductFieldNames.ENGLISH_NAME);

//		if there is no exception then return true
		return true;

	}

//	Method validate teh product tamil name
	public boolean validateTamilName(String tamName) throws InvalidProductDataException {

//		validate with string validator
		validateString(tamName, ProductFieldNames.TAMIL_NAME);

//		validate with regex pattern validator
		validateStringWithRegex(tamName, RegexPatterns.TAMIL_NAME, ProductFieldNames.TAMIL_NAME);

//		if there is no exception then return true
		return true;

	}

//	validation end for product name object

//	validation starts for product image url

//	Method to validate the product image url
	public boolean validateImageUrl(String imgUrl) throws InvalidProductDataException {

//		validate with string validator
		validateString(imgUrl, ProductFieldNames.IMAGE_URL);

//		validate with regex pattern validator
		validateStringWithRegex(imgUrl, RegexPatterns.IMAGE_URL, ProductFieldNames.IMAGE_URL);

//		if there is no exception then return true
		return true;
	}

//	validation end for product image url

//	validate the product category
	public boolean validateCategory(ProductCategory cat) throws InvalidProductDataException {

//		validate the category is not equal to null
		if (cat == null)
			throw new InvalidProductDataException(ProductValidatorErrors.INVALID_CATEGORY);

//		if the category is not equal to null
		return true;
	}

//	validation starts for product description

//	Method to validate the product description
	public boolean validateDescription(String desc) throws InvalidProductDataException {

//		validate with string validator
		validateString(desc, ProductFieldNames.DESCRIPTION);

//		validate with regex pattern validator
		validateStringWithRegex(desc, RegexPatterns.DESCRIPTION, ProductFieldNames.DESCRIPTION);

//		if there is no exception then return true
		return true;
	}

//	validation end for product description

//	validation starts for product nutritions

//	minimum value for all nutr
	public static final double minNutr = 0;

//	Method to validate the nutritions num
	public boolean validateNutrNum(double num, String field) throws InvalidProductDataException {

		if (num < minNutr)
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_NUTR_COUNT);

//	if there is no exception then return true
		return true;
	}

//	Method to validate the product nutritions
	public boolean validateNutrObj(ProductNutritions nutr) throws InvalidProductDataException {

//		validate the object is not equal to null
		validateObject(nutr, ProductFieldNames.NUTRITION);

//		validate the object is instance of product class
		validateClass(nutr, ProductFieldNames.NUTRITION);

//		validate the protein num
		validateNutrNum(nutr.getProteinNum(), ProductFieldNames.PROTEIN);

//		validate the carbo num
		validateNutrNum(nutr.getCarboNum(), ProductFieldNames.CARBO);

//		validate the calories
		validateNutrNum(nutr.getKcalNum(), ProductFieldNames.CALORIES);

//		if there is no exception then return true
		return true;

	}

//	validation end for product nutritions

//	validation starts for product available stock

//	Method to validate the available stock object
	public boolean validateAvailObj(ProductAvailableStock avail) throws InvalidProductDataException {

//		validate the avail stock obj
		validateObject(avail, ProductFieldNames.AVAILABLE);

//		validate the object instance of class
		validateClass(avail, ProductFieldNames.AVAILABLE);

//		validate the available stock num
		validateAvailNum(avail.getNum(), avail.getUnit());

//		if there is no exceptiom then return true
		return true;
	}

//	variable to store the minimum quantity
	
	// TODO : have a interface which holds the constants variable 
	
	public static final double minQty = 20;

//	Method to validate the weight num
	public boolean validateAvailNum(double num, ProductStockUnits productStockUnits)
			throws InvalidProductDataException {

//		check the stock unit not equal to null
		if (productStockUnits == null)
			throw new InvalidProductDataException(ProductValidatorErrors.INVALID_AVAIL_UNIT);

//		if the num is lesser than zero or lesser than 20 then throw an exception
		if (num <= minQty)
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_AVAIL_NUM + " "
							+ productStockUnits.toString().toLowerCase() + ".");

//		if there is no exception then return true
		return true;
	}

//	validation ends for product available stock

//	validation starts for product quantities

//	Method to validate the rupees,unit,quantity
	public boolean validateQtyObj(TreeSet<ProductQuantites> qty, ProductAvailableStock stock)
			throws InvalidProductDataException {

//	check list minimum one qty is added or not
		if (qty == null || qty.isEmpty())
			throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.QTY_OBJ + " "
					+ ProductValidatorErrors.INVALID_QTY_OBJ);

//		validate the each qty object is not equal to null
		for (ProductQuantites ele : qty) {

			validateObject(ele, ProductFieldNames.QTY);

			validateClass(ele, ProductFieldNames.QTY);
		}

//		if there is no exception then send the qty list to another method to validate the units,rs,weight
		validateQtyEle(qty, stock);

//		if there is no exception then return true
		return true;
	}

//	validate the status start

	public boolean validateStatus(ProductStatus status) throws InvalidProductDataException {

		if (status == null)
			throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.STATUS + " "
					+ ProductValidatorErrors.INVALID_STATUS);

		return true;
	}

//	validate the status end

//	Method to validate the unit,rs,weight
	public boolean validateQtyEle(TreeSet<ProductQuantites> qty, ProductAvailableStock stock)
			throws InvalidProductDataException {

//		check the qty units is not equal to null
		for (ProductQuantites ele : qty) {

			if (ele.getUnit() == null) {

				throw new InvalidProductDataException(ProductValidatorErrors.INVALID_QTY_UNIT);
			}
		}

//		get the available stock unit

		String trimStockUnit = stock.getUnit().toString().toLowerCase();

//		and also validate the weight and rs nums

//		if the selected available stock is in kg
//		then user can only add kg and gram quantities

		if (trimStockUnit.equals(ProductStockUnits.KG.toString().toLowerCase())) {

			validateQtyListKg(qty);

		}

//		if the selected available stock is in pkt
//		then user can only add pkt quantities

		if (trimStockUnit.equals(ProductStockUnits.PKT.toString().toLowerCase())) {

			validateQtyListPkt(qty);
		}

//		if the selecte available stock is in nos
//		then user can only add the nos quantities

		if (trimStockUnit.equals(ProductStockUnits.NOS.toString().toLowerCase())) {

			validateQtyListNos(qty);
		}

//		validte the rs and weight
		validateQtyWeight(qty);
		validateQtyRs(qty);

//		if there is no exception then return true
		return true;

	}

//	variable to store the minimum amount for all product
	public static final double minAmount = 10;

//	Method to validate the rs
	public boolean validateQtyRs(TreeSet<ProductQuantites> qty) throws InvalidProductDataException {

//		iterate throught the for each loop
		for (ProductQuantites ele : qty) {

//			if the any product amount is lesser than 10 rs thrown an exception
			if (ele.getRs() <= minAmount) {
				throw new InvalidProductDataException(
						ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_RS);
			}
		}

//		if there is no exception then return true
		return true;
	}

//	if the selected qty is gram then the weight must be greater than 100
	public static final double minWeightGm = 100;

//	if the selected qty is other than gram must be greater than zero
	public static final double minWeight = 0;

//	Method to the validate the weight of the product
	public boolean validateQtyWeight(TreeSet<ProductQuantites> qty) throws InvalidProductDataException {

		for (ProductQuantites ele : qty) {

			String unitGm = trimString(ele.getUnit().toString().toLowerCase());

//			if the selected quantity is gm then check the weight is greater than 100 gram
			if (unitGm.equals("gm")) {

//				if the weight is lesser than 100 throw an exception
				if (ele.getWeight() <= minWeightGm) {

					throw new InvalidProductDataException(
							ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_WEIGHT_GM);
				}
			}

//			if the weight is lesser than zero then throw an exceptoin
			if (ele.getWeight() <= minWeight) {

				throw new InvalidProductDataException(
						ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_WEIGHT);
			}
		}

//		if there is no exception return true
		return true;
	}

//	Method to validate the list of quantites if the available stock is kg
	public boolean validateQtyListKg(TreeSet<ProductQuantites> qty) throws InvalidProductDataException {

//		if the selected the available stock is kg then the quantites must be kg or gm
		String unitKG = ProductStockUnits.KG.toString().toLowerCase();
		String unitGM = ProductStockUnits.GM.toString().toLowerCase();

		for (ProductQuantites ele : qty) {

			String qtyUnit = trimString(ele.getUnit().toString().toLowerCase());

			if (!qtyUnit.equals(unitKG) && !qtyUnit.equals(unitGM)) {

				throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
						+ " " + ProductValidatorErrors.INVALID_QTY_KG);
			}

		}

//		if there is no exception then return true
		return true;
	}

//	Method to validate the list of quantites if the available stock is pkt
	public boolean validateQtyListPkt(TreeSet<ProductQuantites> qty) throws InvalidProductDataException {

//		if the selected the available stock is pkt then the quantites must be pkt 

		String unitPkt = ProductStockUnits.PKT.toString().toLowerCase();

		for (ProductQuantites ele : qty) {

			String qtyUnit = trimString(ele.getUnit().toString().toLowerCase());

			if (!qtyUnit.equals(unitPkt)) {

				throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
						+ " " + ProductValidatorErrors.INVALID_QTY_PKT);
			}
		}

//		if there is no exception then return true
		return true;
	}

//	Method to validate the list of quantites if the available stock is nos
	public boolean validateQtyListNos(TreeSet<ProductQuantites> qty) throws InvalidProductDataException {

//		if the selected the available stock is nos then the quantites must be nos

		String unitNos = ProductStockUnits.NOS.toString().toLowerCase();

		for (ProductQuantites ele : qty) {

			String qtyUnit = trimString(ele.getUnit().toString().toLowerCase());

			if (!qtyUnit.equals(unitNos)) {

				throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
						+ " " + ProductValidatorErrors.INVALID_QTY_NOS);
			}
		}

//		if there is no exception then return true
		return true;
	}

//	validation ends for product qunatities

//	validation starts for product created date and time

	// Method to validate the product created date
	public boolean validateCreatedDate(LocalDate date) throws InvalidProductDataException {
		// Check if the date is null
		if (date == null) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_CREATED_DATE_NULL);
		}

		// Check if the date is not equal to the current date (i.e., it should be
		// present)
		if (!date.isEqual(LocalDate.now())) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_CREATED_DATE);
		}

		return true; // or false based on your requirements
	}

	// Method to validate the product created time
	public boolean validateCreatedTime(LocalTime time) throws InvalidProductDataException {
		// Check if the time is null
		if (time == null) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_CREATED_TIME_NULL);
		}

		// Check if the time is within 24 hours (00:00:00 to 23:59:59)
		if (time.isBefore(LocalTime.MIN) || time.isAfter(LocalTime.MAX)) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_CREATED_TIME);
		}

		return true; // or false based on your requirements
	}

//	validation ends for product created date and time

//	validation starts for product updated date and time

//	Method to validate the product updated date
	public boolean validateUpdatedDate(LocalDate date, LocalDate createdDate) throws InvalidProductDataException {
//		check if the date is null
		if (date == null) {

			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_UPDATED_DATE_NULL);
		}

//		if the updated is lesser than created date thrown exception
		if (date.isBefore(createdDate)) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_UPDATED_DATE);
		}

//		if there is no exception then return true
		return true;

	}

//	Method to validate the product updated time
	public boolean validateUpdatedTime(LocalTime updatedTime, LocalTime createdTime, LocalDate createdDate,
			LocalDate updatedDate) throws InvalidProductDataException {

//		check if the time is null
		if (updatedTime == null) {
			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_UPDATED_TIME_NULL);
		}

		if (updatedDate.equals(createdDate) && updatedTime.isBefore(createdTime)) {

			throw new InvalidProductDataException(
					ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_UPDATED_TIME);
		}

//		if there is no exception then return true
		return true;
	}

//	validation ends for product updated date and time

}
