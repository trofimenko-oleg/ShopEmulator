package com.myshop.util;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;

public abstract class TimeUtil {
    private static Clock clock = Clock.system(ZoneId.systemDefault());
    //private static Clock clock = Clock.offset(Clock.system(ZoneId.systemDefault()), Duration.ofMinutes(-13));


    public static Clock getClock() {
        return clock;
    }

    public static void setClock(Clock clock) {
        TimeUtil.clock = clock;
    }
}
