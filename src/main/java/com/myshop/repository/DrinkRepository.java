package com.myshop.repository;

import com.myshop.domain.Drink;
import com.myshop.util.exception.NotEnoughProductInStorage;

import java.util.List;

public interface DrinkRepository<T extends Drink> {

    T save(T drink);
    void delete(int id);
    T get(int id);
    List <T> getByPartOfName(String searchString);
    List<T> getAll();

    void add(T drink, int quantity);

    void take(T drink, int quantity) throws NotEnoughProductInStorage;
}
