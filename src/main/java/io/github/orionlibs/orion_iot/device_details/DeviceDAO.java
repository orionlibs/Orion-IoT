package io.github.orionlibs.orion_iot.device_details;

import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.orion_iot.database.IoTDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceDAO
{
    public static long getNumberOfRecords()
    {
        return Database.getNumberOfRecords(IoTDatabase.tableDevices, IoTDatabase.deviceDataDatabase);
    }


    public static List<DeviceModel> getAll()
    {
        List<DeviceModel> results = new ArrayList<>();
        List<Object> temp = Database.getAllRows(DeviceModel.of(),
                        IoTDatabase.tableDevices,
                        IoTDatabase.deviceDataDatabase);
        if(temp != null && !temp.isEmpty())
        {
            temp.forEach(record -> results.add((DeviceModel)record));
        }
        return results;
    }


    public static List<DeviceModel> getAllWithAscendingOrder(String columnToAscendBy)
    {
        List<DeviceModel> results = new ArrayList<>();
        List<Object> temp = Database.getAllRowsWithAscendingOrder(DeviceModel.of(),
                        IoTDatabase.tableDevices,
                        IoTDatabase.deviceDataDatabase,
                        columnToAscendBy);
        if(temp != null && !temp.isEmpty())
        {
            temp.forEach(record -> results.add((DeviceModel)record));
        }
        return results;
    }


    public static DeviceModel getByID(Long deviceID)
    {
        DeviceModel model = DeviceModel.builder()
                        .deviceID(deviceID)
                        .build();
        return (DeviceModel)Database.getOneModel(model,
                        IoTDatabase.tableDevices,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.deviceID));
    }


    public static int save(DeviceModel model)
    {
        return Database.saveModel(model,
                        IoTDatabase.tableDevices,
                        IoTDatabase.deviceDataDatabase);
    }


    public static int update(DeviceModel model)
    {
        return Database.updateModel(model,
                        IoTDatabase.tableDevices,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.deviceID));
    }
}