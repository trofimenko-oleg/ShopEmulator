package com.myshop.util;

public abstract class FormattersUtil {

    public static double round(double price) {
        double temp = Math.round(price * 100);
        return temp / 100;
        //return Math.round(price*100)/100 gives wrong result no idea why
    }
}
