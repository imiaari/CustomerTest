package com.exam.customer.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
public class Customer {

	public Customer() {
		// Default constructor
	}

	public Customer(Long id, String name, String address, String mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String mobileNumber;

}
