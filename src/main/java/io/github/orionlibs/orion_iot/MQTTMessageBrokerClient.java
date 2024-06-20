package io.github.orionlibs.orion_iot;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTMessageBrokerClient
{
    private MqttClient subscriberClient;
    public String testVariable;


    public MQTTMessageBrokerClient(String testText)
    {
        this.testVariable = "hello there " + testText;
    }


    public MQTTMessageBrokerClient(String topicToSubscribeTo, String mqttAddressToConnectTo, String mqttClientID) throws MqttException
    {
        subscriberClient = new MqttClient(mqttAddressToConnectTo, mqttClientID);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        subscriberClient.connect(options);
        subscriberClient.subscribe(topicToSubscribeTo, (topic, msg) -> {
            String messageRead = new String(msg.getPayload());
            //assertEquals("Hello World!!", messageRead);
        });
    }


    public void stopClient() throws MqttException
    {
        if(subscriberClient.isConnected())
        {
            subscriberClient.disconnect();
            subscriberClient.close();
        }
    }
}