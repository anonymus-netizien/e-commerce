package com.ecommerce.repository;

import com.ecommerce.model.Customer;
import com.ecommerce.util.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    private final List<Customer> customers;

    public CustomerRepository() throws IOException {
        CsvParser csvParser = new CsvParser();
        this.customers = csvParser.getCustomersFromCsv();
    }

    //Get all Customers
    public List<Customer> getAll() {
        return this.customers;
    }

    //Get Customer by ID
    public Optional<Customer> getById(int id) {
        return this.customers.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst();
    }

    //Save a new Customer
    public Customer save(Customer customer) {
        this.customers.add(customer);
        return customer;
    }

    //Update a Product
    public Customer update(int id, Customer customer) {
        this.customers.replaceAll(c -> c.getId() == id ? customer : c);
        return customer;
    }

    //Delete a Product
    public boolean delete(int id) {
        return this.customers.removeIf(customer -> customer.getId() == id);
    }

}
