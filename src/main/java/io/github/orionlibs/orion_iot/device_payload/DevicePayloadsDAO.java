package io.github.orionlibs.orion_iot.device_payload;

import io.github.orionlibs.core.content.Pagination;
import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.orion_iot.database.IoTDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DevicePayloadsDAO
{
    public static long getNumberOfRecords()
    {
        DevicePayloadModel model = DevicePayloadModel.builder()
                        .isDeleted(Boolean.FALSE)
                        .build();
        return Database.getNumberOfRecords(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.isDeleted));
    }


    public static long getNumberOfRecordsForDeviceID(Long deviceID)
    {
        DevicePayloadModel model = DevicePayloadModel.builder()
                        .deviceID(deviceID)
                        .isDeleted(Boolean.FALSE)
                        .build();
        return Database.getNumberOfRecords(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.deviceID,
                                        IoTDatabase.isDeleted));
    }


    public static List<DevicePayloadModel> getByTopic(String topic)
    {
        List<DevicePayloadModel> results = new ArrayList<>();
        DevicePayloadModel model = DevicePayloadModel.builder()
                        .topic(topic)
                        .isDeleted(Boolean.FALSE)
                        .build();
        List<Object> temp = Database.getModels(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.topic,
                                        IoTDatabase.isDeleted));
        if(temp != null && !temp.isEmpty())
        {
            temp.forEach(record -> results.add((DevicePayloadModel)record));
        }
        return results;
    }


    public static List<DevicePayloadModel> getNLatestByDeviceID(Long deviceID, int numberOfLatestRecords)
    {
        List<DevicePayloadModel> results = new ArrayList<>();
        DevicePayloadModel model = DevicePayloadModel.builder()
                        .deviceID(deviceID)
                        .isDeleted(Boolean.FALSE)
                        .build();
        List<Object> temp = Database.getModelsWithDescendingOrder(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.deviceID,
                                        IoTDatabase.isDeleted),
                        IoTDatabase.devicePayloadID,
                        Pagination.ofLimit(numberOfLatestRecords));
        if(temp != null && !temp.isEmpty())
        {
            temp.forEach(record -> results.add((DevicePayloadModel)record));
        }
        return results;
    }


    public static int save(DevicePayloadModel model)
    {
        return Database.saveModel(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase);
    }


    public static int update(DevicePayloadModel model)
    {
        return Database.updateModel(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.devicePayloadID));
    }
}