package com.myshop.simulator;

import com.myshop.ApplicationContextUtils;
import com.myshop.domain.Drink;
import com.myshop.service.*;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.slf4j.LoggerFactory.getLogger;

public class Customer {
    private static final Logger log = getLogger(Customer.class);
    private DrinkService drinkService = ApplicationContextUtils.getApplicationContext().getBean(DrinkService.class);
    public static List<Drink> DRINKS;
    public static int count = 0;

    public Map<Drink, Integer> getDrinks() {
        if (DRINKS == null) {
            DRINKS = drinkService.getAll();
        }
        if (count == 0) {
            count = DRINKS.size();
        }
        Random random = new Random();
        Map<Drink, Integer> map = new HashMap<>();
        //будем выбирать до 4 видов товара в общем количестве от 0 до 10
        map.put(DRINKS.get(random.nextInt(count)), random.nextInt(2));
        map.merge(DRINKS.get(random.nextInt(count)), random.nextInt(3), Integer::sum);
        map.merge(DRINKS.get(random.nextInt(count)), random.nextInt(4), Integer::sum);
        map.merge(DRINKS.get(random.nextInt(count)), random.nextInt(5), Integer::sum);
        return map;
    }

    public List<Drink> getDrinksInstance() {
        return DRINKS;
    }

    public Integer getDrinksCount() {
        return count;
    }

}
