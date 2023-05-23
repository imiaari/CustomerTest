package com.exam.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.customer.dto.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}