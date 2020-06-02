package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.util.exception.NotEnoughProductInStorage;


public interface PurchaseService {
    void buy(Drink drink, int quantity) throws NotEnoughProductInStorage;
}
