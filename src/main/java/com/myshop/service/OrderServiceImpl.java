package com.myshop.service;

import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.repository.DrinkRepository;
import com.myshop.repository.OrderRepository;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public Order save(Order order) throws NotEnoughProductInStorage {
        if (order.isNew())
        {
            for (OrderDetails item: order.getOrders()){
                if (item.getDrinkQuantity() > item.getDrink().getQuantity()){
                    throw new NotEnoughProductInStorage();
                }
                else {
                    drinkRepository.take(item.getDrink(), item.getDrinkQuantity());
                }
            }
        }
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
