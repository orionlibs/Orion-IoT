package io.github.orionlibs.orion_iot.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.orion_iot.ATest;
import io.github.orionlibs.orion_iot.config.ConfigurationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class DatabaseTest extends ATest
{
    @BeforeEach
    void setUp()
    {
        resetAndSeedDatabase();
    }


    @Test
    public void whenInsertRecordToDatabase_thenOK()
    {
        assertEquals(2L, Database.getNumberOfRecords("." + ConfigurationService.getProp("orionlibs.orion-iot.database.of.iot.device.data.and.devices.table.name"), IoTDatabase.deviceDataDatabaseName));
    }
}
