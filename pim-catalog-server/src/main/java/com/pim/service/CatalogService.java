package com.pim.service;

import com.pim.model.AssetAuditData;
import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.repository.CatalogRepository;
import com.pim.repository.domain.ProductInfo;
import com.pim.repository.domain.SkuInfo;
import com.pim.utils.AuditChanges;
import com.pim.utils.AuditHelper;
import com.pim.utils.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by pkulkar4 on 8/1/18.
 */

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private AuditService auditService;

    @Autowired
    private ESService esService;

    @Autowired
    private AuditHelper helper;

    @Autowired
    private BeanConverter beanConverter;

    public void load(SkuModel skuData) {

        AssetAuditData auditRequest = null;
        Optional<SkuInfo> oSkuInfo = catalogRepository.getSku(skuData.getId());
        if (oSkuInfo.isPresent()) {
            List<AuditChanges> auditChanges = helper.getSKUChangeRequest(beanConverter.convertSKUInfotoModel(oSkuInfo.get()), skuData);
            auditRequest = AssetAuditData.builder().actionPerformed("update").updatedTime(new Date())
                    .changedProperties(auditChanges.stream().map(
                            e->e.getPropertyName()).collect(Collectors.toList()))
                    .Id(skuData.getId()).build();
        }else {
            auditRequest = AssetAuditData.builder().actionPerformed("create")
                    .updatedTime(new Date())
                    .Id(skuData.getId()).build();
        }
        SkuInfo skuInfo = beanConverter.convertSKUModeltoInfo(skuData);
        catalogRepository.save(skuInfo);
        esService.updateESSkuChanges(beanConverter.convertSKUInfotoESData(skuInfo));
        auditService.recordSkuChanges(auditRequest);
    }

    public List<SkuModel> getSkus(int total) {
        return catalogRepository.getSku(total).orElse(new ArrayList<SkuInfo>()).stream().map(
                e -> beanConverter.convertSKUInfotoModel(e)
        ).collect(Collectors.toList());
    }

    public void load(ProductInfo productInfo) {

        AssetAuditData auditRequest = null;
        Optional<ProductInfo> oProductInfo = catalogRepository.getProduct(productInfo.getId());
        if (oProductInfo.isPresent()) {
            List<AuditChanges> auditChanges = helper.getProductChangeRequest(oProductInfo.get(), productInfo);
            auditRequest = AssetAuditData.builder().actionPerformed("update").updatedTime(new Date())
                    .changedProperties(auditChanges.stream().map(
                            e->e.getPropertyName()).collect(Collectors.toList()))
                    .Id(productInfo.getId()).build();
        }else {
            auditRequest = AssetAuditData.builder().actionPerformed("create")
                    .updatedTime(new Date())
                    .Id(productInfo.getId()).build();
        }
        catalogRepository.save(productInfo);
        esService.updateESProductChanges(productInfo);
        auditService.recordProductChanges(auditRequest);

    }
}
