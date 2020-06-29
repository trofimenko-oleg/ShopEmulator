package com.myshop.service;

import com.myshop.domain.Order;
import com.myshop.util.exception.NotEnoughProductInStorage;

import java.util.List;

public interface OrderService {
    Order save(Order order) throws NotEnoughProductInStorage;

    void delete(int id);

    Order get(int id);

    List<Order> getAll();
}
