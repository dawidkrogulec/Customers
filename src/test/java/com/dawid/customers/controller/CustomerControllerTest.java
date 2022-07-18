package com.dawid.customers.controller;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerRepository repository;

    @Test
    void updateCustomerNotFound() {
        //given
        
        //when
        //than
    }

    @Test
    void updateCustomerFound() {
    }

    private static class Fixtures{
        private final static Optional<Customer> CUSTOMER_FOUND = Optional.of(new Customer(3L, "Dawid", "Krogulec", 39));
        private final static Optional<Customer> CUSTOMER_NOT_FOUND = Optional.empty();
    }
}