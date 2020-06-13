package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import com.myshop.service.to.OrderForm;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService{

    private final DrinkRepository drinkRepository;

    public PurchaseServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public void buy(Drink drink, int quantity) throws NotEnoughProductInStorage {
        int maximumQuantity = drinkRepository.get(drink.getId()).getQuantity();
        if (quantity > maximumQuantity) {
            throw new NotEnoughProductInStorage();
        }
        else {
            drinkRepository.take(drink, quantity);
        }
    }

    @Override
    public void buy(OrderForm orderForm) throws NotEnoughProductInStorage {
        buy(orderForm.getOrderItems());
    }

    @Override
    public void buy(List<ShortenedOrderItem> shortenedOrderItemList) throws NotEnoughProductInStorage {
        for (ShortenedOrderItem item: shortenedOrderItemList)
        {
            drinkRepository.take(item.getDrink(), item.getQuantity());
        }
    }
}
