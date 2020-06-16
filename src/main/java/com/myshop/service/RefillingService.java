package com.myshop.service;

import com.myshop.domain.Drink;

import java.util.Map;

public interface RefillingService {
    Map<Drink, Integer> refill();
}
