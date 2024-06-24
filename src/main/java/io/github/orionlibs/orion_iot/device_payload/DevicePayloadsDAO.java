package io.github.orionlibs.orion_iot.device_payload;

import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.orion_iot.database.IoTDatabase;
import java.util.Arrays;

public class DevicePayloadsDAO
{
    public static long getNumberOfRecords()
    {
        return Database.getNumberOfRecords(IoTDatabase.tableDevicePayloads, IoTDatabase.deviceDataDatabase);
    }


    public static long getNumberOfRecordsForDeviceID(Long deviceID)
    {
        DevicePayloadModel model = DevicePayloadModel.builder()
                        .deviceID(deviceID)
                        .build();
        return Database.getNumberOfRecords(model,
                        IoTDatabase.tableDevicePayloads,
                        IoTDatabase.deviceDataDatabase,
                        Arrays.asList(IoTDatabase.deviceID));
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