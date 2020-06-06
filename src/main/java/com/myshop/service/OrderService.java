package com.myshop.service;

import com.myshop.domain.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    void delete(int id);
    Order get(int id);
    List<Order> getAll();
}
