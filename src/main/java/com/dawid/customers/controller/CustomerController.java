package com.dawid.customers.controller;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<Customer> findAllCustomers() {

        return customerRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Customer findCustomerById(@PathVariable(value = "id") long id) {

        return customerRepository.findById(id);
    }

    @PostMapping("/customer")
    public Customer saveUser(@Validated @RequestBody Customer customer) {

        return customerRepository.save(customer);
    }

    @GetMapping("/age/{age}")
    public List<Customer> findCustomerByAge(@PathVariable(value = "age") Integer age) {

        return customerRepository.findCustomersByAge(age);
    }

    @GetMapping("/firstname")
    public List<Customer> findCustomerByFirstName(String firstName) {

        return customerRepository.findCustomersByFirstName(firstName);
    }
    @DeleteMapping("/firstName")
    public List<Customer> deleteCustomerByFirstName(String firstName){
        return customerRepository.deleteCustomersByFirstName(firstName);
    }
    @PutMapping("/id/{id}")
    public List<Customer> updateCustomerById(Long id){
        return customerRepository.updateCustomersById(id);
    }
}