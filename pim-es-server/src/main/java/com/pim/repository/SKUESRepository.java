package com.pim.repository;

import com.pim.indexes.model.SkuDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKUESRepository extends ElasticsearchRepository<SkuDocument, String> {

}