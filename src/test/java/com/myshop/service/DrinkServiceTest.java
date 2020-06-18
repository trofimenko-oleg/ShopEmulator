package com.myshop.service;

import com.myshop.domain.Drink;
import javassist.NotFoundException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:/spring/spring-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DrinkServiceTest extends TestCase {

    @Autowired
    private DrinkService service;

    public void testSave() {

    }

    @Test
    public void testDelete() {
        service.delete(6);
        assertNull(service.get(6));
        assertEquals(6, service.getAll().size());
    }

    @Test
    public void testGet() {
        Drink drink = service.get(1);
        assertEquals("Вода минеральная Хорошо", drink.getName());
    }

    @Test
    public void testGetNotFound() {
        Drink drink = service.get(8);
        assertNull(drink);
    }

    public void testGetByPartOfName() {
    }

    public void testGetAll() {
    }

    public void testAdd() {
    }
}