package io.github.orionlibs.orion_iot.database;

import java.util.List;

public class Database
{
    public static OrionSQLDatabaseAccessObject connection;
    public static String deviceDataDatabaseName;


    public static void runDDL(String SQLCode)
    {
        connection.runDDL(SQLCode);
    }


    public static long getNumberOfRecords(String databaseTable, String databaseName)
    {
        return GetNumberOfRecordsTask.run(databaseTable, databaseName);
    }


    public static int saveModel(OrionModel model, String databaseTable, String databaseName)
    {
        return SaveModelTask.run(model, databaseTable, databaseName);
    }


    public static List<Object> runSQL(String SQLCode, Object modelToUse, Object[] parameters)
    {
        return connection.runSQL(SQLCode, modelToUse, parameters);
    }


    public static int runSQL(String SQLCode)
    {
        return connection.runSQL(SQLCode);
    }


    public static int runSQL(String SQLCode, Object[] parameters)
    {
        return connection.runSQL(SQLCode, parameters);
    }


    public static void executeMultipleCommands(String SQLCode)
    {
        connection.executeMultipleCommands(SQLCode);
    }


    public static List<Object> runSQL(String SQLCode, Object modelToUse)
    {
        return connection.runSQL(SQLCode, modelToUse);
    }
}