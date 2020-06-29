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
    private Map<Drink, Integer> sellsInfo = new HashMap<>();
    private Map<Drink, Integer> refillInfo = new HashMap<>();
    private double allOrdersChecksSum = 0;
    private DrinkService drinkService = ApplicationContextUtils.getApplicationContext().getBean(DrinkService.class);
    private ShortenedOrderItemService shortenedOrderItemService = ApplicationContextUtils.getApplicationContext().getBean(ShortenedOrderItemService.class);
    private OrderService orderService = ApplicationContextUtils.getApplicationContext().getBean(OrderService.class);
    private RefillingService refillingService = ApplicationContextUtils.getApplicationContext().getBean(RefillingService.class);

    public void toNextDay() {
        LocalDate ld = LocalDate.now(clock).plus(1, ChronoUnit.DAYS);
        LocalDateTime localDateTime = LocalDateTime.of(ld, LocalTime.of(8, 0, 0));
        //clock set to tomorrow 8AM
        clock = Clock.fixed(localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())), ZoneId.systemDefault());
        TimeUtil.setClock(clock);
        log.info("Date/Time is {}.", localDateTime);
        log.info("New day started");
        log.info("==============================");
    }

    public void operateOneHour() throws NotEnoughProductInStorage {
        Random random = new Random();
        Map<Drink, Integer> customerMap;
        customerList = Stream.generate(Customer::new).limit(random.nextInt(10) + 1).collect(Collectors.toList());
        for (Customer customer : customerList) {
            Clock buyTime = Clock.offset(clock, Duration.ofSeconds(random.nextInt(3600)));
            TimeUtil.setClock(buyTime);
            customerMap = customer.getDrinks();
            List<ShortenedOrderItem> shortenedOrderItems = new ArrayList<>();
            for (Map.Entry<Drink, Integer> entry : customerMap.entrySet()) {
                Drink drink = drinkService.get(entry.getKey().getId());
                int count = minimum(drink.getQuantity(), entry.getValue());

                if (count > 0) {
                    ShortenedOrderItem shortenedOrderItem = new ShortenedOrderItem(drink, count);
                    shortenedOrderItems.add(shortenedOrderItem);
                    log.debug("Date/Time: {}, Drink {} in amount {} was added to order, price for 1 piece is {}, current markup is {}", LocalDateTime.ofInstant(buyTime.instant(), ZoneId.systemDefault()), drink.getName(), count, shortenedOrderItem.getAverageItemPrice(), PriceUtil.getMarkupAsString());
                }
            }
            if (shortenedOrderItems.size() > 0) {
                for (ShortenedOrderItem item : shortenedOrderItems) {
                    sellsInfo.merge(item.getDrink(), item.getQuantity(), Integer::sum);
                }
                Order order = shortenedOrderItemService.getOrderFromItemList(shortenedOrderItems);
                orderService.save(order);
                allOrdersChecksSum += order.getTotalCheckValue();
                log.debug("Full order: {}", order.getOrders());
            }
        }
    }

    public void workForOneDay() throws NotEnoughProductInStorage {
        toNextDay();
        //shop works for 13 hours
        for (int i = 0; i < 13; i++) {
            operateOneHour();
            clock = Clock.offset(clock, Duration.ofHours(1));
        }
        Map<Drink, Integer> refilledDrinks = refillingService.refill();
        if (refilledDrinks.size() > 0) {
            for (Map.Entry<Drink, Integer> entry : refilledDrinks.entrySet()) {
                refillInfo.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }
        log.info("Day ended");
        log.info("------------------------------");
    }

    private int minimum(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    public Map<Drink, Integer> getSellsInfo() {
        return sellsInfo;
    }

    public Map<Drink, Integer> getRefillInfo() {
        return refillInfo;
    }

    public double getAllOrdersChecksSum() {
        return allOrdersChecksSum;
    }
}
