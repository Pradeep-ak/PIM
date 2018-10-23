package com.pim.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pim.repository.cassandra.model.SKUAudit;
import com.pim.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by pkulkar4 on 9/11/18.
 */
@Component
public class Receiver {

    Logger logger =
            LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuditService auditService;

    @KafkaListener(topics = "${kafka.topic.skuAuditTopic}")
    public void receive(String payload) throws IOException {
        logger.info("received payload='{}'", payload);
        ObjectMapper mapper = new ObjectMapper();
        SKUAudit skuAudit = mapper.readValue(payload, SKUAudit.class);
        auditService.recordSkuChanges(skuAudit);
    }
}
