/**
 *
 */
package com.fssa.agrokart.validator;

/**
 * An interface which holds the field names for the product model object
 * to give an appropriate error message to the user
 *
 * @author HemanathMuralikrishnan
 */
public interface ProductFieldNames {

    //	for whole object
    String PRODUCT = "Product";
    //	for the product name object
    String NAME_OBJ = "name";
    //	for product english name
    String ENGLISH_NAME = "english name";
    //	for product tamil name
    String TAMIL_NAME = "tamil name";
    //	for product image url
    String IMAGE_URL = "image url";
    //	for category
    String CATEGORY = "category";
    //	for product description
    String DESCRIPTION = "description";
    //	for product nutritions
    String NUTRITION = "nutrition";
    //	for protein
    String PROTEIN = "protein";
    //	for carbohydrates
    String CARBO = "carbohydrates";
    //	for calories
    String CALORIES = "calories";
    //	for available stock
    String AVAILABLE = "available stock";
    //	for available stock unit
    String AVAIL_UNIT = "available stock unit";
    //	for product status
    String STATUS = "status";
    //	for product quantity
    String QTY_OBJ = "quantity";
    //	for product qty unit
    String QTY = "quantity unit";
}
