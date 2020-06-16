package com.myshop;

import com.myshop.domain.*;
import com.myshop.repository.OrderRepository;
import com.myshop.repository.OrderRepositoryImpl;
import com.myshop.service.*;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.simulator.Simulator;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AppMain {
    public static void main(String[] args)  {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            //DrinkService drinkService = appCtx.getBean(DrinkServiceImpl.class);
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            OrderService orderService = appCtx.getBean(OrderService.class);
            ShortenedOrderItemService shortenedOrderItemService = appCtx.getBean(ShortenedOrderItemService.class);
            ApplicationContextUtils applicationContextUtils = new ApplicationContextUtils();
            applicationContextUtils.setApplicationContext(appCtx);
            try {
                try {
                    Simulator.startSimulator(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NotEnoughProductInStorage notEnoughProductInStorage) {
                notEnoughProductInStorage.printStackTrace();
            }
            //  Drink drink = new AlcoholicDrink("Вино", 15.20, 0.67, 10, AlcoholicGroup.WINE, 12);
         //   drinkService.save(drink);
         //   Drink drink2 = new NonAlcoholicDrink("Минералка", 15.20, 0.67, 10, NonAlcoholicGroup.MINERAL_WATER, "Ну очень вкусная минералка");
         //   drinkService.save(drink2);
//            List<Drink> drinks = drinkService.getAll();
//            for (Drink drink1: drinks)
//            {
//                System.out.println(drink1);
//            }
//            List<ShortenedOrderItem> list = shortenedOrderItemService.getItems(orderService.get(2));
//            System.out.println(list);
//
//
//            Order order = new Order();
//            OrderDetails orderDetails = new OrderDetails(order, drinks.get(0), 6, 15.20);
//            OrderDetails orderDetails2 = new OrderDetails(order, drinks.get(1), 7, 14.40);
//            OrderDetails orderDetails3 = new OrderDetails(order, drinks.get(4), 1, 10.99);
//            order.setOrders(new ArrayList<OrderDetails>(){{
//                add(orderDetails);
//                add(orderDetails2);
//                add(orderDetails3);
//            }});
//            order.setDayOfWeek(DayOfWeek.FRIDAY);
//            order.setLocalDate(LocalDate.now());
//            order.setShippingInfo("bla");
//            order.setTime(LocalTime.now());
//            double checkValue = (orderDetails.getCostWithoutMarkup() + orderDetails2.getCostWithoutMarkup() + orderDetails3.getCostWithoutMarkup())*1.1;
//            order.setTotalCheckValue((Math.round(checkValue*100))/100);
//            orderService.save(order);
//            System.out.println(orderService.get(1).getTotalCheckValue());
//            Order second = new Order(order);
//            second.setTotalCheckValue(23566);
//            orderService.save(second);



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
