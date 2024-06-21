package io.github.orionlibs.orion_iot.utils.strings;

import java.util.List;

public class StringsService
{
    public static boolean isNotEmpty(String aString)
    {
        return aString != null && !aString.isEmpty();
    }


    public static String convertToCommaAndSpaceSeparatedString(List<String> strings)
    {
        return ConvertToCommaAndSpaceSeparatedStringTask.run(strings);
    }


    public static String convertFirstCharacterToUppercase(String aString)
    {
        return ConvertFirstCharacterToUppercaseTask.run(aString);
    }
}