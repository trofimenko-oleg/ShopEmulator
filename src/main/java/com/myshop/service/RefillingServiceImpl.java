package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;

import java.util.List;

public class RefillingServiceImpl implements RefillingService{

    private final DrinkRepository drinkRepository;

    public RefillingServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public void refill() {
        List<Drink> drinkList = drinkRepository.getAll();
        for (Drink drink: drinkList)
        {
            if (drink.getQuantity() < 10)
            {
                drinkRepository.add(drink, 150);
            }
        }
    }
}
