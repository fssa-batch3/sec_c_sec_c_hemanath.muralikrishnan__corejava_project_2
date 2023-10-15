package com.fssa.agrokart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fssa.agrokart.dao.OrderDAO;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.exception.InvalidInputException;
import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.MainOrderHistory;
import com.fssa.agrokart.model.ProductQuantitiesCategory;
import com.fssa.agrokart.model.SubOrderItems;
import com.fssa.agrokart.util.ExceptionLoggerUtil;
import com.fssa.agrokart.validator.OrderValidator;

public class OrderService {

	OrderDAO orderDao = new OrderDAO();
	OrderValidator orderVal = new OrderValidator();

	public boolean BeforePayment(List<SubOrderItems> orderItems) throws ServiceException {

		try {
			return orderVal.validateOrder(orderItems, orderDao.findBasicDetails(orderItems),
					orderDao.findQtyCat(orderItems));
		} catch (InvalidInputException | DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public double getRs(List<SubOrderItems> orderItems) throws ServiceException {

		double total = 0;

		try {
			List<ProductQuantitiesCategory> listOfQty = orderDao.findQtyCat(orderItems);

			Map<Integer, Double> amount = new HashMap<>();

			for (ProductQuantitiesCategory ele : listOfQty) {

				amount.put(ele.getId(), ele.getRs());
			}

			for (SubOrderItems ele : orderItems) {

				double rs = amount.get(ele.getQtyId());

				total += rs * ele.getQtyNos();
			}

		} catch (DAOException e) {

			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

		return total;

	}

	public boolean placeOrder(MainOrderHistory order) throws ServiceException {

		try {
			return orderDao.OrderCreation(order);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public List<MainOrderHistory> readAllOrder(int userId) throws ServiceException {

		try {
			return orderDao.readAllOrderHistory(userId);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public boolean cancelOrder(int id, int userId) throws ServiceException {

		try {

			return orderDao.cancelOrder(id, userId);

		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}
	
	

}
