package com.myshop.util;

import com.myshop.domain.Drink;

import java.text.DecimalFormat;
import java.util.Locale;

public abstract class FormattersUtil {
    public static void formatPriceTwoDigitsAfterPoint(Drink drink)
    {
        double price = drink.getPurchasePrice();
        drink.setPurchasePrice(round(price));
    }
    public static double round(double price)
    {
        double temp = Math.round(price * 100);
       return temp/100;
       //return Math.round(price*100)/100 gives wrong result no idea why
    }
}
