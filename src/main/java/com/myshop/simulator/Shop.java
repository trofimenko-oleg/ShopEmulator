package com.myshop.simulator;

import com.myshop.domain.Drink;
import com.myshop.service.*;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.PriceUtil;
import com.myshop.util.TimeUtil;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class Shop {
private static final Logger log = getLogger(Shop.class);
private static Clock clock = Clock.system(ZoneId.systemDefault());
private List<Customer> customerList = new ArrayList<>();

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ShortenedOrderItemService shortenedOrderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private RefillingService refillingService;


    //offset for next client
//    private Duration duration;

    public Shop() {

    }

    public void toNextDay()
    {
        LocalDate ld = LocalDate.now(clock).plus(1, ChronoUnit.DAYS);
        //clock set to tomorrow 8AM
        clock = Clock.fixed(LocalDateTime.of(ld, LocalTime.of(8,0,0)).toInstant(OffsetDateTime.now().getOffset()), ZoneId.systemDefault());
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
                drinkService.take(drink, count);
                ShortenedOrderItem shortenedOrderItem = new ShortenedOrderItem(drink, count);
                log.debug("Time: {}, Drink {} in amount {} was taken, price for 1 piece is {}, current markup is {}", buyTime, drink.getName(), count, shortenedOrderItem.getAverageItemPrice(), PriceUtil.getMarkup());
                shortenedOrderItems.add(shortenedOrderItem);
            }
            orderService.save(shortenedOrderItemService.getOrderFromItemList(shortenedOrderItems));
            log.debug("Full order: {}", customerMap);
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
