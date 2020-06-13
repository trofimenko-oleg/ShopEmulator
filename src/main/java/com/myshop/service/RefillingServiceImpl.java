package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefillingServiceImpl implements RefillingService{

    private final DrinkRepository drinkRepository;

    public RefillingServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    @Scheduled(cron = "0 0 21 * * *")
    public void refill() {
        List<Drink> drinkList = drinkRepository.getAll();
        for (Drink drink: drinkList)
        {
            if (drink.getQuantity() < 10)
            {
                drinkRepository.add(drink, 150);
            }
        }
    }
}
