package com.myshop.service;

import com.myshop.DatabaseSetup;
import com.myshop.domain.AlcoholicDrink;
import com.myshop.domain.AlcoholicGroup;
import com.myshop.domain.Drink;
import com.myshop.util.TimeUtil;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import javassist.NotFoundException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.time.Clock;
import java.time.Instant;
import java.util.List;

import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

//nothing new compared to repository methods, so tests are for checkmark :)
@ContextConfiguration({"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DrinkServiceTest extends TestCase {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DrinkService service;
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
    }

    public void testSave() {
        Drink drink = new AlcoholicDrink("new", 1, 1, 1, AlcoholicGroup.WINE, 1);
        drink = service.save(drink);
        assertNotNull(drink.getId());
    }

    @Test
    public void testDelete() {
        service.delete(6);
        assertNull(service.get(6));
        assertEquals(6, service.getAll().size());
    }

    @Test
    public void testGet() {
        dbSetupTracker.skipNextLaunch();
        Drink drink = service.get(1);
        assertEquals("Вода минеральная хорошо", drink.getName());
    }

    @Test
    public void testGetNotFound() {
        dbSetupTracker.skipNextLaunch();
        Drink drink = service.get(8);
        assertNull(drink);
    }

    @Test
    public void testGetByPartOfNameNotTrimmed() {
        dbSetupTracker.skipNextLaunch();
        List<Drink> drinkList = service.getByPartOfName("      Два моря     ");
        assertEquals(0, drinkList.size());
    }

    @Test
    public void testGetAll() {
        dbSetupTracker.skipNextLaunch();
        assertEquals(7, service.getAll().size());
    }
}