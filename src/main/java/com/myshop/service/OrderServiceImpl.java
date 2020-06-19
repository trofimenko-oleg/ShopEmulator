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
        int toTakeFromStorage = 0;
            for (OrderDetails item: order.getOrders()){
                if (order.isNew()){
                    toTakeFromStorage = item.getDrinkQuantity();
                }
                else {
                    toTakeFromStorage = item.getDrinkQuantity() - orderRepository.getItem(item.getId()).getDrinkQuantity();
                }

                if (toTakeFromStorage > item.getDrink().getQuantity()){
                    throw new NotEnoughProductInStorage();
                }
                else {
                    if (toTakeFromStorage >= 0){
                        drinkRepository.take(item.getDrink(), toTakeFromStorage);
                    }
                    else {
                        drinkRepository.add(item.getDrink(), Math.abs(toTakeFromStorage));
                    }
                    drinkRepository.save(item.getDrink());
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
