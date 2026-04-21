package com.ecommerce.service;

import com.ecommerce.enums.OrderStatus;

import java.util.Map;

public interface OrderService {

    Map<OrderStatus, Long> countOrdersByStatus();
}
