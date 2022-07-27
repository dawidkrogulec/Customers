package com.dawid.customers.controller;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    class CustomerControllerTest {

        @Mock
        CustomerRepository repository;

        @InjectMocks
        CustomerController controller;


        @Test
        void shouldReturnAllCustomers() {
            //given
            //when
            when(repository.findAll()).thenReturn((Fixtures.CUSTOMER_LIST));
            assertEquals(controller.findAllCustomers(), Fixtures.CUSTOMER_NOT_FOUND_MSG);


            //then
        }

        @Test
        void shouldNotUpdateCustomer() {
            //given
            when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_NOT_FOUND);
            //when
            assertEquals(controller.updateCustomer(any(), Fixtures.CUSTOMER_WITH_NO_ID),Fixtures.CUSTOMER_UPDATED_MSG);
            //then
            verify(repository, times(1)).findById(any());
            verify(repository, times(0)).save(any());
        }

        @Test
        void shouldUpdateCustomer() {
            //given
            when(repository.findById(any())).thenReturn(Optional.of(Fixtures.CUSTOMER_FOUND));
            //when
            assertEquals(controller.updateCustomer(any(), Fixtures.CUSTOMER_WITH_NO_ID), Fixtures.CUSTOMER_UPDATED_MSG);
            //then
            verify(repository, times(1)).findById(any());
            verify(repository, times(1)).save(any());

        }
        @Test
        void shouldReturnZeroCustomers() {
            //given
            //when
            when(repository.findAll()).thenReturn(Fixtures.NO_CUSTOMERS_FOUND);
            List<Customer> result = controller.findAllCustomers();
            assertEquals(0, result.size());
        }
    private static class Fixtures{

        private final static Customer CUSTOMER_FOUND = new Customer(3L, "Dawid", "Krogulec", 39);
        private final static Customer CUSTOMER_WITH_NO_ID = new Customer("Dawid", "Krogulec", 39);
        private final static List<Customer> NO_CUSTOMERS_FOUND = new ArrayList<>();
        //listę customerów
        private static final  List<Customer> CUSTOMER_LIST = Arrays.asList(new Customer(0L, "Łukasz", "Curzydło", 33),
                new Customer(1L, "Karol", "Krogulec", 37),
                new Customer(2L, "Dawid", "Krogulec", 40));

        private static final Optional<Customer> CUSTOMER_NOT_FOUND = Optional.empty();


        private final static String CUSTOMER_NOT_FOUND_MSG = "Customer not found";
        private final static String CUSTOMER_UPDATED_MSG = "Customer updated succesfully";
    }
}
