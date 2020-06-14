package com.myshop.simulator;

import com.myshop.domain.Drink;
import com.myshop.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.slf4j.LoggerFactory.getLogger;
@Controller
public class Customer {
private static final Logger log = getLogger(Customer.class);

    @Autowired
    private DrinkService drinkService;

    public static List<Drink> DRINKS;
    public static int count = 0;
    public Map<Drink, Integer> getDrinks()
    {
        if (DRINKS == null){
            DRINKS = drinkService.getAll();
        }
        if (count == 0){
            count = DRINKS.size();
        }
        Random random = new Random();
        Map map = new HashMap();
        //будем выбирать до 4 видов товара в общем количестве от 0 до 10
        for (int i = 0; i < 4; i++){
            map.put(DRINKS.get(random.nextInt(count)), random.nextInt(2));
            map.put(DRINKS.get(random.nextInt(count)), random.nextInt(3));
            map.put(DRINKS.get(random.nextInt(count)), random.nextInt(4));
            map.put(DRINKS.get(random.nextInt(count)), random.nextInt(5));
        }
        return map;
    }

    public Customer() {
    }

    public List<Drink> getDrinksInstance()
    {
        return DRINKS;
    }

    public Integer getDrinksCount()
    {
        return count;
    }

}
