package com.pim.repository.cassandra;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import com.pim.model.SkuModel;
import com.pim.repository.CatalogRepository;
import com.pim.repository.domain.ProductInfo;
import com.pim.repository.domain.SkuInfo;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.cql.WriteOptions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by pkulkar4 on 8/1/18.
 */

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private Session session;

    @Autowired
    CassandraTemplate cassandraTemplate;

    public boolean save(SkuInfo skuInfo){
        logger.info("Saving the sku data : " + skuInfo.getId());

        cassandraTemplate.insert(skuInfo, InsertOptions.builder()
                        .consistencyLevel(ConsistencyLevel.LOCAL_QUORUM).build());
//        if(!session.isClosed()){
//            String insertStr = QueryBuilder.insertInto("sku_info").
//                    value("skuId", skuInfo.getId())
//                    .value("name", skuInfo.getDisplayName())
//                    .value("attributes", skuInfo.getDynamicAttributes())
//                    .value("lastUpdatedTime", UUIDs.timeBased())
//                    .ifNotExists()
//                    .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM)
//                    .toString();
//            session.execute(insertStr);
//            return true;
//        }
        return true;
    }

    public Optional<List<SkuInfo>> getSku(int total) {
        return Optional.ofNullable(cassandraTemplate.select(
                QueryBuilder.select().from("sku_info")
                        .limit(total), SkuInfo.class));
    }

    @Override
    public Optional<SkuInfo> getSku(String skuId) {
        Select select = QueryBuilder.select().from("sku_info")
                .where(QueryBuilder.eq("skuid",skuId)).limit(1);
        logger.info(select.toString());
        return Optional.ofNullable(cassandraTemplate.selectOne(select, SkuInfo.class));
    }

    @Override
    public Optional<ProductInfo> getProduct(String id) {
        Select select = QueryBuilder.select().from("product_info")
                .where(QueryBuilder.eq("productid",id)).limit(1);
        logger.info(select.toString());
        return Optional.ofNullable(cassandraTemplate.selectOne(select, ProductInfo.class));
    }

    @Override
    public boolean save(ProductInfo productInfo) {
        logger.info("Saving the product data : " + productInfo.getId());

        cassandraTemplate.insert(productInfo, InsertOptions.builder()
                .consistencyLevel(ConsistencyLevel.LOCAL_QUORUM).build());
        return true;
    }
}
