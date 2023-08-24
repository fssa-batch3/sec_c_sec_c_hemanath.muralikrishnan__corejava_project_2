/**
 *
 */
package com.fssa.agrokart.validator;

import java.util.SortedSet;

import com.fssa.agrokart.error.ProductValidatorErrors;
import com.fssa.agrokart.exception.InvalidInputException;
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
    public boolean validateObject(Object obj, String message) throws InvalidInputException {

//		if the object is null, then thrown an exception

        if (obj == null)
            throw new InvalidInputException(message);

//		if the object is not equal to null, then return true
        return true;
    }


//	main validation starts for product

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
        validateAvailObj(product.getAvailableStock());

//		validate the product quantities
        validateQtyObj(product.getQuantities(), product.getAvailableStock());

//		if there is no exception, then return true
        return true;

    }

//	main validation ends for product

//	validation start for a product name object

    //	Method to validate the name
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

    //	Method to validate the product english name
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

    //	Method validate teh product tamil name
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

    //	Method to validate the product image url
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

    //	validate the product category
    public boolean validateCategory(ProductCategory cat) throws InvalidInputException {

//		validate the category is not equal to null
        if (cat == null)
            throw new InvalidInputException(ProductValidatorErrors.INVALID_CATEGORY);

//		if the category is not equal to null
        return true;
    }

//	validation starts for product description

    //	Method to validate the product description
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

//    TODO: check for nutr maximum values for protein,carbo,kcal

//	validation starts for product nutrition's

    //	Method to validate the nutrition's num
    public boolean validateNutrNum(double num, String message) throws InvalidInputException {

        if (num < ProductConstants.MINIMUM_NUTRITION)
            throw new InvalidInputException(message);

//	if there is no exception, then return true
        return true;
    }

    //	Method to validate the product nutrition's
    public boolean validateNutrObj(ProductNutrition nutr) throws InvalidInputException {

//		validate the object is not equal to null
        validateObject(nutr, ProductValidatorErrors.INVALID_NUTR_OBJ);

//		validate the protein num
        validateNutrNum(nutr.getProteinNum(), ProductValidatorErrors.INVALID_PROTEIN_NUTR);

//		validate the carbo num
        validateNutrNum(nutr.getCarbonNumb(), ProductValidatorErrors.INVALID_CARBOHYDRATES_NUTR);

//		validate the calories
        validateNutrNum(nutr.getKcalNum(), ProductValidatorErrors.INVALID_CALORIES_NUTR);

//		if there is no exception, then return true
        return true;

    }

//	validation end for product nutrition's

//	validation starts for product available stock

    //	Method to validate the available stock object
    public boolean validateAvailObj(ProductAvailableStock avail) throws InvalidInputException {

//		validate the avail stock obj
        validateObject(avail, ProductValidatorErrors.INVALID_AVAILABLE_STOCK_OBJ);

//		validate the available stock num
        validateAvailableStock(avail.getNum(), avail.getUnit());

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the weight num
    public boolean validateAvailableStock(double num, ProductStockUnits productStockUnits)
            throws InvalidInputException {

//		check the stock unit not equal to null
        if (productStockUnits == null)
            throw new InvalidInputException(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_UNIT);

//		if the num is lesser than zero or lesser than 20, then throw an exception
        if (num <= ProductConstants.MINIMUM_AVAILABLE_STOCK_QUANTITY)
            throw new InvalidInputException(ProductValidatorErrors.INVALID_AVAILABLE_STOCK_NUM);

//		if there is no exception, then return true
        return true;
    }

//	validation ends for product available stock

    //	validate the status start

    public boolean validateStatus(ProductStatus status) throws InvalidInputException {

        if (status == null)
            throw new InvalidInputException(ProductValidatorErrors.INVALID_STATUS);

        return true;
    }

//	validation starts for product quantities

    //	Method to validate the rupees, unit,quantity
    public boolean validateQtyObj(SortedSet<ProductQuantitiesCate> qty, ProductAvailableStock stock)
            throws InvalidInputException {

//	check list minimum one qty is added or not
        if (qty == null || qty.isEmpty())
            throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_OBJ);

//		validate the qty object is not equal to null
        for (ProductQuantitiesCate ele : qty) {

            validateObject(ele, ProductValidatorErrors.INVALID_QUANTITY_CATE_OBJ);
        }

//		if there is no exception, then send the qty list to another method to validate the units,rs,weight
        validateQtyEle(qty, stock);

//		if there is no exception, then return true
        return true;
    }


//	validate the status end

    //	Method to validate the unit,rs,weight
    public boolean validateQtyEle(SortedSet<ProductQuantitiesCate> qty, ProductAvailableStock stock)
            throws InvalidInputException {

//		check the qty units is not equal to null
        for (ProductQuantitiesCate ele : qty) {

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
    public boolean validateQtyRs(SortedSet<ProductQuantitiesCate> qty) throws InvalidInputException {

//		iterate through the for each loop
        for (ProductQuantitiesCate ele : qty) {

//			if any product amount is lesser than 10 rs thrown an exception
            if (ele.getRs() <= ProductConstants.MINIMUM_AMOUNT) {
                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_AMOUNT);
            }
        }

//		if there is no exception, then return true
        return true;
    }


    //	Method to the validate the weight of the product

//    TODO: check if the available stock 20 then the user given quantites cate must be 20 kg | nos | pkt not more than that
    public boolean validateQtyWeight(SortedSet<ProductQuantitiesCate> qty) throws InvalidInputException {

        for (ProductQuantitiesCate ele : qty) {

            String unitGm = ele.getUnit().toString().toLowerCase();

//			if the selected quantity is gm, then check the weight is greater than gram 100
//				if the weight is lesser than 100, throw an exception
            if (unitGm.equals("gm") && ele.getWeight() <= ProductConstants.MINIMUM_WEIGHT_FOR_GRAM) {

                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_GRAM);
            }


//			if the weight is lesser than one, then throw an exception
            if (ele.getWeight() < ProductConstants.MINIMUM_WEIGHT) {

                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_CATE_NUM);
            }
        }

//		if there is no exception return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is kg
    public boolean validateQtyListKg(SortedSet<ProductQuantitiesCate> qty) throws InvalidInputException {

//		if they selected the available stock is kg, then the quantities must be kg or gm
        String unitKG = ProductStockUnits.KG.toString().toLowerCase();
        String unitGM = ProductStockUnits.GM.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitKG) && !qtyUnit.equals(unitGM)) {

                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_KG);
            }

        }

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is pkt
    public boolean validateQtyListPkt(SortedSet<ProductQuantitiesCate> qty) throws InvalidInputException {

//		if they selected the available stock is pkt, then the quantities must be pkt

        String unitPkt = ProductStockUnits.PKT.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitPkt)) {

                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_PKT);
            }
        }

//		if there is no exception, then return true
        return true;
    }

    //	Method to validate the list of quantities if the available stock is nos
    public boolean validateQtyListNos(SortedSet<ProductQuantitiesCate> qty) throws InvalidInputException {

//		if they selected the available stock is nos, then the quantities must be nos

        String unitNos = ProductStockUnits.NOS.toString().toLowerCase();

        for (ProductQuantitiesCate ele : qty) {

            String qtyUnit = ele.getUnit().toString().toLowerCase();

            if (!qtyUnit.equals(unitNos)) {

                throw new InvalidInputException(ProductValidatorErrors.INVALID_QUANTITY_FOR_NOS);
            }
        }

//		if there is no exception, then return true
        return true;
    }

//	validation ends for product quantities


}
