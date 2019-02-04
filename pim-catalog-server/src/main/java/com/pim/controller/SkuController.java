package com.pim.controller;

import com.pim.model.SkuModel;
import com.pim.repository.domain.SkuInfo;
import com.pim.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pkulkar4 on 8/1/18.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SkuController {

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(method = RequestMethod.POST, value = "/sku/save", produces = "application/json")
    public ResponseEntity saveSku(@RequestBody SkuModel model){
        catalogService.load(model);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sku/top", produces = "application/json")
    public ResponseEntity<List<SkuModel>> getTopSku(){
        return new ResponseEntity<List<SkuModel>>(catalogService.getSkus(10), HttpStatus.OK) ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sku/{skuId}", produces = "application/json")
    public ResponseEntity<SkuModel> getTopSku(@PathVariable(value = "skuId") String skuId){
        return new ResponseEntity<SkuModel>(catalogService.getSku(skuId), HttpStatus.OK) ;
    }
}
