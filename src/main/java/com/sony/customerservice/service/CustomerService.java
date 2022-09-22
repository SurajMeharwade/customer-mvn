package com.sony.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sony.customerservice.entity.Customer;
import com.sony.customerservice.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;

	public List<Customer> getAllCustomers() 
	{
		return repo.findAll();
	}

	public Customer getCustomerById(String id)
	{
		Optional<Customer> customer = repo.findById(id);
		if(customer.isPresent())
			{
				return customer.get();
			}
		return null;
	}
	
	public Customer addNewCustomer(Customer customer) {
		customer = repo.save(customer);
		return customer;
	}

	public Customer putCustomer(String id, Customer customer) {
		customer.setCustomerId(id);
		return repo.save(customer);
	}
}
