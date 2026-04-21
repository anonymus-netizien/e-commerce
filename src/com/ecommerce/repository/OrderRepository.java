package com.ecommerce.repository;

import com.ecommerce.enums.OrderStatus;
import com.ecommerce.model.Order;
import com.ecommerce.util.CsvParser;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final List<Order> orders;

    public OrderRepository() throws IOException {
        CsvParser csvParser = new CsvParser();
        this.orders = csvParser.getOrdersFromCsv();
    }

    public List<Order> findAll() {
        return orders;
    }

    public Optional<Order> findById(int id) {
        return this.orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }

    public Order save(Order order) {
        this.orders.add(order);
        return order;
    }

    public Optional<Order> update(int id, OrderStatus newStatus) {
        return this.orders.stream().filter(order -> order.getId() == id).findFirst().map(order -> {
            order.setStatus(newStatus);
            return order;
        });
    }

    public boolean delete(int id) {
        return this.orders.removeIf(order -> order.getId() == id);
    }
}
