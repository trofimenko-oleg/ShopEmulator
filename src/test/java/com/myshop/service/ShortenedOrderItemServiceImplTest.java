package com.myshop.service;

import com.myshop.DatabaseSetup;
import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.repository.DrinkRepository;
import com.myshop.repository.OrderRepository;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.TimeUtil;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-test.xml")
public class ShortenedOrderItemServiceImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    ShortenedOrderItemService shortenedOrderItemService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DrinkRepository drinkRepository;

    private final static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void prepare(){
        Operation operation =
                sequenceOf(
                        DatabaseSetup.DELETE_DRINKS,
                        DatabaseSetup.INSERT_DRINKS);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        // or without DataSource:
        // DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
        dbSetup.launch();
    }

    @Test
    public void getItems() {
        Order order = orderRepository.get(2);
        List<ShortenedOrderItem> itemsFromOrder = shortenedOrderItemService.getItems(order);
        assertEquals(3, itemsFromOrder.size());
        List<ShortenedOrderItem> newItems = new ArrayList<>();
        for (OrderDetails item: order.getOrders()){
            newItems.add(new ShortenedOrderItem(item.getDrink(), item.getDrinkQuantity()));
        }
        assertTrue(itemsFromOrder.containsAll(newItems));
    }

    @Test
    public void getOrderFromItemList() {
        //Friday, 26/6/2020 15:00:00
        TimeUtil.setClock(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 26), LocalTime.of(15, 0, 0)).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()));
        List<ShortenedOrderItem> items = new ArrayList<>();
        ShortenedOrderItem first = new ShortenedOrderItem(drinkRepository.get(1), 2);
        items.add(first);
        ShortenedOrderItem second = new ShortenedOrderItem(drinkRepository.get(2), 10);
        items.add(second);
        Order order = shortenedOrderItemService.getOrderFromItemList(items);
        assertEquals(2, order.getOrders().size());
        List<OrderDetails> orderDetails = Arrays.asList(new OrderDetails(order, drinkRepository.get(1), 2, first.getAverageItemPrice()), new OrderDetails(order, drinkRepository.get(2), 10, second.getAverageItemPrice()));
        assertTrue(order.getOrders().containsAll(orderDetails));
        assertEquals(DayOfWeek.FRIDAY, order.getDayOfWeek());
        assertEquals(LocalDate.of(2020,6,26), order.getLocalDate());
        assertEquals(LocalTime.of(15, 0, 0), order.getTime());
        assertEquals(164.58, order.getTotalCheckValue(), 0.01); // if prices are 9.99 and 13.25
    }
}