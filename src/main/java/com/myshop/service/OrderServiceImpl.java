package com.myshop.service;

import com.myshop.domain.Order;
import com.myshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    @Override
    public Order get(int id) {
        return orderRepository.get(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.getAll();
    }
}
