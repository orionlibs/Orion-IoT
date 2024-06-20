package io.github.orionlibs.orion_iot;

import static io.netty.util.CharsetUtil.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.moquette.broker.Server;
import io.moquette.broker.config.ClasspathResourceLoader;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class MQTTMessageBrokerClientTest
{
    @Test
    public void whenSendSingleMessage_thenSuccess() throws Exception
    {
        IResourceLoader classpathLoader = new ClasspathResourceLoader();
        final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);
        final Server mqttBroker = new Server();
        mqttBroker.startServer(classPathConfig);
        System.out.println("Broker started");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            mqttBroker.stopServer();
        }));
        MqttClient subscriberClient = new MqttClient("tcp://0.0.0.0:1883", "demo_client");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        subscriberClient.connect(options);
        subscriberClient.subscribe(MQTTMessageBrokerClient.TOPIC, (topic, msg) -> {
            assertEquals("Hello World!!", new String(msg.getPayload()));
        });
        MqttPublishMessage message = MqttMessageBuilders.publish()
                        .topicName(MQTTMessageBrokerClient.TOPIC)
                        .retained(true)
                        .qos(MqttQoS.AT_LEAST_ONCE)
                        .payload(Unpooled.copiedBuffer("Hello World!!".getBytes(UTF_8)))
                        .build();
        mqttBroker.internalPublish(message, "demo_client");
        mqttBroker.disconnectAndPurgeClientState("demo_client");
        mqttBroker.stopServer();
        if(subscriberClient.isConnected())
        {
            subscriberClient.disconnect();
            subscriberClient.close();
        }
    }
}
