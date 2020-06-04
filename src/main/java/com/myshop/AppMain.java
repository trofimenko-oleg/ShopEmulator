package com.myshop;

import com.myshop.domain.*;
import com.myshop.service.DrinkService;
import com.myshop.service.DrinkServiceImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;


public class AppMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml")) {
            DrinkService drinkService = appCtx.getBean(DrinkServiceImpl.class);
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

          //  Drink drink = new AlcoholicDrink("Вино", 15.20, 0.67, 10, AlcoholicGroup.WINE, 12);
         //   drinkService.save(drink);
         //   Drink drink2 = new NonAlcoholicDrink("Минералка", 15.20, 0.67, 10, NonAlcoholicGroup.MINERAL_WATER, "Ну очень вкусная минералка");
         //   drinkService.save(drink2);
            List<Drink> drinks = drinkService.getAll();
            for (Drink drink1: drinks)
            {
                System.out.println(drink1);
            }
         //   List<Drink> list = drinkService.getByPartOfName("минерал");
         //   for (Drink drink1: list)
         //   {
         //       System.out.println(drink1);
         //   }
         //   List<Drink> list2 = drinkService.getByPartOfName("вин");
        //    for (Drink drink1: list2)
        //    {
        //        System.out.println(drink1);
         //   }
        }
    }
}
