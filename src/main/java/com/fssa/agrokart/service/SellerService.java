package com.fssa.agrokart.service;

import java.util.List;
import com.fssa.agrokart.dao.SellerDAO;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.exception.ServiceException;
import com.fssa.agrokart.model.Seller;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

public class SellerService {

	SellerDAO sellerDAO = new SellerDAO();

	public List<Integer> getAllSellerID() throws ServiceException {

		try {
			return sellerDAO.getAllSellerID();
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

	public Seller getSeller(int id) throws ServiceException {

		try {
			return sellerDAO.getSeller(id);
		} catch (DAOException e) {
			ExceptionLoggerUtil.logException(e);
			throw new ServiceException(e.getMessage());
		}
	}

}
