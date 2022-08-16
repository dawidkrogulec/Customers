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
import static org.junit.jupiter.api.Assertions.assertNull;
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
        List<Customer> result = controller.findAllCustomers();
        assertEquals(3,result.size());


        //then
    }
    @Test
    void shouldReturnZeroCustomers() {
        //given
        //when
        when(repository.findAll()).thenReturn(Fixtures.NO_CUSTOMERS_FOUND);
        List<Customer> result = controller.findAllCustomers();
        assertEquals(0, result.size());
    }
    @Test
    void shouldNotUpdateCustomer() {
        //given
        when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_NOT_FOUND);
        //when
        assertEquals(Fixtures.CUSTOMER_NOT_FOUND_MSG, controller.updateCustomer(1L, Fixtures.CUSTOMER_WITH_NO_ID));
        //then
        verify(repository, times(1)).findById(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    void shouldUpdateCustomer() {
        //given
        when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_FOUND);
        //when
        assertEquals(Fixtures.CUSTOMER_UPDATED_MSG, controller.updateCustomer(any(), Fixtures.CUSTOMER_WITH_NO_ID));
        //then
        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());

    }
    @Test
    void shouldReturnCustomerById(){

        //given
        when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_FOUND);
        //when
        Customer customer = controller.findCustomerById(any());
        //then
        assertEquals(Fixtures.CUSTOMER_WITH_ID.getId(), customer.getId());
        assertEquals(Fixtures.CUSTOMER_WITH_ID.getAge(), customer.getAge());
        assertEquals(Fixtures.CUSTOMER_WITH_ID.getFirstName(), customer.getFirstName());
        assertEquals(Fixtures.CUSTOMER_WITH_ID.getLastName(), customer.getLastName());

    }
    @Test
    void shouldNotReturnCustomerById(){
        //given
        when(repository.findById(any())).thenReturn(Fixtures.CUSTOMER_NOT_FOUND);
        //when
        Customer customer = controller.findCustomerById(any());
        //then
        assertNull(customer);
    }

    @Test
    void shouldReturnCustomersByAge(){
        //given
        when(repository.findCustomersByAge(55)).thenReturn(Fixtures.CUSTOMER_LIST_SAME_AGE);
        //when
        List<Customer> customers = controller.findCustomerByAge(55);
        //then
        assertEquals(2, customers.size());
        assertEquals(55, customers.get(0).getAge());
        assertEquals(55, customers.get(1).getAge());
        verify(repository, times(1)).findCustomersByAge(any());

    }

    @Test
    void shouldNotReturnCustomersByAge(){
        //given
        when(repository.findCustomersByAge(99)).thenReturn(Fixtures.NO_CUSTOMERS_FOUND);
        //when
        List<Customer> customers = controller.findCustomerByAge(99);
        //then
        assertEquals(0, customers.size());
        verify(repository, times(1)).findCustomersByAge(99);
    }

    @Test
    void shouldReturnCustomersByFirstName(){
        //given
        when(repository.findCustomersByFirstName("Robert")).thenReturn(Fixtures.CUSTOMER_LIST_SAME_FIRST_NAME);
        //when
        List<Customer> customers = controller.findCustomerByFirstName("Robert");
        //then
        assertEquals(2, customers.size());
        assertEquals("Robert", customers.get(0).getFirstName());
        assertEquals("Robert", customers.get(1).getFirstName());
        verify(repository, times(1)).findCustomersByFirstName(any());
    }

    @Test
    void shouldNotReturnCustomersByFirstName(){
        //given
        when(repository.findCustomersByFirstName("Dawid")).thenReturn(Fixtures.NO_CUSTOMERS_FOUND);
        //when
        List<Customer> customers = controller.findCustomerByFirstName("Dawid");
        //then
        assertEquals(0, customers.size());
        verify(repository, times(1)).findCustomersByFirstName(any());
    }

    @Test
    void shouldThrowExceptionOnInvalidFirstName(){  //dokończyć i dodać też Fixtures
        //given
        when(repository.findCustomersByFirstName("Robert")).thenReturn(Fixtures.CUSTOMER_LIST_SAME_FIRST_NAME);
        //when
        List<Customer> customers = controller.findCustomerByFirstName("Robert");
        //then
        assertEquals(2, customers.size());
        assertEquals("Robert", customers.get(0).getFirstName());
        assertEquals("Robert", customers.get(1).getFirstName());
        verify(repository, times(1)).findCustomersByFirstName(any());
    }

    private static class Fixtures{ //dodać do Fixtures ostatni scenariusz
        private final static Optional<Customer> CUSTOMER_FOUND = Optional.of(new Customer(3L, "Dawid", "Krogulec", 39));
        private final static Customer CUSTOMER_WITH_NO_ID = new Customer("Dawid", "Krogulec", 39);
        private final static List<Customer> NO_CUSTOMERS_FOUND = new ArrayList<>();

        private static final  List<Customer> CUSTOMER_LIST_SAME_AGE = Arrays.asList(new Customer(0L, "Jaś", "Fasola", 55),
                new Customer(1L, "Jasia", "Fasola", 55));

        private static final  List<Customer> CUSTOMER_LIST_SAME_FIRST_NAME = Arrays.asList(new Customer(0L, "Robert", "Lewandowski", 33),
                new Customer(1L, "Robert", "Kubica", 33));
        private static final  List<Customer> CUSTOMER_LIST = Arrays.asList(new Customer(0L, "Łukasz", "Curzydło", 33),
                new Customer(1L, "Karol", "Krogulec", 37),
                new Customer(2L, "Dawid", "Krogulec", 40));

        private static final Optional<Customer> CUSTOMER_NOT_FOUND = Optional.empty();
        private final static Customer CUSTOMER_WITH_ID = new Customer(3L, "Dawid", "Krogulec", 39);
        private final static String CUSTOMER_NOT_FOUND_MSG = "Customer with id: 1 not found";
        private final static String CUSTOMER_UPDATED_MSG = "Customer updated successfully";
    }
}