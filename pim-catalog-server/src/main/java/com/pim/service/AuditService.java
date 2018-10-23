package com.pim.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pim.model.AssetAuditData;
import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import com.pim.utils.AuditHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by pkulkar4 on 8/11/18.
 */
@Service
@EnableCircuitBreaker
public class AuditService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.pim.audit-url.sku}")
    private String auditSkuURL;

    @Value("${com.pim.audit-url.product}")
    private String auditProductURL;

    @Autowired
    private AuditHelper helper;

    @Autowired
    private KafkaService kafkaService;

    @HystrixCommand(commandKey = "recordSkuChanges", groupKey = "auditServer",
            fallbackMethod = "recordSkuChangesFB")
    public Boolean recordSkuChanges(AssetAuditData auditRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AssetAuditData> request = new HttpEntity<AssetAuditData>(
                auditRequest, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                auditSkuURL, HttpMethod.POST, request, String.class);
        if (responseEntity.getStatusCode()== HttpStatus.OK)
            logger.info("sku changes are recorded.");
        return true;

    }

    public Boolean recordSkuChangesFB(AssetAuditData auditRequest, Throwable e) {
        logger.error("the Recording of the event for Audit is not successful.", e);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(auditRequest);
            kafkaService.sendSkuAuditMsg(json);
            return true;
        } catch (JsonProcessingException e1) {
            logger.error("Error while paser to JSON for Kafka Topic ", e1);
        }
        return false;
    }

    @HystrixCommand(commandKey = "recordProductChanges", groupKey = "auditServer",
            fallbackMethod = "recordProductChangesFB")
    public Boolean recordProductChanges(AssetAuditData auditRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AssetAuditData> request = new HttpEntity<AssetAuditData>(
                auditRequest, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                auditProductURL, HttpMethod.POST, request, String.class);
        if (responseEntity.getStatusCode()== HttpStatus.OK)
            logger.info("product changes are recorded.");
        return true;
    }

    public Boolean recordProductChangesFB(AssetAuditData auditRequest, Throwable e) {
        logger.error("the Recording of the event for Audit is not successful.", e);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(auditRequest);
            kafkaService.sendProductAuditMsg(json);
            return true;
        } catch (JsonProcessingException e1) {
            logger.error("Error while paser to JSON for Kafka Topic ", e1);
        }
        return false;
    }
}
