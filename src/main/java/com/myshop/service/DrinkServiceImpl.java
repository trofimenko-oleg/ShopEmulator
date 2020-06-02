package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

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

    @Override
    public void take(Drink drink, int quantity) throws NotEnoughProductInStorage {
        drinkRepository.take(drink, quantity);
    }
}
