package com.myshop.service;

import com.myshop.DatabaseSetup;
import com.myshop.repository.DrinkRepository;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;
import java.time.Clock;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-test.xml")
public class RefillingServiceImplTest {

    private static Clock clock = TimeUtil.getClock();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RefillingService refillingService;

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
        //June 18, 2020 20:59:50 Local Time (Kiev, GMT +3), Thursday
        //long offset = 1592503190 - Instant.now().getEpochSecond();
       // TimeUtil.setClock(Clock.offset(Clock.system(TimeUtil.getDefaultZoneId()), Duration.ofSeconds(offset)));
    }

    @AfterClass
    public static void setClockBack()
    {
        TimeUtil.setClock(clock);
    }

    //don't know how to pass fake clock
    public void refillScheduled() throws InterruptedException {
        int quantity = drinkRepository.get(7).getQuantity();
        for (int i = 0; i < 60; i++)
        {
            System.out.println(drinkRepository.get(7).getQuantity());
            System.out.println(LocalDateTime.now(TimeUtil.getClock()));
            Thread.sleep(1000);
        }
    }

    @Test
    public void refill(){
        int quantity = drinkRepository.get(7).getQuantity();
        refillingService.refill(100);
        int newQuantity = drinkRepository.get(7).getQuantity();
        assertEquals(quantity+100, newQuantity);
    }
}