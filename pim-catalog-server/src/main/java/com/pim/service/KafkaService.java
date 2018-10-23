package com.pim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



/**
 * Created by pkulkar4 on 9/11/18.
 */
@Service
public class KafkaService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic.audit.skuTopic}")
    private String skuAuditTopic;

    @Value("${kafka.topic.audit.productTopic}")
    private String productAuditTopic;

    @Value("${kafka.topic.ES.skuTopic}")
    private String ESSkuTopic;

    @Value("${kafka.topic.ES.productTopic}")
    private String ESProductTopic;

    public boolean sendSkuAuditMsg(String payload){
        logger.info("Sending Sku Audit message in Kafka ("+ skuAuditTopic +") : " + payload);
        kafkaTemplate.send(skuAuditTopic, payload);
        return true;
    }

    public boolean sendProductAuditMsg(String payload) {
        logger.info("Sending product Audit message in Kafka ("+ productAuditTopic +") : " + payload);
        kafkaTemplate.send(productAuditTopic, payload);
        return true;
    }

    public boolean sendESSkuMsg(String payload){
        logger.info("Sending ES Sku message in Kafka ("+ ESSkuTopic +") : " + payload);
        kafkaTemplate.send(ESSkuTopic, payload);
        return true;
    }

    public boolean sendESProductMsg(String payload) {
        logger.info("Sending ES Product message in Kafka ("+ ESProductTopic +") : " + payload);
        kafkaTemplate.send(ESProductTopic, payload);
        return true;
    }
}
