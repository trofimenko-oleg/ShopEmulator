package com.myshop.service;

import com.myshop.domain.Drink;

import java.util.List;

public interface DrinkService {
    Drink save(Drink drink);

    void delete(int id);

    Drink get(int id);

    List<Drink> getByPartOfName(String searchString);

    List<Drink> getAll();

    void add(Drink drink, int quantity);
}
