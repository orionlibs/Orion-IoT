package io.github.orionlibs.orion_iot.utils.calendar;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class SQLTimestamp extends Timestamp
{
    @SuppressWarnings("deprecation")
    public SQLTimestamp(int year, int month, int date, int hour, int minute, int second, int nanoseconds)
    {
        super(year - 1900, month - 1, date, hour, minute, second, nanoseconds);
    }


    public SQLTimestamp(long epochMilliseconds)
    {
        super(epochMilliseconds);
    }


    public SQLTimestamp(Timestamp timestamp)
    {
        super(timestamp.toInstant().toEpochMilli());
        setNanos((timestamp.getNanos()));
    }


    public SQLTimestamp(ZonedDateTime zonedDatetime)
    {
        this(zonedDatetime.toEpochSecond() * 1000);
    }


    public static SQLTimestamp of(int year, int month, int date, int hour, int minute, int second, int nanoseconds)
    {
        return new SQLTimestamp(year, month, date, hour, minute, second, nanoseconds);
    }


    public static SQLTimestamp of(long epochMilliseconds)
    {
        return new SQLTimestamp(epochMilliseconds);
    }


    public static SQLTimestamp of(Timestamp timestamp)
    {
        return new SQLTimestamp(timestamp);
    }


    public static SQLTimestamp of(ZonedDateTime zonedDatetime)
    {
        return new SQLTimestamp(zonedDatetime);
    }


    public static SQLTimestamp ofEpochSeconds(long epochSeconds)
    {
        return new SQLTimestamp(epochSeconds * 1000);
    }


    public DateTimeTokens getAsTokens()
    {
        return DateTimeTokens.builder()
                        .days(getDays())
                        .month(getMonths())
                        .year(getYears())
                        .hours(getHours())
                        .minutes(getMinutes())
                        .seconds(getSeconds())
                        .build();
    }


    @SuppressWarnings("deprecation")
    public int getYears()
    {
        return getYear() + 1900;
    }


    @SuppressWarnings("deprecation")
    public int getMonths()
    {
        return getMonth() + 1;
    }


    @SuppressWarnings("deprecation")
    public int getDays()
    {
        return getDate();
    }


    @Override
    public int hashCode()
    {
        return super.hashCode();
    }


    @Override
    public boolean equals(Object other)
    {
        return super.equals(other);
    }
}