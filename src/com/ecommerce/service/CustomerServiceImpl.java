package com.ecommerce.service;

import com.ecommerce.exception.CustomerExistsException;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getCustomerById(int id) throws CustomerNotFoundException {
        return customerRepository.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
    }

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerExistsException {
        customerRepository.getById(customer.getId()).ifPresent(existingCustomer -> {
            throw new IllegalArgumentException("Customer with id " + customer.getId() + " already exists");
        });
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        customerRepository.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        return customerRepository.update(id, customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + id + " not found"));
        customerRepository.delete(id);
    }
}
