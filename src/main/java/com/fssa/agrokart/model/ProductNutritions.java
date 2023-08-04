/**
 * 
 */
package com.fssa.agrokart.model;

/**
 * @author HemanathMuralikrishn
 *
 */
public class ProductNutritions {

//	units for protein and carbo is "gram" only it will not change

	private static final String procarboUnit = "gm";

//	unit for kcal is "cal" only it will not change

	private static final String kcalUnit = "kcal";

	private double proteinNum;
	private double carboNum;
	private double kcalNum;

//	defeault constructor
	public ProductNutritions() {

	}

//	parameterized constructor
	public ProductNutritions(double proteinNum, double carboNum, double kcalNum) {
		super();
		this.proteinNum = proteinNum;
		this.carboNum = carboNum;
		this.kcalNum = kcalNum;
	}

	public double getProteinNum() {
		return proteinNum;
	}

	public void setProteinNum(double proteinNum) {
		this.proteinNum = proteinNum;
	}

	public double getCarboNum() {
		return carboNum;
	}

	public void setCarboNum(double carboNum) {
		this.carboNum = carboNum;
	}

	public double getKcalNum() {
		return kcalNum;
	}

	public void setKcalNum(double kcalNum) {
		this.kcalNum = kcalNum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Protein: ").append(proteinNum).append(" ").append(procarboUnit).append(" ");
		sb.append("Carbohydrates: ").append(carboNum).append(" ").append(procarboUnit).append(" ");
		sb.append("Calories: ").append(kcalNum).append(" ").append(kcalUnit);
		return sb.toString();
	}

	public static String getProcarbounit() {
		return procarboUnit;
	}

	public static String getKcalunit() {
		return kcalUnit;
	}
}
