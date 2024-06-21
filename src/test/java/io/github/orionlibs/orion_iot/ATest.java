package io.github.orionlibs.orion_iot;

import io.github.orionlibs.orion_iot.database.Database;
import io.github.orionlibs.orion_iot.utils.file_system.FileService;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ATest
{
    static
    {
        System.setProperty("active.execution.profile", OrionDomain.testing);
        Setup.setup();
    }

    public void resetDatabase()
    {
        resetTheDatabase();
    }


    public void resetAndSeedDatabase()
    {
        resetTheDatabase();
        seedDatabase();
    }


    public static void resetTheDatabase()
    {
        try
        {
            Database.runDDL("BEGIN NOT ATOMIC\n" + FileService.convertFileResourceToString("/io/github/orionlibs/orion_iot/configuration/MySQLSchema.sql") + "\nEND");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void seedDatabase()
    {
        resetDatabase();
        seedTheDatabase();
    }


    public static void seedTheDatabase()
    {
        resetTheDatabase();
        try
        {
            Database.runDDL("BEGIN NOT ATOMIC\n" + FileService.convertFileResourceToString("/io/github/orionlibs/orion_iot/configuration/MySQLSchema-initialisation.sql") + "\nEND");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
