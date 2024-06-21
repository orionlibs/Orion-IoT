package io.github.orionlibs.orion_iot.database;

import java.util.List;

public class GetNumberOfRecordsTask
{
    public static long run(String databaseTable, String databaseName)
    {
        MySQLQueryBuilderService mySQLQuery = new MySQLQueryBuilderService();
        mySQLQuery.selectColumn("COUNT(1)");
        mySQLQuery.fromTable(databaseName + databaseTable);
        String SQL = mySQLQuery.semicolon().toString();
        List<Object> temp = Database.runSQL(SQL, Long.valueOf(0), mySQLQuery.getParameters());
        if(temp != null && !temp.isEmpty())
        {
            return (long)temp.get(0);
        }
        return 0L;
    }


    public static long run(String SQL, Object[] parameters)
    {
        List<Object> temp = Database.runSQL(SQL, Long.valueOf(0), parameters);
        if(temp != null && !temp.isEmpty())
        {
            return (long)temp.get(0);
        }
        return 0L;
    }


    public static long run(OrionModel model, String databaseTable, String databaseName, List<String> columnsForCondition)
    {
        MySQLQueryBuilderService mySQLQuery = new MySQLQueryBuilderService();
        mySQLQuery.selectColumn("COUNT(*)");
        mySQLQuery.fromTable(databaseName + databaseTable);
        mySQLQuery.whereColumnsEqualsQuestionMarkConjunction(columnsForCondition);
        mySQLQuery.buildParametersArray(model);
        String SQL = mySQLQuery.semicolon().toString();
        List<Object> temp = Database.runSQL(SQL, Long.valueOf(0), mySQLQuery.getParameters());
        if(temp != null && !temp.isEmpty())
        {
            return (long)temp.get(0);
        }
        return 0L;
    }
}