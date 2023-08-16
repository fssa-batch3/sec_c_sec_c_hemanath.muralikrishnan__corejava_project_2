/**
 *
 */
package com.fssa.agrokart.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.SortedSet;

import com.fssa.agrokart.error.ProductValidatorErrors;
import com.fssa.agrokart.exception.InvalidProductDataException;
import com.fssa.agrokart.model.*;
import com.fssa.agrokart.enums.*;
import com.fssa.agrokart.regexpattern.ProductRegexPatterns;
import com.fssa.agrokart.constants.ProductConstants;
import com.fssa.agrokart.util.StringUtil;

/**
 * A validator class which holds the all types of validator for a product object
 * and fields
 *
 * @author HemanathMuralikrishn
 */

public class ProductValidator {

    //    creating new string util class
    StringUtil stringUtil = new StringUtil();

    //	method will validate the object is not equal not null
    public boolean validateObject(Object obj, String field) throws InvalidProductDataException {

//		if the object is null, then thrown an exception

        if (obj == null)
            throw new InvalidProductDataException(
                    ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_OBJECT);

//		if the object is not equal to null, then return true
        return true;
    }

    //	method will validate the current instance of class or not
    public boolean validateClass(Object obj, String field) throws InvalidProductDataException {

//		if the object is not current instance of class then throw an exception
        if (obj instanceof Product || obj instanceof ProductName || obj instanceof ProductNutrition
                || obj instanceof ProductAvailableStock || obj instanceof ProductQuantitiesCate) {

//			if the object is current instance of class, then return true
            return true;

        }

        throw new InvalidProductDataException(
                ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_INSTANCE_CLASS);
    }


//	main validation starts for product

    public boolean validateProduct(Product product) throws InvalidProductDataException {

//		validate the product object is not equal to null
        validateObject(product, ProductFieldNames.PRODUCT);

//		validate the product object is instance of product class
        validateClass(product, ProductFieldNames.PRODUCT);

//		validate the product name
        validateNameObj(product.getName());

//		validate the product image url
        validateImageUrl(product.getImageUrl());

//		validate the product description
        validateDescription(product.getDescription());

//		validate the product nutrition's
        validateNutrObj(product.getNutrition());

//		validate the product available stock
        validateAvailObj(product.getAvailableStock());

//		validate the product quantities
        validateQtyObj(product.getQuantities(), product.getAvailableStock());

//		if there is no exception, then return true
        return true;

    }

//	main validation ends for product

//	validation start for a product name object

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

//		if there is no exception, then return true
        return true;

//		
    }

    //	Method to validate the product english name
    public boolean validateEnglishName(String engName) throws InvalidProductDataException {

        String trimmedInput = stringUtil.trimString(engName);

        if (trimmedInput == null)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.ENGLISH_NAME + " " + ProductValidatorErrors.INVALID_STRING);


        boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.ENGLISH_NAME);

        if (!isMatch)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.ENGLISH_NAME + " " + ProductValidatorErrors.PATTERN_MISMATCH_MESSAGE);

//		if there is no exception, then return true
        return true;

    }

    //	Method validate teh product tamil name
    public boolean validateTamilName(String tamName) throws InvalidProductDataException {

        String trimmedInput = stringUtil.trimString(tamName);

        if (trimmedInput == null)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.TAMIL_NAME + " " + ProductValidatorErrors.INVALID_STRING);

        boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.TAMIL_NAME);

        if (!isMatch)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.TAMIL_NAME + " " + ProductValidatorErrors.PATTERN_MISMATCH_MESSAGE);

//		if there is no exception, then return true
        return true;

    }

//	validation end for a product name object

//	validation starts for product image url

    //	Method to validate the product image url
    public boolean validateImageUrl(String imgUrl) throws InvalidProductDataException {

        String trimmedInput = stringUtil.trimString(imgUrl);

        if (trimmedInput == null)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.IMAGE_URL + " " + ProductValidatorErrors.INVALID_STRING);

        boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.IMAGE_URL);

        if (!isMatch)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.IMAGE_URL + " " + ProductValidatorErrors.PATTERN_MISMATCH_MESSAGE);

//		if there is no exception, then return true
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


        String trimmedInput = stringUtil.trimString(desc);

        if (trimmedInput == null)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.DESCRIPTION + " " + ProductValidatorErrors.INVALID_STRING);

        boolean isMatch = stringUtil.stringWithRegex(trimmedInput, ProductRegexPatterns.DESCRIPTION);

        if (!isMatch)
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.DESCRIPTION + " " + ProductValidatorErrors.PATTERN_MISMATCH_MESSAGE);


//		if there is no exception, then return true
        return true;
    }

//	validation end for product description

//	validation starts for product nutrition's

    //	Method to validate the nutrition's num
    public boolean validateNutrNum(double num, String field) throws InvalidProductDataException {

        if (num < ProductConstants.MINIMUM_NUTRITION)
            throw new InvalidProductDataException(
                    ProductFieldNames.PRODUCT + " " + field + " " + ProductValidatorErrors.INVALID_NUTR_COUNT);

//	if there is no exception, then return true
        return true;
    }

    //	Method to validate the product nutrition's
    public boolean validateNutrObj(ProductNutrition nutr) throws InvalidProductDataException {

//		validate the object is not equal to null
        validateObject(nutr, ProductFieldNames.NUTRITION);

//		validate the object is instance of product class
        validateClass(nutr, ProductFieldNames.NUTRITION);

//		validate the protein num
        validateNutrNum(nutr.getProteinNum(), ProductFieldNames.PROTEIN);

//		validate the carbo num
        validateNutrNum(nutr.getCarbonNumb(), ProductFieldNames.CARBO);

//		validate the calories
        validateNutrNum(nutr.getKcalNum(), ProductFieldNames.CALORIES);

//		if there is no exception, then return true
        return true;

    }

//	validation end for product nutrition's

//	validation starts for product available stock

    //	Method to validate the available stock object
    public boolean validateAvailObj(ProductAvailableStock avail) throws InvalidProductDataException {

//		validate the avail stock obj
        validateObject(avail, ProductFieldNames.AVAILABLE);

//		validate the object instance of class
        validateClass(avail, ProductFieldNames.AVAILABLE);

//		validate the available stock num
        validateAvailNum(avail.getNum(), avail.getUnit());

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the weight num
    public boolean validateAvailNum(double num, ProductStockUnits productStockUnits)
            throws InvalidProductDataException {

//		check the stock unit not equal to null
        if (productStockUnits == null)
            throw new InvalidProductDataException(ProductValidatorErrors.INVALID_AVAIL_UNIT);

//		if the num is lesser than zero or lesser than 20, then throw an exception
        if (num <= ProductConstants.MINIMUM_AVAILABLE_STOCK_QUANTITY)
            throw new InvalidProductDataException(
                    ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_AVAIL_NUM + " "
                            + productStockUnits.toString().toLowerCase() + ".");

//		if there is no exception, then return true
        return true;
    }

//	validation ends for product available stock

//	validation starts for product quantities

    //	Method to validate the rupees, unit,quantity
    public boolean validateQtyObj(SortedSet<ProductQuantitiesCate> qty, ProductAvailableStock stock)
            throws InvalidProductDataException {

//	check list minimum one qty is added or not
        if (qty == null || qty.isEmpty())
            throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.QTY_OBJ + " "
                    + ProductValidatorErrors.INVALID_QTY_OBJ);

//		validate the qty object is not equal to null
        for (ProductQuantitiesCate ele : qty) {

            validateObject(ele, ProductFieldNames.QTY);

            validateClass(ele, ProductFieldNames.QTY);
        }

//		if there is no exception, then send the qty list to another method to validate the units,rs,weight
        validateQtyEle(qty, stock);

//		if there is no exception, then return true
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
    public boolean validateQtyEle(SortedSet<ProductQuantitiesCate> qty, ProductAvailableStock stock)
            throws InvalidProductDataException {

//		check the qty units is not equal to null
        for (ProductQuantitiesCate ele : qty) {

            if (ele.getUnit() == null) {

                throw new InvalidProductDataException(ProductValidatorErrors.INVALID_QTY_UNIT);
            }
        }

//		get the available stock unit

        String stockUnit = stock.getUnit().toString().toLowerCase();

//		and also validate the weight and rs numbs

//		if the selected available stock is in kg,
//		then user can only add kg and gram quantities

        if (stockUnit.equals(ProductStockUnits.KG.toString().toLowerCase())) {

            validateQtyListKg(qty);

        }

//		if the selected available stock is in pkt,
//		then user can only add pkt quantities

        if (stockUnit.equals(ProductStockUnits.PKT.toString().toLowerCase())) {

            validateQtyListPkt(qty);
        }

//		if the select available stock is in nos,
//		then user can only add the no quantities

        if (stockUnit.equals(ProductStockUnits.NOS.toString().toLowerCase())) {

            validateQtyListNos(qty);
        }

//		validate the rs and weight
        validateQtyWeight(qty);
        validateQtyRs(qty);

//		if there is no exception, then return true
        return true;

    }


    //	Method to validate the rs
    public boolean validateQtyRs(SortedSet<ProductQuantitiesCate> qty) throws InvalidProductDataException {

//		iterate through the for each loop
        for (ProductQuantitiesCate ele : qty) {

//			if any product amount is lesser than 10 rs thrown an exception
            if (ele.getRs() <= ProductConstants.MINIMUM_AMOUNT) {
                throw new InvalidProductDataException(
                        ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_RS);
            }
        }

//		if there is no exception, then return true
        return true;
    }


    //	Method to the validate the weight of the product
    public boolean validateQtyWeight(SortedSet<ProductQuantitiesCate> qty) throws InvalidProductDataException {

        for (ProductQuantitiesCate ele : qty) {

            String unitGm = ele.getUnit().toString().toLowerCase();

//			if the selected quantity is gm, then check the weight is greater than gram 100
//				if the weight is lesser than 100, throw an exception
            if (unitGm.equals("gm") && ele.getWeight() <= ProductConstants.MINIMUM_WEIGHT_FOR_GRAM) {

                throw new InvalidProductDataException(
                        ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_WEIGHT_GM);
            }


//			if the weight is lesser than zero, then throw an exception
            if (ele.getWeight() <= ProductConstants.MINIMUM_WEIGHT) {

                throw new InvalidProductDataException(
                        ProductFieldNames.PRODUCT + " " + ProductValidatorErrors.INVALID_QTY_WEIGHT);
            }
        }

//		if there is no exception return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is kg
    public boolean validateQtyListKg(SortedSet<ProductQuantitiesCate> qty) throws InvalidProductDataException {

//		if they selected the available stock is kg, then the quantities must be kg or gm
        String unitKG = ProductStockUnits.KG.toString().toLowerCase();
        String unitGM = ProductStockUnits.GM.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitKG) && !qtyUnit.equals(unitGM)) {

                throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
                        + " " + ProductValidatorErrors.INVALID_QTY_KG);
            }

        }

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is pkt
    public boolean validateQtyListPkt(SortedSet<ProductQuantitiesCate> qty) throws InvalidProductDataException {

//		if they selected the available stock is pkt, then the quantities must be pkt

        String unitPkt = ProductStockUnits.PKT.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitPkt)) {

                throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
                        + " " + ProductValidatorErrors.INVALID_QTY_PKT);
            }
        }

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is nos
    public boolean validateQtyListNos(SortedSet<ProductQuantitiesCate> qty) throws InvalidProductDataException {

//		if they selected the available stock is nos, then the quantities must be nos

        String unitNos = ProductStockUnits.NOS.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitNos)) {

                throw new InvalidProductDataException(ProductFieldNames.PRODUCT + " " + ProductFieldNames.AVAIL_UNIT
                        + " " + ProductValidatorErrors.INVALID_QTY_NOS);
            }
        }

//		if there is no exception, then return true
        return true;
    }

//	validation ends for product quantities

//	validation starts for product-created date and time

    // Method to validate the product-created date
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


}
