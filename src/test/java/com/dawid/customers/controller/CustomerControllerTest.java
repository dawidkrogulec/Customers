package com.dawid.customers.controller;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerController controller;

    @Test
    void updateCustomerNotFound() {
        //given
        //when
        when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_NOT_FOUND);
        //then
        verify(controller.updateCustomer(any(), any()).equals(Fixtures.CUSTOMER_NOT_FOUND_MSG));
    }

    @Test
    void updateCustomerFound() {
    }

    private static class Fixtures{
        private final static Optional<Customer> CUSTOMER_FOUND = Optional.of(new Customer(3L, "Dawid", "Krogulec", 39));
        private final static Customer CUSTOMER_WITH_NO_ID = new Customer("Dawid", "Krogulec", 39);
        private final static Optional<Customer> CUSTOMER_NOT_FOUND = Optional.empty();
        private final static String CUSTOMER_NOT_FOUND_MSG = "Customer not found";
    }
}