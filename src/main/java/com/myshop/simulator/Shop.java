package com.myshop.simulator;

import com.myshop.ApplicationContextUtils;
import com.myshop.domain.Drink;
import com.myshop.domain.Order;
import com.myshop.service.*;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.PriceUtil;
import com.myshop.util.TimeUtil;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;


public class Shop {
private static final Logger log = getLogger(Shop.class);
private static Clock clock = Clock.system(ZoneId.systemDefault());
private List<Customer> customerList = new ArrayList<>();

    private DrinkService drinkService = ApplicationContextUtils.getApplicationContext().getBean(DrinkService.class);
    private ShortenedOrderItemService shortenedOrderItemService = ApplicationContextUtils.getApplicationContext().getBean(ShortenedOrderItemService.class);
    private OrderService orderService = ApplicationContextUtils.getApplicationContext().getBean(OrderService.class);
    private PurchaseService purchaseService = ApplicationContextUtils.getApplicationContext().getBean(PurchaseService.class);
    private RefillingService refillingService = ApplicationContextUtils.getApplicationContext().getBean(RefillingService.class);


    //offset for next client
//    private Duration duration;

    public void toNextDay()
    {
        LocalDate ld = LocalDate.now(clock).plus(1, ChronoUnit.DAYS);
        LocalDateTime localDateTime = LocalDateTime.of(ld, LocalTime.of(8, 0, 0));
        Instant instant = localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
        //clock set to tomorrow 8AM

        clock = Clock.fixed(instant, ZoneId.systemDefault());
        TimeUtil.setClock(clock);
    }

    public void operateOneHour() throws NotEnoughProductInStorage {
        Random random = new Random();
        Map<Drink, Integer> customerMap;
        customerList = Stream.generate(Customer::new).limit(random.nextInt(10)+1).collect(Collectors.toList());
        for (Customer customer: customerList)
        {
            Clock buyTime = Clock.offset(clock, Duration.ofSeconds(random.nextInt(3600)));
            TimeUtil.setClock(buyTime);
            customerMap = customer.getDrinks();
            List<ShortenedOrderItem> shortenedOrderItems = new ArrayList<>();
            for (Map.Entry<Drink, Integer> entry: customerMap.entrySet())
            {
                Drink drink = drinkService.get(entry.getKey().getId());
                int count = minimum(drink.getQuantity(), entry.getValue());

                if (count > 0) {
                    //drinkService.take(drink, count);
                    ShortenedOrderItem shortenedOrderItem = new ShortenedOrderItem(drink, count);
                    shortenedOrderItems.add(shortenedOrderItem);
                    log.debug("Date/Time: {}, Drink {} in amount {} was added to order, price for 1 piece is {}, current markup is {}", LocalDateTime.ofInstant(buyTime.instant(), ZoneId.systemDefault()), drink.getName(), count, shortenedOrderItem.getAverageItemPrice(), PriceUtil.getMarkupAsString());
                }
            }
            Order order = shortenedOrderItemService.getOrderFromItemList(shortenedOrderItems);
            orderService.save(order);
            log.debug("Full order: {}", order.getOrders());
        }
    }

    public void workForOneDay() throws NotEnoughProductInStorage {
        //shop works for 13 hours
        for (int i = 0; i < 13; i ++)
        {
            operateOneHour();
            clock = Clock.offset(clock, Duration.ofHours(1));
        }
        refillingService.refill();
        LocalDate ld = LocalDate.now(clock).plus(1, ChronoUnit.DAYS);
        toNextDay();
    }


//    public void makePurchase(Map<Drink, Integer> items) throws NotEnoughProductInStorage {
//
//    }

    private int minimum(int a, int b)
    {
        if (a < b) return a;
        else return b;
    }


}
