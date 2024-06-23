package io.github.orionlibs.orion_iot.device_payload;

import io.github.orionlibs.core.calendar.CalendarService;
import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.orion_iot.database.IoTDatabase;
import java.io.Closeable;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTMessageBrokerClient implements Closeable
{
    private MqttClient subscriberClient;


    public MQTTMessageBrokerClient(String topicToSubscribeTo, String mqttAddressToConnectTo, String mqttClientID) throws MqttException, IOException
    {
        subscriberClient = new MqttClient(mqttAddressToConnectTo, mqttClientID);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        subscriberClient.connect(options);
        subscriberClient.subscribe(topicToSubscribeTo, (topic, message) -> {
            String messageRead = new String(message.getPayload());
            Database.saveModel(DevicePayloadModel.builder()
                                            .topic(topic)
                                            .payload(messageRead)
                                            .timestampOfRecord(CalendarService.getCurrentDatetimeAsSQLTimestamp())
                                            .build(),
                            IoTDatabase.tableDevicePayloads, IoTDatabase.deviceDataDatabase);
        });
    }


    private void stopClient() throws MqttException
    {
        if(subscriberClient.isConnected())
        {
            subscriberClient.disconnect();
            subscriberClient.close();
        }
    }


    @Override
    public void close() throws IOException
    {
        try
        {
            stopClient();
        }
        catch(MqttException e)
        {
            throw new IOException(e);
        }
    }
}