package io.github.orionlibs.orion_iot.utils.calendar;

import java.sql.Timestamp;

public class CalendarService
{
    public static Timestamp getCurrentDatetimeAsTimestamp()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setNanos((int)(System.nanoTime() % 1000000000));
        return timestamp;
        //return new Timestamp(getCurrentInstant().toEpochMilli());
    }


    public static SQLTimestamp getCurrentDatetimeAsSQLTimestamp()
    {
        return new SQLTimestamp(getCurrentDatetimeAsTimestamp());
    }
}