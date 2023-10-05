package com.fssa.agrokart.model;

public class SubOrderItems {

	private int uniqueId;
	private int sellerId;
	private int mainOrderId;
	private int productId;
	private int qtyId;
	private int qtyNos;
	private double amount;

	public SubOrderItems() {

	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getMainOrderId() {
		return mainOrderId;
	}

	public void setMainOrderId(int mainOrderId) {
		this.mainOrderId = mainOrderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQtyId() {
		return qtyId;
	}

	public void setQtyId(int qtyId) {
		this.qtyId = qtyId;
	}

	public int getQtyNos() {
		return qtyNos;
	}

	public void setQtyNos(int qtyNos) {
		this.qtyNos = qtyNos;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SubOrderItems [uniqueId=" + uniqueId + ", sellerId=" + sellerId + ", mainOrderId=" + mainOrderId
				+ ", productId=" + productId + ", qtyId=" + qtyId + ", qtyNos=" + qtyNos + ", amount=" + amount + "]";
	}

}
