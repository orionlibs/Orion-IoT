package io.github.orionlibs.orion_iot.utils.strings;

import java.util.List;

public class ConvertToCommaAndSpaceSeparatedStringTask
{
    public static String run(List<String> strings)
    {
        StringBuffer commaAndSpaceSeparatedList = new StringBuffer();
        for(int i = 0; i < strings.size(); i++)
        {
            if(StringsService.isNotEmpty(strings.get(i)))
            {
                commaAndSpaceSeparatedList.append(strings.get(i));
                if(i < strings.size() - 1)
                {
                    commaAndSpaceSeparatedList.append(", ");
                }
            }
        }
        return commaAndSpaceSeparatedList.toString();
    }
}