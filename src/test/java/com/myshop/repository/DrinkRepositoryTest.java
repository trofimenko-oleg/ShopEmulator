package com.myshop.repository;

import com.myshop.DatabaseSetup;
import com.myshop.domain.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.junit.Assert.*;

@ContextConfiguration("classpath:/spring/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DrinkRepositoryTest {

    @Autowired
    private DataSource dataSource;

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
    public void saveNonAlcoholDrink() {
        NonAlcoholicDrink nonAlcoholicDrink = new NonAlcoholicDrink("Водичка", 10.15, 0.5, 600, NonAlcoholicGroup.MINERAL_WATER, "Вода артезианская, добытая из скважины 826м. Лечит от всех болезней.");
        Drink newDrink = drinkRepository.save(nonAlcoholicDrink);
        assertNotNull(newDrink.getId());
        assertEquals("Водичка", newDrink.getName());
        assertEquals(10.15, newDrink.getPurchasePrice(), 0.001);
        assertEquals(0.5, newDrink.getVolume(), 0.001);
        assertEquals(600, newDrink.getQuantity());
        NonAlcoholicDrink newNonAlcoholicDrink = (NonAlcoholicDrink) newDrink;
        assertEquals(NonAlcoholicGroup.MINERAL_WATER, newNonAlcoholicDrink.getGroup());
        assertEquals("Вода артезианская, добытая из скважины 826м. Лечит от всех болезней.", newNonAlcoholicDrink.getComposition());
    }

    @Test
    public void saveAlcoholDrink() {
        AlcoholicDrink alcoholicDrink = new AlcoholicDrink("Тройной одеколон", 8.20, 0.2, 15, AlcoholicGroup.OTHER, 33);
        Drink newDrink = drinkRepository.save(alcoholicDrink);
        assertNotNull(newDrink.getId());
        assertEquals("Тройной одеколон", newDrink.getName());
        assertEquals(8.20, newDrink.getPurchasePrice(), 0.001);
        assertEquals(0.2, newDrink.getVolume(), 0.001);
        assertEquals(15, newDrink.getQuantity());
        AlcoholicDrink newAlcoholicDrink = (AlcoholicDrink) newDrink;
        assertEquals(AlcoholicGroup.OTHER, newAlcoholicDrink.getGroup());
        assertEquals(33, newAlcoholicDrink.getABV(), 0.1);
    }

    @Test
    public void delete() {
        drinkRepository.delete(1);
        assertNull(drinkRepository.get(1));
        assertEquals(6, drinkRepository.getAll().size());
    }

    @Test
    public void getAlcoholicDrink() {
        dbSetupTracker.skipNextLaunch();
        assertEquals(AlcoholicDrink.class, drinkRepository.get(2).getClass());
        AlcoholicDrink alcoholicDrink = (AlcoholicDrink)drinkRepository.get(2);
        assertEquals("Пиво Одесское Новое", alcoholicDrink.getName());
        assertEquals(13.25, alcoholicDrink.getPurchasePrice(), 0.001);
        assertEquals(0.5, alcoholicDrink.getVolume(), 0.001);
        assertEquals(120, alcoholicDrink.getQuantity());
        assertEquals(AlcoholicGroup.BEER, alcoholicDrink.getGroup());
        assertEquals(4, alcoholicDrink.getABV(), 0.001);
    }

    @Test
    public void getNonAlcoholicDrink() {
        dbSetupTracker.skipNextLaunch();
        assertEquals(NonAlcoholicDrink.class, drinkRepository.get(1).getClass());
        NonAlcoholicDrink nonAlcoholicDrink = (NonAlcoholicDrink)drinkRepository.get(1);
        assertEquals("Вода минеральная хорошо", nonAlcoholicDrink.getName());
        assertEquals(9.99, nonAlcoholicDrink.getPurchasePrice(), 0.001);
        assertEquals(0.3, nonAlcoholicDrink.getVolume(), 0.001);
        assertEquals(570, nonAlcoholicDrink.getQuantity());
        assertEquals(NonAlcoholicGroup.MINERAL_WATER, nonAlcoholicDrink.getGroup());
        assertEquals("Вода минеральная, лечебно-столовая", nonAlcoholicDrink.getComposition());
    }

    @Test
    public void getByPartOfName() {
        dbSetupTracker.skipNextLaunch();
        List<Drink> drinks = drinkRepository.getByPartOfName("во");
        assertEquals(2, drinks.size());
        List<Drink> expectedDrinks = new ArrayList<>(Arrays.asList(drinkRepository.get(1), drinkRepository.get(2)));
        assertTrue(drinks.containsAll(expectedDrinks));
        drinks = drinkRepository.getByPartOfName("МартИни");
        assertEquals(1, drinks.size());
        assertTrue(drinks.contains(drinkRepository.get(6)));
        drinks = drinkRepository.getByPartOfName("Несуществующий напиток");
        assertEquals(0, drinks.size());

    }

    @Test
    public void getAll() {
        dbSetupTracker.skipNextLaunch();
        assertEquals(7, drinkRepository.getAll().size());
    }

    @Test
    public void add() {
        Drink drink = drinkRepository.get(1);
        drinkRepository.add(drink, -50);
        assertEquals(570, drink.getQuantity());
        drinkRepository.add(drink, 0);
        assertEquals(570, drink.getQuantity());
        drinkRepository.add(drink, 100);
        assertEquals(670, drink.getQuantity());
    }

    @Test(expected = NotEnoughProductInStorage.class)
    public void takeTooMuch() throws NotEnoughProductInStorage {
        drinkRepository.take(drinkRepository.get(1), 1000);
    }

    @Test
    public void take() throws NotEnoughProductInStorage {
        Drink drink = drinkRepository.get(1);
        drinkRepository.take(drink, 100);
        assertEquals(470, drink.getQuantity());
        drinkRepository.take(drink, -140);
        assertEquals(470, drink.getQuantity());
    }
}