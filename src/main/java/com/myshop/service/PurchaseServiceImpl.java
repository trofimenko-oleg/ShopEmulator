package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import com.myshop.util.exception.NotEnoughProductInStorage;

public class PurchaseServiceImpl implements PurchaseService{

    private final DrinkRepository drinkRepository;

    public PurchaseServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public void buy(Drink drink, int quantity) throws NotEnoughProductInStorage {
        drinkRepository.take(drink, quantity);
    }
}
