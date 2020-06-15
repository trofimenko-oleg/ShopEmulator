package com.myshop.simulator;


import com.myshop.util.exception.NotEnoughProductInStorage;


public class Simulator {
    public static void startSimulator(int days) throws NotEnoughProductInStorage {
        Shop shop = new Shop();
        shop.toNextDay();
        for (int i = 0; i < days; i ++)
        {
            shop.workForOneDay();
        }
    }
}
