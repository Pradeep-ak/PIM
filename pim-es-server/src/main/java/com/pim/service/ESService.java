package com.pim.service;

import com.pim.indexes.model.ProductDocument;
import com.pim.indexes.model.SkuDocument;
import com.pim.repository.ProductESRepository;
import com.pim.repository.SKUESRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

/**
 * Created by pkulkar4 on 9/12/18.
 */
@Service
public class ESService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
    }
}
