package com.ecommerce.service;

import com.ecommerce.enums.OrderPriority;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;

import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public Map<OrderStatus, Long> countOrdersByStatus() {
        return orderRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }
}
