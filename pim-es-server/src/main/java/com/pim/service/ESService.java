package com.pim.service;

import com.pim.indexes.model.ProductDocument;
import com.pim.indexes.model.SkuDocument;
import com.pim.repository.ProductESRepository;
import com.pim.repository.SKUESRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by pkulkar4 on 9/12/18.
 */
@Service
public class ESService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${com.pim.catalog-url.sku}")
    private String catalogSkuUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SKUESRepository skuesRepository;

    @Autowired
    private ProductESRepository productESRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Boolean save(SkuDocument skuData) {
//        productESRepository.save(ProductDocument.builder().build());
        if(skuData.getParentProducts() == null)
            skuData.setParentProducts("d00000");
        skuesRepository.save(skuData);
//        elasticsearchTemplate.createIndex(SkuDocument.class);
//        elasticsearchTemplate.createIndex(ProductDocument.class);
//        elasticsearchTemplate.putMapping(SkuDocument.class);
//
//        IndexQuery productDoc = new IndexQuery();
//        ProductDocument productDocument = ProductDocument.builder().build();
//        productDoc.setId(productDocument.getProductId());
//        productDoc.setObject(productDocument);
//        elasticsearchTemplate.index(productDoc);
//
//        IndexQuery skuDoc = new IndexQuery();
//        skuDoc.setId(skuData.getId());
//        skuDoc.setObject(skuData);
//        skuDoc.setParentId(skuData.getParentProducts());
//        elasticsearchTemplate.index(skuDoc);
        return true;
    }

    public void save(String SkuId) {
        logger.info("update ES from queue : {}", SkuId);
        getSkuDetails(SkuId).ifPresent(data -> save(data));
    }

    Optional<SkuDocument> getSkuDetails (String skuId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> request = new HttpEntity<Object>(headers);

        ResponseEntity<SkuDocument> responseEntity = restTemplate.exchange(
                catalogSkuUrl, HttpMethod.GET, request, SkuDocument.class, skuId);

        return Optional.ofNullable(
                (responseEntity.getStatusCode() == HttpStatus.OK)?responseEntity.getBody():null
        );

    }

    public SkuDocument get(String skuId) {
        return skuesRepository.findById(skuId).orElse(new SkuDocument());
    }
}
