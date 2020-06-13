package com.myshop;

import com.myshop.service.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Simulator {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            DrinkService drinkService = appCtx.getBean(DrinkServiceImpl.class);
            OrderService orderService = appCtx.getBean(OrderServiceImpl.class);
            ShortenedOrderItemService shortenedOrderItemService = appCtx.getBean(ShortenedOrderItemServiceImpl.class);
            RefillingService refillingService = appCtx.getBean(RefillingServiceImpl.class);
        }
    }
}
