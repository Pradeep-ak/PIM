package com.pim.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pim.model.AssetAuditData;
import com.pim.model.ESSkuData;
import com.pim.repository.domain.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by pkulkar4 on 9/14/18.
 */
@Service
public class ESService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${com.pim.es-url.sku}")
    private String ESSkuUdateUrl;

    @Value("${com.pim.es-url.product}")
    private String ESProductUdateUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaService kafkaService;

    @HystrixCommand(commandKey = "ESSkuChanges", groupKey = "ESServer",
            fallbackMethod = "updateESSkuChangesFB")
    public boolean updateESSkuChanges(ESSkuData skuData){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ESSkuData> request = new HttpEntity<ESSkuData>(
                skuData, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ESSkuUdateUrl, HttpMethod.POST, request, String.class);

        if (responseEntity.getStatusCode()== HttpStatus.OK)
            logger.info("sku changes are recorded.");

        return true;
    }

    public boolean updateESSkuChangesFB(ESSkuData skuData, Throwable e){
        logger.error("failed to publish the message", e );
        kafkaService.sendESSkuMsg(skuData.getId());
        return false;
    }

    @HystrixCommand(commandKey = "ESProductChanges", groupKey = "ESServer",
            fallbackMethod = "updateESProductChangesFB")
    public boolean updateESProductChanges(ProductInfo productInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductInfo> request = new HttpEntity<ProductInfo>(
                productInfo, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                ESProductUdateUrl, HttpMethod.POST, request, String.class);

        if (responseEntity.getStatusCode()== HttpStatus.OK)
            logger.info("Product changes are recorded.");

        return true;
    }

    public boolean updateESProductChangesFB(ProductInfo productInfo, Throwable e){
        logger.error("failed to publish the message", e );
        kafkaService.sendESProductMsg(productInfo.getId());
        return false;
    }
}
