package com.myshop.service;

import com.myshop.DatabaseSetup;
import com.myshop.util.TimeUtil;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-test.xml")
public class RefillingServiceImplTest {

    private static Clock clock = TimeUtil.getClock();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DrinkService drinkService;

    private final static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void prepare() throws Exception {
        Operation operation =
                sequenceOf(
                        DatabaseSetup.DELETE_DRINKS,
                        DatabaseSetup.INSERT_DRINKS);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        // or without DataSource:
        // DbSetup dbSetup = new DbSetup(new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD), operation);
        dbSetup.launch();
        //June 18, 2020 20:59:50 Local Time (Kiev, GMT +3), Tuesday
        long offset = 1592503190 - Instant.now().getEpochSecond();
        TimeUtil.setClock(Clock.offset(Clock.system(TimeUtil.getDefaultZoneId()), Duration.ofSeconds(offset)));
    }

    @AfterClass
    public static void setClockBack()
    {
        TimeUtil.setClock(clock);
    }

    @Test
    public void refill() throws InterruptedException {
        int quantity = drinkService.get(7).getQuantity();
        for (int i = 0; i < 20; i++)
        {
            System.out.println(drinkService.get(7).getQuantity());
            System.out.println(LocalDateTime.now(TimeUtil.getClock()));
            Thread.sleep(1000);

        }
    }
}