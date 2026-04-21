package com.ecommerce.util;

import com.ecommerce.enums.Gender;
import com.ecommerce.enums.Membership;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.PaymentMethod;
import com.ecommerce.enums.Status;
import com.ecommerce.model.Address;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    /* Method to convert products.csv file into List of Products
        -Method Name
        -Arguments
        -return type
        -Access Modifier
     */

    public List<Product> getProductsFromCsv() throws IOException {
        List<Product> products = new ArrayList<>();
        File file = new File("/Users/VISHU/vishu/products.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String productData = br.readLine();

        while (productData != null) {
            String[] split = productData.split(",");
            Product product = new Product();
            product.setId(Integer.parseInt(split[0]))
                    .setName(split[1])
                    .setMaxRetailPrice(Integer.parseInt(split[2]))
                    .setDiscountPercentage(Float.parseFloat(split[3]))
                    .setAvailable(Boolean.parseBoolean(split[4]))
                    .setCompany(split[5])
                    .setCategory(split[6])
                    .setManufacturedYear(Integer.parseInt(split[7]));

            products.add(product);
            productData = br.readLine();
        }
        return products;
    }


    public List<Customer> getCustomersFromCsv() throws IOException {
        List<Customer> customers = new ArrayList<>();
        File file = new File("/Users/VISHU/vishu/customers.csv");

        //try used for auto closing BufferdReader after completion
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String customerData = br.readLine();
            while (customerData != null) {
                String[] split = customerData.split(",");

                Address residential = parseAddress(split[7]);

                Address shipping = parseAddress(split[8]);

                //Customer Data
                Customer customer = new Customer()
                        .setId(Integer.parseInt(split[0].trim()))
                        .setName(split[1].trim())
                        .setEmail(split[2].trim())
                        .setAge(Integer.parseInt(split[3].trim()))
                        .setGender(Gender.valueOf(split[4].trim().toUpperCase()))
                        .setStatus(Status.valueOf(split[5].trim().toUpperCase()))
                        .setMembershipType(Membership.valueOf(split[6].trim().toUpperCase()))
                        .setResdentialAddress(residential)
                        .setShippingAddress(shipping);

                customers.add(customer);
                customerData = br.readLine();
            }
        }
        return customers;
    }

    public List<Order> getOrdersFromCsv() throws IOException {
        List<Order> orders = new ArrayList<>();
        File file = new File("/Users/VISHU/vishu/orders.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String orderData = br.readLine();
            while (orderData != null) {
                String[] split = orderData.split(",");

                Order order = new Order()
                        .setId(Integer.parseInt(split[0].trim()))
                        .setStatus(OrderStatus.valueOf(split[1].trim().toUpperCase()))
                        .setPaymentMethod(PaymentMethod.valueOf(split[2].trim().toUpperCase()));

                orders.add(order);
                orderData = br.readLine();
            }
        }
        return orders;
    }

    public Address parseAddress(String address) {
        String[] split = address.split("\\|");
        if (split.length < 5) {
            throw new IllegalArgumentException("Invalid address: " + address);
        }
        Address addressObject = new Address();
        addressObject.setHouseNo(split[0].trim())
                .setStreet(split[1].trim())
                .setArea(split[2].trim())
                .setCity(split[3].trim())
                .setPincode(Integer.parseInt(split[4].trim()));

        return addressObject;
    }
}
