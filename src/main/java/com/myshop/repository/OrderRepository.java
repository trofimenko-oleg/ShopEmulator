package com.myshop.repository;

import com.myshop.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order drink);
    void delete(int id);
    Order get(int id);
    List<Order> getAll();
}
