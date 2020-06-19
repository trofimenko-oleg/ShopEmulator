package com.myshop.repository;

import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    void delete(int id);
    Order get(int id);
    List<Order> getAll();
    OrderDetails getItem(int orderDetailsId);
}
