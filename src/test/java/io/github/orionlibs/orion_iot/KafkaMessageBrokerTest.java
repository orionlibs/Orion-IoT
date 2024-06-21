package io.github.orionlibs.orion_iot;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;*/
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;
//import org.springframework.kafka.test.utils.KafkaTestUtils;

@TestInstance(Lifecycle.PER_CLASS)
//@Execution(ExecutionMode.CONCURRENT)
public class KafkaMessageBrokerTest extends ATest
{
    /*private static EmbeddedKafkaKraftBroker embeddedKafkaBroker;
    private static KafkaProducer<String, String> producer;
    private static KafkaConsumer<String, String> consumer;


    @BeforeEach
    void setUp()
    {
        //embeddedKafkaBroker = new EmbeddedKafkaZKBroker(1, true, "testTopic");
        embeddedKafkaBroker = new EmbeddedKafkaKraftBroker(1, 1, "testTopic");
        embeddedKafkaBroker.afterPropertiesSet();
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(producerProps);
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList("testTopic"));
    }


    @AfterEach
    public void teardown() throws MqttException
    {
        producer.close();
        consumer.close();
        embeddedKafkaBroker.destroy();
    }


    @Test
    @Disabled
    public void testPublishSubscribe()
    {
        String topic = "testTopic";
        String message = "Hello Kafka!";
        producer.send(new ProducerRecord<>(topic, message));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(3));
        assertEquals(1, records.count());
        for(ConsumerRecord<String, String> record : records)
        {
            assertEquals(message, record.value());
        }
    }*/
}
