package com.exam.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.customer.CustomerService;
import com.exam.customer.dto.CustomerDTO;
import com.exam.customer.exception.ApplicationException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Operation(summary = "Add new customer", description = "Add new customer")
	@PostMapping
	public ResponseEntity<Boolean> addCustomer(@RequestBody CustomerDTO customer) throws ApplicationException  {

		try {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomer(customer));
		}catch (Exception e) {
			throw new ApplicationException(e.getMessage());
		}
	}

	@Operation(summary = "Update customer", description = "Update customer")
	@PutMapping("/{id}")
	public ResponseEntity<Boolean> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customer)
			throws ApplicationException {

		return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customer));
	}

	@Operation(summary = "delete customer", description = "delete customer")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> updateCustomer(@PathVariable Long id) throws ApplicationException {

		return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteCustomer(id));
	}

	@Operation(summary = "Get all customer", description = "list of customers")
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomer() throws ApplicationException {

		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomer());

	}

}
