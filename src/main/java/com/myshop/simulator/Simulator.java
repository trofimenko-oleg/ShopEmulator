package com.myshop.simulator;

import com.myshop.domain.Drink;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.slf4j.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import static org.slf4j.LoggerFactory.getLogger;

public class Simulator {

    private static final Logger log = getLogger(Simulator.class);
    private static String fileName = "C:/log/shop_log.txt";

    public static void setFileName(String fileName) {
        Simulator.fileName = fileName;
    }

    public static void startSimulator(int days) throws NotEnoughProductInStorage, IOException {
        Shop shop = new Shop();
        log.info("Simulator started");
        for (int i = 0; i < days; i ++){
            shop.workForOneDay();
        }
        Map<Drink, Integer> sellsInfo = shop.getSellsInfo();
        Map<Drink, Integer> refillInfo = shop.getRefillInfo();
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName), true))
        {
            writer.printf("For the period of %d days we have these sells:%n", days);
            writer.print("-------------------------" + System.lineSeparator());
            double allOrdersCostPrice = 0;
            for (Map.Entry<Drink, Integer> entry: sellsInfo.entrySet()){
                allOrdersCostPrice += (entry.getKey().getPurchasePrice() * entry.getValue());
                writer.printf("Drink \"%s\" - %d pc.%n", entry.getKey().getName(), entry.getValue());
            }
            writer.print("-------------------------" + System.lineSeparator());
            writer.print("And these refills:" + System.lineSeparator());
            writer.print("-------------------------" + System.lineSeparator());
            double moneySpent = 0;
            for (Map.Entry<Drink, Integer> entry: refillInfo.entrySet()){
                moneySpent += (entry.getKey().getPurchasePrice() * entry.getValue());
                writer.printf("Drink \"%s\" - %d pc.%n", entry.getKey().getName(), entry.getValue());
            }
            writer.print("-------------------------" + System.lineSeparator());
            writer.printf("Money spent on refilling - %.2f%n", moneySpent);
            writer.print("-------------------------" + System.lineSeparator());
            writer.printf("Money got after sales - %.2f%n", shop.getAllOrdersChecksSum());
            writer.print("-------------------------" + System.lineSeparator());
            writer.printf("Cost price of sold items - %.2f%n", allOrdersCostPrice);
            writer.print("-------------------------" + System.lineSeparator());
            writer.printf("So, profit is %.2f - %.2f = %.2f%n", shop.getAllOrdersChecksSum(), allOrdersCostPrice, shop.getAllOrdersChecksSum() - allOrdersCostPrice);
            writer.print("=========================" + System.lineSeparator());
        }
    }
}
