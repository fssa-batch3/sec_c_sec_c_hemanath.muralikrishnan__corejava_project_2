package com.fssa.agrokart.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.model.Product;
import com.fssa.agrokart.model.ProductQuantitiesCategory;
import com.fssa.agrokart.model.ProductStatus;
import com.fssa.agrokart.model.ProductStockUnits;
import com.fssa.agrokart.model.SubOrderItems;

public class OrderValidator {

	public boolean validateOrder(List<SubOrderItems> orderItems, List<Product> productDetails,
			List<ProductQuantitiesCategory> qtyCate) throws InvalidInputException {

		checkAvblOfProduct(orderItems, productDetails);

		checkProductWithinStock(orderItems, productDetails, qtyCate);

		return true;
	}

	public boolean checkAvblOfProduct(List<SubOrderItems> orderItems, List<Product> productDetails)
			throws InvalidInputException {

		// Create a map to store product availability status
		Map<Integer, Boolean> productAvailability = new HashMap<>();

		// Initialize product availability status based on productDetails
		for (Product product : productDetails) {
			boolean isAvailable = product.getStatus().equals(ProductStatus.AVAILABLE);
			productAvailability.put(product.getId(), isAvailable);
		}
	

		// Check if each product in orderItems is available
		for (SubOrderItems subOrderItem : orderItems) {
			int productId = subOrderItem.getProductId();

			if (!productAvailability.containsKey(productId)) {
				throw new InvalidInputException("Invalid product ID in orderItems: " + productId);
			}

			boolean isAvailable = productAvailability.get(productId);

			if (!isAvailable) {
				throw new InvalidInputException("Sorry some of the product not available check in the cart page.");
			}
		}

		return true; // All products are available
	}

	public boolean checkProductWithinStock(List<SubOrderItems> orderItems, List<Product> productDetails,
			List<ProductQuantitiesCategory> qtyCate) throws InvalidInputException {

		Map<Integer, Double> avblStocks = new HashMap<>();
		Map<Integer, ProductQuantitiesCategory> qtyMap = new HashMap<>();

		for (Product product : productDetails) {

			avblStocks.put(product.getId(), product.getAvailableStock().getNum());
		}

		for (ProductQuantitiesCategory qty : qtyCate) {

			qtyMap.put(qty.getId(), qty);

		}

		for (SubOrderItems order : orderItems) {

			ProductQuantitiesCategory qty = qtyMap.get(order.getQtyId());

			double avblStock = avblStocks.get(order.getProductId())
					* (qty.getUnit().equals(ProductStockUnits.KG) || qty.getUnit().equals(ProductStockUnits.GM) ? 1000
							: 1);

			double getQty = order.getQtyNos()
					* (qty.getUnit().equals(ProductStockUnits.KG) ? qty.getWeight() * 1000 : qty.getWeight());

			if (getQty >= avblStock) {
				throw new InvalidInputException(
						"Some of the products are quantities are greater than available stock. Please check in the cart page. Before proceeding with order.");
			}

		}

		return true;
	}

}
