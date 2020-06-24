package com.myshop.service;

import com.myshop.DatabaseSetup;
import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.util.TimeUtil;
import com.myshop.util.exception.NotEnoughProductInStorage;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-test.xml")
public class OrderServiceImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DrinkService drinkService;

    private final static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void prepare(){
        Operation operation =
                sequenceOf(
                        DatabaseSetup.DELETE_ALL,
                        DatabaseSetup.INSERT_ALL);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        // or without DataSource:
        // DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
        dbSetup.launch();
    }

    @Test(expected = NotEnoughProductInStorage.class)
    public void saveNewWithMoreQuantityThanAvailable() throws NotEnoughProductInStorage {
        LocalDateTime localDateTime = LocalDateTime.now(TimeUtil.getClock());
        Order order = new Order(null, localDateTime.getDayOfWeek(), localDateTime.toLocalTime(), localDateTime.toLocalDate());
        OrderDetails orderDetails = new OrderDetails(order, drinkService.get(1), 1500, drinkService.get(1).getPurchasePrice()*1.5);
        OrderDetails orderDetails2 = new OrderDetails(order, drinkService.get(2), 1, drinkService.get(2).getPurchasePrice()*1.5);
        OrderDetails orderDetails3 = new OrderDetails(order, drinkService.get(3), 6, drinkService.get(3).getPurchasePrice()*1.5);
        order.setOrders(new ArrayList<>(Arrays.asList(orderDetails, orderDetails2, orderDetails3)));
        orderService.save(order);
    }

    @Test(expected = NotEnoughProductInStorage.class)
    public void saveExistingWithMoreQuantityThanAvailable() throws NotEnoughProductInStorage {
        Order order = orderService.get(2);
        for (OrderDetails item: order.getOrders()){
            if (item.getDrink().getId() == 3) {
                item.setDrinkQuantity(2000);
            }
        }
        orderService.save(order);
    }

    @Test
    public void saveExistingTakeAllAvailableFromStorage() throws NotEnoughProductInStorage {
        LocalDateTime localDateTime = LocalDateTime.now(TimeUtil.getClock());
        Order order = orderService.get(2);
        for (OrderDetails item: order.getOrders()){
            if (item.getDrink().getId() == 3) {
                item.setDrinkQuantity(87);
            }
        }
        orderService.save(order);
        for (OrderDetails item: order.getOrders()){
            if (item.getDrink().getId() == 3) {
                assertEquals(87, item.getDrinkQuantity());
            }
        }
        assertEquals(0, drinkService.get(3).getQuantity());
    }

    @Test
    public void saveNew() throws NotEnoughProductInStorage {
        LocalDateTime localDateTime = LocalDateTime.now(TimeUtil.getClock());
        Order order = new Order(null, localDateTime.getDayOfWeek(), localDateTime.toLocalTime(), localDateTime.toLocalDate());
        OrderDetails orderDetails = new OrderDetails(order, drinkService.get(1), 2, drinkService.get(1).getPurchasePrice()*1.5);
        OrderDetails orderDetails2 = new OrderDetails(order, drinkService.get(2), 1, drinkService.get(2).getPurchasePrice()*1.5);
        OrderDetails orderDetails3 = new OrderDetails(order, drinkService.get(3), 6, drinkService.get(3).getPurchasePrice()*1.5);
        order.setOrders(new ArrayList<>(Arrays.asList(orderDetails, orderDetails2, orderDetails3)));
        orderService.save(order);
        for (OrderDetails item: order.getOrders()){
            assertNotNull(item.getId());
        }
        assertEquals(3, order.getOrders().size());
    }
}