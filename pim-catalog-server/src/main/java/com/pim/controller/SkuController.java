package com.pim.controller;

import com.pim.model.SkuModel;
import com.pim.repository.domain.SkuInfo;
import com.pim.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pkulkar4 on 8/1/18.
 */

@RestController
public class SkuController {

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(method = RequestMethod.GET, value = "/sku/load", produces = "application/json")
    public ResponseEntity loadSkuData(){
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("color","Black");
        attributes.put("size", "8");
        catalogService.load(SkuModel
                .builder().id("11223344556")
                .displayName("NikeBlackShoes").dynamicAttributes(attributes).build());

        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sku/save", produces = "application/json")
    public ResponseEntity saveSku(@RequestBody SkuModel model){
        catalogService.load(model);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sku/top", produces = "application/json")
    public ResponseEntity<List<SkuModel>> getTopSku(){
        return new ResponseEntity<List<SkuModel>>(catalogService.getSkus(10), HttpStatus.OK) ;
    }
}
