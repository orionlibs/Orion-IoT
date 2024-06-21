package io.github.orionlibs.orion_iot;

import static io.netty.util.CharsetUtil.UTF_8;

import io.moquette.broker.Server;
import io.moquette.broker.config.ClasspathResourceLoader;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.IResourceLoader;
import io.moquette.broker.config.ResourceLoaderConfig;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.mqtt.MqttMessageBuilders;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class MQTTMessageBrokerClientTest
{
    private static final String TOPIC = "topic/test";


    @Test
    public void whenSendSingleMessage_thenSuccess() throws Exception
    {
        IResourceLoader classpathLoader = new ClasspathResourceLoader();
        final IConfig classPathConfig = new ResourceLoaderConfig(classpathLoader);
        final Server mqttBroker = new Server();
        mqttBroker.startServer(classPathConfig);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            mqttBroker.stopServer();
        }));
        String clientID = "demo_client";
        MQTTMessageBrokerClient client = new MQTTMessageBrokerClient(TOPIC, "tcp://0.0.0.0:1883", clientID);
        MqttPublishMessage message = MqttMessageBuilders.publish()
                        .topicName(TOPIC)
                        .retained(true)
                        .qos(MqttQoS.AT_LEAST_ONCE)
                        .payload(Unpooled.copiedBuffer("Hello World!!".getBytes(UTF_8)))
                        .build();
        mqttBroker.internalPublish(message, clientID);
        mqttBroker.disconnectAndPurgeClientState(clientID);
        mqttBroker.stopServer();
        client.close();
    }
}
