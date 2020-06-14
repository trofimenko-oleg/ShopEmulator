package com.myshop.simulator;

import com.myshop.service.*;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Simulator {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml", "spring/spring-mvc.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            DrinkService drinkService = appCtx.getBean(DrinkServiceImpl.class);
            OrderService orderService = appCtx.getBean(OrderServiceImpl.class);
            ShortenedOrderItemService shortenedOrderItemService = appCtx.getBean(ShortenedOrderItemServiceImpl.class);
            RefillingService refillingService = appCtx.getBean(RefillingServiceImpl.class);
            Shop shop = new Shop();
            shop.toNextDay();
            for (int i = 0; i < 30; i ++)
            {
                try {
                    shop.workForOneDay();
                } catch (NotEnoughProductInStorage notEnoughProductInStorage) {
                    notEnoughProductInStorage.printStackTrace();
                }
            }


        }
    }
}
