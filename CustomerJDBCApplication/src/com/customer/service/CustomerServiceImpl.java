package com.customer.service;

import java.util.List;

import com.customer.dao.CustomerDaoImpl;
import com.customer.exception.DaoException;
import com.customer.model.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	CustomerDaoImpl customerDao = null;
	
	public CustomerServiceImpl() {
		customerDao = new CustomerDaoImpl();
	}

	@Override
	public List<Customer> getSelectedCustomer(long purchaseCapacity) throws DaoException {
		
		return customerDao.getSelectedCustomer(purchaseCapacity);
	}

	@Override
	public List<Customer> getAllCustomers() throws DaoException {
		return customerDao.getAllCustomers();
	}

	@Override
	public void addCustomer(Customer customer) throws DaoException {
		customerDao.addCustomer(customer);
		
	}

	@Override
	public void deleteCustomer(int custId) throws DaoException {
		customerDao.deleteCustomer(custId);
	}

	@Override
	public void updateCustomer(int custId, long purchaseCapacity) throws DaoException {
		customerDao.updateCustomer(custId, purchaseCapacity);
	}

	@Override
	public Customer getCustomerById(int custId) throws DaoException {
		return customerDao.getCustomerById(custId);
	}
	
	
}