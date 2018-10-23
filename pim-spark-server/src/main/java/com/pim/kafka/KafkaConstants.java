package com.pim.kafka;

public interface KafkaConstants {
	
	public static String KAFKA_BROKER_STRING =   
            "kafka:9092";
    public static String KAFKA_KEY_SERIALIZER =   
            "org.apache.kafka.common.serialization.StringSerializer";  
    public static String KAFKA_VALUE_SERIALIZER =   
            "org.apache.kafka.common.serialization.StringSerializer";  
    public static String KAFKA_TOPIC = "sku-feed-topic.t";  
    public static String KAFKA_CONSUMER_GROUP = "catalog-feeds";
}
	