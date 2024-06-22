package io.github.orionlibs.orion_iot.device_message;

import io.github.orionlibs.core.calendar.CalendarService;
import io.github.orionlibs.orion_iot.config.ConfigurationService;
import io.github.orionlibs.orion_iot.database.Database;
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
                            "." + ConfigurationService.getProp("orionlibs.orion-iot.database.of.iot.device.data.and.device.payloads.table.name"),
                            ConfigurationService.getProp("orionlibs.orion-iot.database.of.iot.device.data.name"));
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