package com.pim.utils;

import com.pim.model.AssetAuditData;
import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by pkulkar4 on 8/11/18.
 */

@Component
public class AuditHelper {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        logger.info("Create the RestTemplate Builder.");
        return builder.build();
    }

    public List<AuditChanges> getSKUChangeRequest(SkuModel oSkuData, SkuModel nSkuData) {
        return compareObjects(oSkuData,nSkuData, new HashSet<>(), 5L);
    }

    public List<AuditChanges> getProductChangeRequest(ProductModel productModel1, ProductModel productModel2) {
        return compareObjects(productModel1, productModel2, new HashSet<>(), 5L);
    }

    public List<AuditChanges> compareObjects(Object oldObject, Object newObject, Set<String> propertyNamesToAvoid, Long deep) {
        return compareObjects(oldObject, newObject, propertyNamesToAvoid, deep, null);
    }

    private List<AuditChanges> compareObjects(Object oldObject, Object newObject, Set<String> propertyNamesToAvoid, Long deep,
                                        String parentPropertyPath) {
        propertyNamesToAvoid = propertyNamesToAvoid != null ? propertyNamesToAvoid : new HashSet<>();
        parentPropertyPath = parentPropertyPath != null ? parentPropertyPath : "";

        propertyNamesToAvoid.add("class");

        List<AuditChanges> diffObject = new ArrayList<>();

        BeanMap map = new BeanMap(oldObject);

        PropertyUtilsBean propUtils = new PropertyUtilsBean();

        for (Object propNameObject : map.keySet()) {
            String propertyName = (String) propNameObject;
            String propertyPath = parentPropertyPath + propertyName;

            if (!propUtils.isReadable(newObject, propertyName)
                    || propertyNamesToAvoid.contains(propertyPath)) {
                continue;
            }

            Object property1 = null;
            try {
                property1 = propUtils.getProperty(oldObject, propertyName);
            } catch (Exception e) {
            }
            Object property2 = null;
            try {
                property2 = propUtils.getProperty(newObject, propertyName);
            } catch (Exception e) {
            }
            try {
                if (property1 != null && property2 != null && property1.getClass().getName().startsWith("com.racing.company")
                        && (deep == null || deep > 0)) {
                    List<AuditChanges> diffProperty = compareObjects(property1, property2, propertyNamesToAvoid,
                            deep != null ? deep - 1 : null, propertyPath + ".");
                    diffObject.addAll(diffProperty);
                } else {
                    if (!Objects.deepEquals(property1, property2)) {
                        diffObject.add(AuditChanges.builder().propertyName(
                                propertyPath).OldValue(property1).newValue(property2).build());
//                        logger.info("> " + propertyPath + " is different (oldValue=\"" + property1 + "\", newValue=\""
//                                + property2 + "\")");
                    }
//                    else {
//                        logger.info(" {} is equal", propertyPath);
//                    }
                }
            } catch (Exception e) {
            }
        }

        return diffObject;
    }
}