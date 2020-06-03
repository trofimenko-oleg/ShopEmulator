package com.myshop.util;

import com.myshop.domain.Drink;

import java.util.Locale;

public abstract class FormattersUtil {
    public static void formatPriceTwoDigitsAfterPoint(Drink drink)
    {
        double price = drink.getPurchasePrice();
        drink.setPurchasePrice(Double.valueOf(String.format(Locale.getDefault(),"%.2f", price)));
    }
    public static double formatPriceTwoDigitsAfterPoint(double price)
    {
        return Double.valueOf(String.format(Locale.getDefault(),"%.2f", price));
    }
}
