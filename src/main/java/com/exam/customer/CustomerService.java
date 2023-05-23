package com.exam.customer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exam.customer.dto.Customer;
import com.exam.customer.dto.CustomerDTO;
import com.exam.customer.exception.ApplicationException;
import com.exam.customer.repository.CustomerRepository;
import com.exam.mobile.dto.MobileInfoDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * This method is used to add the new customer
	 * 
	 * @param customerDTO
	 * @throws Exception
	 */
	public Boolean addCustomer(CustomerDTO customerDTO) throws ApplicationException {

		log.debug("Start adding customer: " + customerDTO.toString());
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/mobile-operations/";

		String encodedMobileNumber = URLEncoder.encode(customerDTO.getMobileNumber(), StandardCharsets.UTF_8);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("mobileNumber",
				encodedMobileNumber);
		// Make a GET request
		log.debug("Validate mobile number: " + customerDTO.getMobileNumber());
		MobileInfoDTO response = restTemplate.getForObject(builder.toUriString(), MobileInfoDTO.class);
		if (response == null) {
			throw new ApplicationException("Invalid Phone number.");
		}
		try {
			Customer customer = new Customer(customerDTO.getId() != null ? customerDTO.getId() : null,
					customerDTO.getName(), customerDTO.getAddress(), customerDTO.getMobileNumber());

			customerRepository.save(customer);
		} catch (Exception e) {
			log.error("Error while adding customer", e);
			throw new ApplicationException("Error while adding customer", e);
		}

		return true;

	}

	/**
	 * this method is used to update the customer
	 * 
	 * @param customerDatabean
	 * @return
	 */
	public boolean updateCustomer(Long id, CustomerDTO customerDTO) throws ApplicationException {

		log.debug("updating customer: " + customerDTO.toString());
		try {

			customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

			addCustomer(customerDTO);

		} catch (Exception e) {
			log.error("Error while updating customer: " + customerDTO.getId(), e);
			throw new ApplicationException("Error while updating customer: " + customerDTO.getId(), e);
		}
		return true;
	}

	/**
	 * this method is used to delete transaction
	 * 
	 * @param id
	 * @throws ApplicationException
	 */
	public Boolean deleteCustomer(Long id) throws ApplicationException {
		log.debug("deleting customer: " + id);
		try {
			customerRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Error while deleting customer: " + id, e);
			throw new ApplicationException("Error while updating customer: " + id, e);
		}
		return true;
	}

	/**
	 * this method is used to get all Customer
	 * 
	 * @return
	 * @throws ApplicationException
	 */
	public List<CustomerDTO> getAllCustomer() throws ApplicationException {
		List<CustomerDTO> listCustomer = new ArrayList<>();
		try {
			List<Customer> customers = customerRepository.findAll();

			for (Customer customer : customers) {
				CustomerDTO customerDTO = CustomerDTO.builder().name(customer.getName()).address(customer.getAddress())
						.mobileNumber(customer.getMobileNumber()).id(customer.getId()).build();
				listCustomer.add(customerDTO);

			}
		} catch (Exception e) {
			log.error("Error while getting all customer. ", e);
			throw new ApplicationException("Error while getting all customer. ", e);
		}

		return listCustomer;

	}

}
