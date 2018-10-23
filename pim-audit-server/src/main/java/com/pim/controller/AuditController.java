package com.pim.controller;

import com.pim.repository.cassandra.AuditRepository;
import com.pim.repository.cassandra.model.ProductAudit;
import com.pim.repository.cassandra.model.SKUAudit;
import com.pim.service.AuditService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pkulkar4 on 8/11/18.
 */
@RestController
public class AuditController {

    @Autowired
    AuditService auditService;

    @PostMapping(value = "/save/product", produces = "application/json")
    public ResponseEntity<Boolean> recordProductChanges(@RequestBody ProductAudit productAudit){
        auditService.recordProductChanges(productAudit);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{productId}", produces = "application/json")
    public ResponseEntity<List<ProductAudit>> getProductChanges(
            @PathVariable(value = "productId") String productId) {
        return new ResponseEntity<List<ProductAudit>>(
                auditService.getProductAuditRecords(
                        productId).orElse(new ArrayList<>()), HttpStatus.OK
        );
    }

    @PostMapping(value = "/save/sku", produces = "application/json")
    public ResponseEntity<Boolean> recordSkuChanges(@RequestBody SKUAudit skuAudit){
        auditService.recordSkuChanges(skuAudit);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/sku/{skuId}", produces = "application/json")
    public ResponseEntity<List<SKUAudit>> getSkuChanges(
            @PathVariable(value = "skuId") String skuId) {
        return new ResponseEntity<List<SKUAudit>>(
                auditService.getSkuAuditRecords(
                        skuId).orElse(new ArrayList<>()), HttpStatus.OK
        );
    }
}
