package com.myshop.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class PriceUtil {
    private static LocalTime eveningStart = LocalTime.of(18, 0, 0);
    private static LocalTime eveningEnd = LocalTime.of(20, 0, 0);
    public enum DAYTIME
    {
        WEEKEND,
        EVENING,
        OTHER
    }

    public static DAYTIME getDaytime()
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = localDateTime.toLocalTime();
        LocalDate localDate = localDateTime.toLocalDate();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (localTime.isAfter(eveningStart) && localTime.isBefore(eveningEnd)){
            return DAYTIME.EVENING;
        }
        else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return DAYTIME.WEEKEND;
        }
        else return DAYTIME.OTHER;
    }
}

