package com.dawid.customers.controller;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
@Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/all")
    public List<Customer> findAllUsers() {

        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findUserById(@PathVariable(value = "id") long id) {

        return null;
    }
    @PostMapping
    public Customer saveUser(@Validated @RequestBody Customer customer) {

        return customer;
    }
}