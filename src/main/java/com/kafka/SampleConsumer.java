package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


class SampleConsumer {

    public static void main(String[] args) {
        
        // create a properties to be used to configure the consumer
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // create a KafkaConsumer object and pass in the properties as an arguement  
        KafkaConsumer<Integer, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // subscribe to a list topic to be consumed
        kafkaConsumer.subscribe(Arrays.asList("test"));
        
        /* Create a ConsumerRecord Object called records 
         * Poll for messages to arrive
         * Use for each to loop over the records
         * Print the key value and partition of each record
         */
        while (true) {
            ConsumerRecords<Integer, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.println("Message recieved: " + " key: " + record.key() + ", value: " +  record.value() 
                                         + ", " + "in partition " + record.partition());
                }
           

        }
    }
    
}
 