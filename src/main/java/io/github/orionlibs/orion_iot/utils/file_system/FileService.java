package io.github.orionlibs.orion_iot.utils.file_system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileService
{
    public static String convertFileResourceToString(String filePath) throws FileNotFoundException, IOException
    {
        return new String(FileService.class.getResourceAsStream(filePath).readAllBytes(), Charset.forName("UTF-8"));
    }
}