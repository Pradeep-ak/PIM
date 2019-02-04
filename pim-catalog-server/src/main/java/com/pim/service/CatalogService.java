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
            auditRequest = AssetAuditData.builder().type("sku").actionPerformed("update").updatedTime(new Date())
                    .changedProperties(auditChanges.stream().map(
                            e->e.getPropertyName()).collect(Collectors.toList()))
                    .Id(skuData.getId()).build();
        }else {
            auditRequest = AssetAuditData.builder().type("sku").actionPerformed("create")
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

    public SkuModel getSku(String skuId) {
        return catalogRepository.getSku(skuId).map(e -> beanConverter.convertSKUInfotoModel(e)).orElse(new SkuModel());
    }

    public void save(ProductModel productModel) {
        AssetAuditData auditRequest = null;
        Optional<ProductInfo> productInfo = catalogRepository.getProduct(productModel.getProductId());
        if (productInfo.isPresent()) {
            List<AuditChanges> auditChanges = helper.getProductChangeRequest(beanConverter.convertProductInfotoModel(productInfo.get()), productModel);
            auditRequest = AssetAuditData.builder().type("product").actionPerformed("update").updatedTime(new Date())
                    .changedProperties(auditChanges.stream().map(
                            e->e.getPropertyName()).collect(Collectors.toList()))
                    .Id(productModel.getProductId()).build();
        }else {
            auditRequest = AssetAuditData.builder().type("product").actionPerformed("create")
                    .updatedTime(new Date())
                    .Id(productModel.getProductId()).build();
        }
        ProductInfo info = beanConverter.convertProductModeltoInfo(productModel);
        catalogRepository.save(info);
        esService.updateESProductChanges(info);
        auditService.recordProductChanges(auditRequest);
    }

    public List<ProductModel> getProducts(int count) {
        return catalogRepository.getProducts(count).orElse(new ArrayList<ProductInfo>()).stream().map(
                e -> beanConverter.convertProductInfotoModel(e)
        ).collect(Collectors.toList());
    }

    public ProductModel getProduct(String productId) {
        return catalogRepository.getProduct(productId).map(
                e -> beanConverter.convertProductInfotoModel(e)).orElse(new ProductModel());
    }
}
