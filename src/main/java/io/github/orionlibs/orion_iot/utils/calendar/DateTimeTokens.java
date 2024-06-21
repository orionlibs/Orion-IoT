package io.github.orionlibs.orion_iot.utils.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DateTimeTokens
{
    private int days;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    private int seconds;


    public static DateTimeTokens of()
    {
        return DateTimeTokens.builder().build();
    }
}