package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public Drink save(Drink drink) {
        return drinkRepository.save(drink);
    }

    @Override
    public void delete(int id) {
        drinkRepository.delete(id);
    }

    @Override
    public Drink get(int id) {
        return drinkRepository.get(id);
    }

    @Override
    public List<Drink> getByPartOfName(String searchString) {
        return drinkRepository.getByPartOfName(searchString);
    }

    @Override
    public List<Drink> getAll() {
        return drinkRepository.getAll();
    }

    @Override
    public void add(Drink drink, int quantity) {
        drinkRepository.add(drink, quantity);
    }
}
