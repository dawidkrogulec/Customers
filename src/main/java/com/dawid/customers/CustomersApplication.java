package com.dawid.customers;

import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomersApplication {
    private static final Logger log = LoggerFactory.getLogger(CustomersApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer", 21));
            repository.save(new Customer("Chloe", "O'Brian", 22));
            repository.save(new Customer("Kim", "Bauer", 23));
            repository.save(new Customer("David", "Palmer", 24));
            repository.save(new Customer("Michelle", "Dessler", 25));

        };
        }
    }
