package com.fssa.agrokart.model;

import java.time.LocalDateTime;
import java.util.List;

public class MainOrderHistory {

	private int uniqueId;
	private int addressId;
	private int userId;
	private int totalProducts;
	private DeliveryDay deliveryDay;
	private double totalAmount;
	private OrderStatus orderStatus;
	private PaymentMethod paymentMethod;
	private PaymentStatus paymentStatus;
	private LocalDateTime orderCreationDateTime;
	private LocalDateTime orderUpdatedDateTime;
	private boolean isOrderCancelled;
	private List<SubOrderItems> orderItems;

	public MainOrderHistory() {

	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}

	public DeliveryDay getDeliveryDay() {
		return deliveryDay;
	}

	public void setDeliveryDay(DeliveryDay deliveryDay) {
		this.deliveryDay = deliveryDay;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getOrderCreationDateTime() {
		return orderCreationDateTime;
	}

	public void setOrderCreationDateTime(LocalDateTime orderCreationDateTime) {
		this.orderCreationDateTime = orderCreationDateTime;
	}

	public LocalDateTime getOrderUpdatedDateTime() {
		return orderUpdatedDateTime;
	}

	public void setOrderUpdatedDateTime(LocalDateTime orderUpdatedDateTime) {
		this.orderUpdatedDateTime = orderUpdatedDateTime;
	}

	public boolean isOrderCancelled() {
		return isOrderCancelled;
	}

	public void setOrderCancelled(boolean isOrderCancelled) {
		this.isOrderCancelled = isOrderCancelled;
	}

	public List<SubOrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<SubOrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "MainOrderHistory [uniqueId=" + uniqueId + ", addressId=" + addressId + ", userId=" + userId
				+ ", totalProducts=" + totalProducts + ", deliveryDay=" + deliveryDay + ", totalAmount=" + totalAmount
				+ ", orderStatus=" + orderStatus + ", paymentMethod=" + paymentMethod + ", paymentStatus="
				+ paymentStatus + ", orderCreationDateTime=" + orderCreationDateTime + ", orderUpdatedDateTime="
				+ orderUpdatedDateTime + ", isOrderCancelled=" + isOrderCancelled + ", orderItems=" + orderItems + "]";
	}

}
