package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@EnableScheduling //not needed?
@Transactional
public class RefillingServiceImpl implements RefillingService{
    private static final Logger log = getLogger(RefillingServiceImpl.class);

    private final DrinkRepository drinkRepository;

    public RefillingServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public Map<Drink, Integer> refill() {
        int refilledCount = 150;
        Map<Drink,Integer> drinksToRefill = new HashMap<>();
        List<Drink> drinkList = drinkRepository.getAll();
        for (Drink drink: drinkList)
        {
            if (drink.getQuantity() < 10)
            {
                drinkRepository.add(drink, refilledCount);
                drinksToRefill.put(drink, refilledCount);
                log.debug("150 units of {} was added to store", drink.getName());
            }
        }
        return drinksToRefill;
    }
}
