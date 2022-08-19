package com.dawid.customers.integration;

import com.dawid.customers.controller.CustomerController;
import com.dawid.customers.model.Customer;
import com.dawid.customers.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void confirmCustomerAddedUserToH2() throws Exception {
        Customer customer = new Customer("Karol", "Krogulec", 37);

        mockMvc.perform(post("/api/customers/customer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());

        List<Customer> customerList = customerRepository.findCustomersByFirstName("Karol");
        assertThat(customerList.get(0).getFirstName()).isEqualTo("Karol");
    }
}