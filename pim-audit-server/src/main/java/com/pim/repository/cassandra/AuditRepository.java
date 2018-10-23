package com.pim.repository.cassandra;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.pim.repository.cassandra.model.ProductAudit;
import com.pim.repository.cassandra.model.SKUAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Created by pkulkar4 on 8/11/18.
 */
@Repository
public class AuditRepository {

    Logger logger = LoggerFactory.getLogger(AuditRepository.class);

    @Autowired
    CassandraTemplate cassandraTemplate;

    public void recordProductChanges(ProductAudit audit) {
        cassandraTemplate.insert(audit);
    }

    public Optional<List<ProductAudit>> getProductAuditRecords(String productId, int limit){
        Select select = QueryBuilder.select().from("product_audit");
        select.where(QueryBuilder.eq("product_id", productId));
        select.orderBy(QueryBuilder.desc("update_time"));
        select.limit(limit);
        logger.info("pull the product records : {}" , select.toString());
        return Optional.ofNullable(cassandraTemplate.select(select
                , ProductAudit.class));
    }

    public void recordSkuChanges(SKUAudit audit) {
        cassandraTemplate.insert(audit);
    }

    public Optional<List<SKUAudit>> getSkuAuditRecords(String skuId, int limit){
        Select select = QueryBuilder.select().from("sku_audit");
        select.where(QueryBuilder.eq("sku_id", skuId));
        select.orderBy(QueryBuilder.desc("update_time"));
        select.limit(limit);
        logger.info("pull the product records : {}" , select.toString());
        return Optional.ofNullable(cassandraTemplate.select(select
                , SKUAudit.class));
    }

}
