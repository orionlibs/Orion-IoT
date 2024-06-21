package io.github.orionlibs.orion_iot.database;

import java.util.List;

public interface OrionSQLDatabaseAccessObject
{
    void executeMultipleCommands(String SQLCode);


    void runDDL(String SQLCode);


    int runSQL(String SQLCode);


    int runSQL(String SQLCode, Object[] parameters);


    int[] runSQLBatch(String SQLCode, List<Object[]> parameters, boolean allowSequentialExecution);


    int[] runSQLBatch(String SQLCode, boolean allowSequentialExecution);


    List<Object> runSQL(String SQLCode, Object modelToUse, Object[] parameters);


    List<Object> runSQL(String SQLCode, Object modelToUse);
}