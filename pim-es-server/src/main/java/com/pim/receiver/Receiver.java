package com.pim.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pim.indexes.model.SkuDocument;
import com.pim.service.ESService;
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
    private ESService esService;

    @KafkaListener(topics = "${kafka.topic.skuESTopic}")
    public void receive(String payload) throws IOException {
        logger.info("received payload for sku ='{}'", payload);
        esService.save(payload);
    }
}
