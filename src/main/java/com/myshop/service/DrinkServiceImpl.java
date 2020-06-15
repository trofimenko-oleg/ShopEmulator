package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import com.myshop.util.PriceUtil;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.myshop.util.FormattersUtil.round;

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
        List<Drink> allDrinks = drinkRepository.getAll();
        return allDrinks;
    }

    @Override
    public void add(Drink drink, int quantity) {
        drinkRepository.add(drink, quantity);
    }

//    @Override
//    @Transactional
//    public void take(Drink drink, int quantity) throws NotEnoughProductInStorage {
//        drinkRepository.take(drink, quantity);
//    }
}
