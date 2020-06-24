package com.myshop;

import com.myshop.domain.*;
import com.myshop.repository.OrderRepository;
import com.myshop.repository.OrderRepositoryImpl;
import com.myshop.service.*;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.simulator.Simulator;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AppMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            ApplicationContextUtils applicationContextUtils = new ApplicationContextUtils();
            applicationContextUtils.setApplicationContext(appCtx);

            try {
                //to change final log file path use Simulator.setFileName(fileName); (default C:/log/shop_log.txt)
                Simulator.startSimulator(30);
            } catch (IOException | NotEnoughProductInStorage e) {
                e.printStackTrace();
            }
        }
    }
}
