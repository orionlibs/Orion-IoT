package io.github.orionlibs.orion_iot.utils.strings;

public class ConvertFirstCharacterToUppercaseTask
{
    public static String run(String aString)
    {
        if(aString != null && !aString.isEmpty())
        {
            char[] stringCharactersArray = aString.trim().toCharArray();
            stringCharactersArray[0] = Character.toUpperCase(stringCharactersArray[0]);
            return String.copyValueOf(stringCharactersArray);
        }
        else
        {
            return "";
        }
    }
}