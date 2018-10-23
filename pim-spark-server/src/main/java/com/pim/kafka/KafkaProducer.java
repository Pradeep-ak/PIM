package com.pim.kafka;

import com.pim.kafka.KafkaConstants;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaProducer {  
    Properties props;  
    Producer<String, String> producer;  
  
    public KafkaProducer(String brokerString) {  
        props = new Properties();  
        props.put("bootstrap.servers", brokerString);  
//        props.put("acks", "all");  
//        props.put("retries", 0);  
//        props.put("batch.size", 10);  
//        props.put("linger.ms", 10);  
//        props.put("request.timeout.ms", 30000);
//        props.put("buffer.memory", 33554432);  
        props.put("key.serializer", KafkaConstants.KAFKA_KEY_SERIALIZER);
        props.put("value.serializer", KafkaConstants.KAFKA_VALUE_SERIALIZER);
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);  
    }  
  
    /* send() method is asynchronous */  
    public void sendMessage(String topic, String message) {  
  
    		long time = System.currentTimeMillis();

    		final ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
		try {
			RecordMetadata metadata = producer.send(record).get();
			long elapsedTime = System.currentTimeMillis() - time;
            System.out.printf("sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d\n",
                            record.key(), record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);
            producer.flush();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    		
        //return future;  
    }  
  
    public void sendMessage(String topic, String key, String value) {  
        producer.send(new ProducerRecord<>(topic, key, value));  
    }  
  
    public void closeProducer() {  
        producer.close();  
    }  
  
} 
