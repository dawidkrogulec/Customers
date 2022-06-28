package com.dawid.customers.repository;

import com.dawid.customers.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findById(long id);


    List<Customer> findCustomersByAge(Integer age);

    List<Customer> findCustomersByFirstName(String firstname);
}
