package com.myshop.service.to;

import com.myshop.DatabaseSetup;
import com.myshop.domain.Drink;
import com.myshop.repository.DrinkRepository;
import com.myshop.util.PriceUtil;
import com.myshop.util.TimeUtil;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.time.*;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@ContextConfiguration({"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShortenedOrderItemTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DrinkRepository drinkRepository;

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

    @After
    public void setClockBack()
    {
        TimeUtil.setClock(Clock.system(TimeUtil.getDefaultZoneId()));
    }

    @Test
    public void getAverageItemPriceAtWeekdayEveningOneOrTwo() {
        //start of Friday evening + 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 26), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 2;
        double markup = PriceUtil.EVENING_MARKUP;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekdayEveningMoreThanTwo() {
        //start of Friday evening + 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 26), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 4;
        double markup = (PriceUtil.EVENING_MARKUP + PriceUtil.WHOLESALE_MARKUP)/2;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekdayOneOrTwo() {
        //start of Friday evening - 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 26), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(-1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 2;
        double markup = PriceUtil.OTHER_TIME_MARKUP;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekdayMoreThanTwo() {
        //start of Friday evening - 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 26), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(-1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 4;
        double markup = (PriceUtil.OTHER_TIME_MARKUP + PriceUtil.WHOLESALE_MARKUP)/2;
        assertEquals(drink.getPurchasePrice() * markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekendDayMoreThanTwo() {
        //start of Saturday evening - 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 27), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(-1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 4;
        double markup = (PriceUtil.WEEKEND_MARKUP + PriceUtil.WHOLESALE_MARKUP)/2;
        assertEquals(drink.getPurchasePrice() *markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekendDayOneOrTwo() {
        //start of Saturday evening - 1 second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 27), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(-1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 2;
        double markup = PriceUtil.WEEKEND_MARKUP;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekendEveningDayMoreThanTwo() {
        //start of Saturday evening + 1second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 27), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 4;
        double markup = (PriceUtil.EVENING_MARKUP + PriceUtil.WHOLESALE_MARKUP)/2;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }

    @Test
    public void getAverageItemPriceAtWeekendEveningDayOneOrTwo() {
        //start of Saturday evening + 1second
        TimeUtil.setClock(Clock.offset(Clock.fixed(LocalDateTime.of(LocalDate.of(2020, 6, 27), TimeUtil.getEveningStart()).toInstant(TimeUtil.getDefaultZoneOffset()), TimeUtil.getDefaultZoneId()), Duration.ofSeconds(1)));
        Drink drink = drinkRepository.get(7); //price = 195
        int quantity = 2;
        double markup = PriceUtil.EVENING_MARKUP;
        assertEquals(drink.getPurchasePrice()*markup, new ShortenedOrderItem(drink, quantity).getAverageItemPrice(), 0.01);
    }
}