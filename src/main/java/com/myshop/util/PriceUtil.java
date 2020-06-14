package com.myshop.util;

import java.time.*;

public abstract class PriceUtil {
    public static final double EVENING_MARKUP = 1.08;
    public static final double WEEKEND_MARKUP = 1.15;
    public static final double OTHER_TIME_MARKUP = 1.1;
    public static final double WHOLESALE_MARKUP = 1.07;
    private static LocalTime eveningStart = LocalTime.of(18, 0, 0);
    private static LocalTime eveningEnd = LocalTime.of(20, 0, 0);
    public enum DAYTIME
    {
        WEEKEND,
        EVENING,
        OTHER
    }

    public static DAYTIME getDaytime(Clock clock)
    {

        LocalDateTime localDateTime = LocalDateTime.now(clock);
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

    public static double getMarkup() {
        DAYTIME daytime = getDaytime(TimeUtil.getClock());

        if (daytime == PriceUtil.DAYTIME.EVENING){
            return EVENING_MARKUP;
        }
        else if (daytime == PriceUtil.DAYTIME.WEEKEND){
            return WEEKEND_MARKUP;
        }
        else
            return OTHER_TIME_MARKUP;
    }
}

