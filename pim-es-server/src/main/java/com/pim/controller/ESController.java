package com.pim.controller;

import com.pim.indexes.model.SkuDocument;
import com.pim.service.ESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pkulkar4 on 9/12/18.
 */
@RestController
@RequestMapping("/es")
public class ESController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ESService esService;

    @RequestMapping(value = "/indexSku", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Boolean> addSku(@RequestBody SkuDocument skuData){
        logger.info("input Data of sku is : {}", skuData);
        esService.save(skuData);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/sku/{skuId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<SkuDocument> getSku(@PathVariable(value = "skuId") String skuId){
        logger.info("Get Sku detail for sku : {}", skuId);
        return new ResponseEntity<SkuDocument>(esService.get(skuId), HttpStatus.OK);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
