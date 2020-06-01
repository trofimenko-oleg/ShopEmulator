package com.myshop.service;

import com.myshop.domain.Drink;

public interface PurchaseService {
    void buy(Drink drink, int amount);
}
