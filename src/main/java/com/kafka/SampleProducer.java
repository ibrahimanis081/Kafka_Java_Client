package com.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;



class SampleProducer {

    public static void main(String[] args) throws InterruptedException {
        

        // create a properties to be used to configure the producer
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
       
        
        // create a KafkaProducer and pass in the properties
        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        /* create a ProducerRecord and pass in the topic, key and value to be sent
         * send the message
         */
        
         for (int i = 0;  i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<>("test", i, "Hello from java-" + i));
            System.out.println("message " + i + " sent to kafka");
            Thread.sleep(500L);
            } 
           
            kafkaProducer.close();
            System.out.println("Finished sending messages");
        }
    }
