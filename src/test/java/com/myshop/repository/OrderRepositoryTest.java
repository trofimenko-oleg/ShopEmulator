package com.myshop.repository;

import com.myshop.DatabaseSetup;
import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.util.TimeUtil;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@ContextConfiguration("classpath:/spring/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    private final static DbSetupTracker dbSetupTracker = new DbSetupTracker();
    private static Clock clock = TimeUtil.getClock();

    @Before
    public void prepare() throws Exception {
        Operation operation =
                sequenceOf(
                        DatabaseSetup.DELETE_ORDERS,
                        DatabaseSetup.INSERT_ORDERS);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        // or without DataSource:
        // DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
        dbSetup.launch();
        //June 9, 2020 12:00:00 Local Time (Kiev, GMT +3), Tuesday
        TimeUtil.setClock(Clock.fixed(Instant.ofEpochSecond(1591693200), TimeUtil.getDefaultZoneId()));
    }

    @AfterClass
    public static void setClockBack()
    {
        TimeUtil.setClock(clock);
    }

    @Test
    public void saveEmpty() {
        LocalDateTime localDateTime = LocalDateTime.now(TimeUtil.getClock());
        Order order = new Order(null, localDateTime.getDayOfWeek(), localDateTime.toLocalTime(), localDateTime.toLocalDate());
        orderRepository.save(order);
        assertNotNull(order.getId());
        assertEquals(DayOfWeek.TUESDAY, order.getDayOfWeek());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        assertEquals("12:00:00", dateTimeFormatter.format(order.getTime()));
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        assertEquals("09-06-2020", dateTimeFormatter.format(order.getLocalDate()));
    }

    @Test
    public void saveWithItems() {
        LocalDateTime localDateTime = LocalDateTime.now(TimeUtil.getClock());
        Order order = new Order(null, localDateTime.getDayOfWeek(), localDateTime.toLocalTime(), localDateTime.toLocalDate());
        OrderDetails orderDetails = new OrderDetails(order, drinkRepository.get(1), 5, drinkRepository.get(1).getPurchasePrice()*1.5);
        OrderDetails orderDetails2 = new OrderDetails(order, drinkRepository.get(2), 1, drinkRepository.get(2).getPurchasePrice()*1.5);
        OrderDetails orderDetails3 = new OrderDetails(order, drinkRepository.get(3), 6, drinkRepository.get(3).getPurchasePrice()*1.5);
        order.setOrders(new ArrayList<>(Arrays.asList(orderDetails, orderDetails2, orderDetails3)));
        order = orderRepository.save(order);
        assertNotNull(order.getId());
        assertNotNull(order.getId());
        for (OrderDetails item: order.getOrders())
        {
            assertNotNull(item.getId());
            assertEquals(item.getOrder().getId(), order.getId());
        }

    }

    @Test
    public void delete() {
        orderRepository.delete(1);
        assertNull(orderRepository.get(1));
        assertEquals(1, orderRepository.getAll().size());
    }

    @Test
    public void checkIfItemsWereDeleted() {
        orderRepository.delete(2);
        assertNull(orderRepository.get(2));
        for (Order order: orderRepository.getAll())
        {
            for (OrderDetails item: order.getOrders())
            {
                assertTrue(item.getOrder() == null || item.getOrder().getId() != 2);
            }
        }
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }
}