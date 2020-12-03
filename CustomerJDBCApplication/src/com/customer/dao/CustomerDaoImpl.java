package com.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.customer.controller.ConnectionFactory;
import com.customer.exception.DaoException;
import com.customer.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	Customer customer = null;
	List<Customer> customerList = null;

	public CustomerDaoImpl() {
		customerList = new ArrayList<>();
		customer = new Customer();
	}

	@Override
	public List<Customer> getSelectedCustomer(long purchaseCapacity) throws DaoException {
		Connection conn = ConnectionFactory.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from customer where purchase_capacity = ?");
			pstmt.setLong(1, purchaseCapacity);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				customer = new Customer();
				
				customer.setCustId(rs.getInt("cust_id"));
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				customer.setBirthDate(rs.getDate("birth_date"));
				customer.setAddress(rs.getString("address"));
				customer.setPurchaseCapacity(rs.getLong("purchase_capacity"));
				
				customerList.add(customer);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error while fetching the details", e);
		}

		return customerList;
	}

	@Override
	public List<Customer> getAllCustomers() throws DaoException {
		Connection conn = ConnectionFactory.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from customer");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				customer = new Customer();

				customer.setCustId(rs.getInt("cust_id"));
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				customer.setBirthDate(rs.getDate("birth_date"));
				customer.setAddress(rs.getString("address"));
				customer.setPurchaseCapacity(rs.getLong("purchase_capacity"));
				customerList.add(customer);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Error while fetching the details", e);
		}

		return customerList;
	}

	@Override
	public void addCustomer(Customer customer) throws DaoException {
		Connection conn = ConnectionFactory.getConnection();
		System.out.println(customer.getBirthDate());
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into customer (name, phone, email, birth_date, address, purchase_capacity) values(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getPhone());
			pstmt.setString(3, customer.getEmail());
			pstmt.setDate(4, customer.getBirthDate());
			pstmt.setString(5, customer.getAddress());
			pstmt.setLong(6, customer.getPurchaseCapacity());
			
			pstmt.executeUpdate();
			System.out.println("customer added successfully..!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCustomer(int custId) throws DaoException {
		Connection conn = ConnectionFactory.getConnection();
		getCustomerById(custId);
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from customer where cust_id = ?");
			pstmt.setInt(1, custId);
			pstmt.executeUpdate();
			System.out.println("customer deleted successfully...!");
		} catch (SQLException e) {
			throw new DaoException("Error while deleting the details by id", e);
		}

	}

	@Override
	public void updateCustomer(int custId, long purchaseCapacity) throws DaoException {
		Connection conn = ConnectionFactory.getConnection();
		getCustomerById(custId);
		try {
			PreparedStatement pstmt = conn.prepareStatement("update customer set purchase_capacity=? where cust_id = ?");
			pstmt.setLong(1, purchaseCapacity);
			pstmt.setInt(2, custId);
			pstmt.executeUpdate();
			System.out.println("customer details updated successfully...!");
		} catch (SQLException e) {
			throw new DaoException("Error while updating the details by id", e);
		}
	}

	@Override
	public Customer getCustomerById(int custId) throws DaoException {
		Connection conn = ConnectionFactory.getConnection();
		Customer customerById = new Customer();

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from customer where cust_id = ?");
			pstmt.setInt(1, custId);
			ResultSet rs = pstmt.executeQuery();
			
			 if(rs.next()) {
				
				customerById.setCustId(rs.getInt("cust_id"));
				customerById.setName(rs.getString("name"));
				customerById.setPhone(rs.getString("phone"));
				customerById.setEmail(rs.getString("email"));
				customerById.setBirthDate(rs.getDate("birth_date"));
				customerById.setAddress(rs.getString("address"));
				customerById.setPurchaseCapacity(rs.getLong("purchase_capacity"));
			 } else {
				 throw new DaoException("invalid customer with id: "+ custId);
			 }
			 
		} catch (SQLException e) {
			throw new DaoException("Error while fetching the details by id", e);
		}

		return customerById;
	}

}