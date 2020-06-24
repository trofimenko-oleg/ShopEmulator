package com.myshop.util;

import java.time.*;

public abstract class TimeUtil {
    private static ZoneId defaultZoneId = ZoneId.of("Europe/Kiev");
    private static Clock clock = Clock.system(defaultZoneId);
    private static ZoneOffset defaultZoneOffset = defaultZoneId.getRules().getOffset(clock.instant());
    private static LocalTime eveningStart = LocalTime.of(18, 0, 0);
    private static LocalTime eveningEnd = LocalTime.of(20, 0, 0);
    //private static Clock clock = Clock.offset(Clock.system(defaultZoneId), Duration.ofMinutes(332));


    public static Clock getClock() {
        return clock;
    }

    public static ZoneId getDefaultZoneId() {
        return defaultZoneId;
    }

    public static void setClock(Clock clock) {
        TimeUtil.clock = clock;
    }

    public static LocalTime getEveningStart() {
        return eveningStart;
    }

    public static LocalTime getEveningEnd() {
        return eveningEnd;
    }

    public static ZoneOffset getDefaultZoneOffset() {
        return defaultZoneOffset;
    }
}
