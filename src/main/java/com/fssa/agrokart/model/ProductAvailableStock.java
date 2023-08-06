/**
 * 
 */
package com.fssa.agrokart.model;

import com.fssa.agrokart.enums.ProductStockUnits;

/**
 *
 * A model object class for product-available stock
 * @author HemanathMuralikrishnan
 */
public class ProductAvailableStock {

	private double num;
	private ProductStockUnits unit;

	public ProductAvailableStock() {

	}

	// get and set for available stock num

	public ProductAvailableStock(double num, ProductStockUnits unit) {
		super();
		this.num = num;
		this.unit = unit;
	}

	public double getNum() {
		return num;
	}

	public void setNum(float num) {
		this.num = num;
	}

	// get and set for available stock unit

	public ProductStockUnits getUnit() {
		return unit;
	}

	public void setUnit(ProductStockUnits unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
        return num +
				" " +
				unit;
	}
}
