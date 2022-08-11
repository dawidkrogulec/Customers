package com.dawid.customers.controller;

import com.dawid.customers.exception.InvalidNameInput;
import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    private final static String DELETE_CONFIRMATION = "Customer deleted successfully";
    private final static String UPDATE_CONFIRMATION = "Customer updated successfully";
    private final static String CUSTOMER_NOT_FOUND = "Customer with id: %s not found";

    @GetMapping("/all")
    public List<Customer> findAllCustomers() {   //czy zwróci wszystkich i czy nie zwróci jeżeli pusta lista

        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    public Customer saveUser(@Validated @RequestBody Customer customer) {

        return customerRepository.save(customer);
    }

    @GetMapping("/age/{age}")
    public List<Customer> findCustomerByAge(@PathVariable(value = "age") Integer age) {

        return customerRepository.findCustomersByAge(age);
    }

    @GetMapping("/firstname/{firstName}")
    public List<Customer> findCustomerByFirstName(@PathVariable(value = "firstName") String firstName) {

        char[] input = firstName.toCharArray();
                for(char c : input) {
                    if (Character.isDigit(c)) {
                    throw new InvalidNameInput("FInvalid name provided");
                    }
                }
        return customerRepository.findCustomersByFirstName(firstName);
    }
    @DeleteMapping("/firstName")
    public String deleteCustomerByFirstName(String firstName){

        customerRepository.deleteCustomersByFirstName(firstName);

        return DELETE_CONFIRMATION;
    }
    @GetMapping("/id/{id}")
    public Customer findCustomerById(@PathVariable (value = "id")  Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return customer.get();
        } else {
            return null;
        }
    }
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable (value = "id")  Long id, @RequestBody Customer cus){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            Customer customerToBeSaved = cus;
            customerToBeSaved.setId(id);
            customerRepository.save(customerToBeSaved);
            return UPDATE_CONFIRMATION;
        } else {
            return String.format(CUSTOMER_NOT_FOUND, id);
        }
    }
}