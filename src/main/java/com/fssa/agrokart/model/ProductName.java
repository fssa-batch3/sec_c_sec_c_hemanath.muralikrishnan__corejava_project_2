/**
 * 
 */
package com.fssa.agrokart.model;

/**
 * @author HemanathMuralikrishn
 *
 */
public class ProductName {

	private String englishName;
	private String tamilName;

//	default constructor
	public ProductName() {

	}

//	Parameterized constructor

	public ProductName(String engName, String tamName) {

		this.englishName = engName;
		this.tamilName = tamName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getTamilName() {
		return tamilName;
	}

	public void setTamilName(String tamilName) {
		this.tamilName = tamilName;
	}

	@Override
	public String toString() {
		return "English Name: " + englishName + ", Tamil Name: " + tamilName;
	}
}
