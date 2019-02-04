package com.pim.controller;

import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pkulkar4 on 8/1/18.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(method = RequestMethod.POST, value = "/product/save", produces = "application/json")
    public ResponseEntity saveSku(@RequestBody ProductModel model){
        catalogService.save(model);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/top", produces = "application/json")
    public ResponseEntity<List<ProductModel>> getTopSku(){
        return new ResponseEntity<List<ProductModel>>(
                catalogService.getProducts(10), HttpStatus.OK) ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{productId}", produces = "application/json")
    public ResponseEntity<ProductModel> getTopSku(@PathVariable(value = "productId") String productId){
        return new ResponseEntity<ProductModel>(
                catalogService.getProduct(productId), HttpStatus.OK) ;
    }
}
