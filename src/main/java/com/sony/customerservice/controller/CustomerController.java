package com.sony.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sony.customerservice.entity.Customer;
import com.sony.customerservice.entity.CustomerList;
import com.sony.customerservice.generator.MyGenerator;
import com.sony.customerservice.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerservice;

	@GetMapping
	public CustomerList handleGetAllCustomer() {
		return new CustomerList(customerservice.getAllCustomers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> handleGetProductById(@PathVariable String id) {
		Customer customer = customerservice.getCustomerById(id);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> handlePutCustomer(@PathVariable String id, @RequestBody Customer customer) {
		customer = customerservice.putCustomer(id, customer);
		if (customer != null) {
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> handlePost(@RequestBody Customer customer) {
//			customer = MyGenerator.sendForIdGeneration(customer);
		try {
			customer = customerservice.addNewCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

}
