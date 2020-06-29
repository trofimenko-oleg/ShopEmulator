package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@Transactional
public class RefillingServiceImpl implements RefillingService {
    private static final Logger log = getLogger(RefillingServiceImpl.class);
    private static int defaultQuantity = 150;

    private final DrinkRepository drinkRepository;

    public RefillingServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Map<Drink, Integer> refill() {
        return refill(defaultQuantity);
    }

    @Override
    public Map<Drink, Integer> refill(int quantity) {
        Map<Drink, Integer> refilledDrinks = new HashMap<>();
        List<Drink> drinkList = drinkRepository.getAll();
        for (Drink drink : drinkList) {
            if (drink.getQuantity() < 10 && quantity > 0) {
                drinkRepository.add(drink, quantity);
                refilledDrinks.put(drink, quantity);
                drinkRepository.save(drink);
                log.debug("{} units of {} was added to store", quantity, drink.getName());
            }
        }
        return refilledDrinks;
    }
}
