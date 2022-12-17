package com.kafka;

import java.util.Properties;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;


class StreamsApi {
    public static void main(String[] args) {
        // create a properties object and pass in the configuraton on how our streams app behave
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        

        // create a new streambuilder object
        StreamsBuilder builder = new StreamsBuilder();

        //build topology
        KStream<Integer, String> source = builder.stream("test");

        source.filter((key, value) -> value.replaceAll())
        .peek((key, value) -> System.out.println(("processed " + key + " and " + value)));
        

        //create a kafkastreams application, pass in the streamsbuilder.build and properties
        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        
        //start the kafkastreams application
        streams.start();


        
    }
    
}
