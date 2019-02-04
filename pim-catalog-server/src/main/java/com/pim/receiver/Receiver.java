package com.pim.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import com.pim.service.CatalogService;
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

    Logger LOGGER =
            LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CatalogService catalogService;

    @KafkaListener(topics = "${kafka.topic.feed.skuTopic}")
    public void receiveSKU(String payload) throws IOException {
        LOGGER.info("received payload for catalog-sku-feed ='{}'", payload);
        ObjectMapper mapper = new ObjectMapper();
        SkuModel skuModel = mapper.readValue(payload, SkuModel.class);
        catalogService.load(skuModel);
    }

    @KafkaListener(topics = "${kafka.topic.feed.productTopic}")
    public void receiveProduct(String payload) throws IOException {
        LOGGER.info("received payload for catalog-product-feed ='{}'", payload);
        ObjectMapper mapper = new ObjectMapper();
        ProductModel productModel = mapper.readValue(payload, ProductModel.class);
        catalogService.save(productModel);
    }
}
