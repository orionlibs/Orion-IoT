package io.github.orionlibs.orion_iot.database;

import io.github.orionlibs.core.reflection.variable.access.ReflectionInstanceVariablesAccessService;
import io.github.orionlibs.core.reflection.variable.retrieval.ReflectionInstanceVariablesRetrievalService;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SaveModelTask
{
    public static int run(OrionModel model, String databaseTable, String databaseName, List<String> columnsToSave)
    {
        MySQLQueryBuilderService mySQLQuery = new MySQLQueryBuilderService();
        mySQLQuery.insertIntoTable(databaseName + databaseTable);
        mySQLQuery.parenthesisedCommaSeparatedColumns(columnsToSave);
        mySQLQuery.valuesOfParenthesisedCommaSeparatedQuestionMarks();
        mySQLQuery.buildParametersArray(model);
        String SQL = mySQLQuery.semicolon().toString();
        return Database.runSQL(SQL, mySQLQuery.getParameters());
    }


    public static int run(OrionModel model, String databaseTable, String databaseName)
    {
        MySQLQueryBuilderService mySQLQuery = new MySQLQueryBuilderService();
        mySQLQuery.insertIntoTable(databaseName + databaseTable);
        List<String> columnsToSave = new ArrayList<>();
        List<Field> privateInstanceVariables = ReflectionInstanceVariablesRetrievalService.getAllPrivateInstanceVariables(model);
        for(Field field : privateInstanceVariables)
        {
            ReflectionInstanceVariablesAccessService.makeInstanceVariableAccessible(field);
            IgnoreForORM ignoreForORMAnnotation = field.getAnnotation(IgnoreForORM.class);
            if(ignoreForORMAnnotation == null)
            {
                columnsToSave.add(field.getName());
            }
        }
        mySQLQuery.parenthesisedCommaSeparatedColumns(columnsToSave);
        mySQLQuery.valuesOfParenthesisedCommaSeparatedQuestionMarks();
        mySQLQuery.buildParametersArray(model);
        String SQL = mySQLQuery.semicolon().toString();
        return Database.runSQL(SQL, mySQLQuery.getParameters());
    }
}