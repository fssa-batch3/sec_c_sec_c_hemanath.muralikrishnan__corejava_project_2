package com.fssa.agrokart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.agrokart.exception.ConnectionException;
import com.fssa.agrokart.exception.DAOException;
import com.fssa.agrokart.model.Seller;
import com.fssa.agrokart.util.ConnectionUtil;
import com.fssa.agrokart.util.ExceptionLoggerUtil;

public class SellerDAO {

	public List<Integer> getAllSellerID() throws DAOException {
		List<Integer> sellerIDs = new ArrayList<>();
		String sql = "SELECT id FROM sellers";

		try (Connection conn = ConnectionUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int sellerID = rs.getInt("id");
				sellerIDs.add(sellerID);
			}
		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error retrieving seller IDs " + e.getMessage(), e);
		}

		return sellerIDs;
	}

	public Seller getSeller(int id) throws DAOException {

		String sql = "SELECT name,image_url FROM sellers WHERE id = ?";
		Seller seller = null;

		try (Connection conn = ConnectionUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {

					seller.setName(rs.getString("name"));
					seller.setImageUrl(rs.getString("image_url"));
				}
			}

		} catch (SQLException | ConnectionException e) {
			ExceptionLoggerUtil.logException(e);
			throw new DAOException("Error while retrieving seller details " + e.getMessage(), e);
		}

		return seller;
	}

}
