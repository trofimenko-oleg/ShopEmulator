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
        return Math.round(price * 100)/100;
    }
}
