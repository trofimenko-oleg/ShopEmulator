package com.myshop.util;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;

public abstract class TimeUtil {
    private static ZoneId defaultZoneId = ZoneId.of("Europe/Kiev");
    private static Clock clock = Clock.system(defaultZoneId);
    //private static Clock clock = Clock.offset(Clock.system(ZoneId.systemDefault()), Duration.ofMinutes(-13));


    public static Clock getClock() {
        return clock;
    }

    public static ZoneId getDefaultZoneId() {
        return defaultZoneId;
    }

    public static void setClock(Clock clock) {
        TimeUtil.clock = clock;
    }
}
