package com.pim.controller;

import com.pim.indexes.model.SkuDocument;
import com.pim.service.ESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
