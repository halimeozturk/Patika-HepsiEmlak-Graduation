package com.example.advert.util;

import java.time.LocalTime;
import java.time.ZonedDateTime;

public class DateFormatUtils {

    public static ZonedDateTime addFromLocalTime(ZonedDateTime zonedDateTime) {
        LocalTime localTime = LocalTime.of(0, 0);
        return ZonedDateTime.of(zonedDateTime.toLocalDate(), localTime, zonedDateTime.getZone());
    }

    public static ZonedDateTime addToLocalTime(ZonedDateTime zonedDateTime) {
        LocalTime localTime = LocalTime.of(23, 59);
        return ZonedDateTime.of(zonedDateTime.toLocalDate(), localTime, zonedDateTime.getZone());
    }
}
