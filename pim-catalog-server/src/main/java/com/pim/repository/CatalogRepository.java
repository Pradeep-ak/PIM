package com.pim.repository;

import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import com.pim.repository.domain.SkuInfo;

import java.util.List;
import java.util.Optional;

/**
 * Created by pkulkar4 on 8/31/18.
 */
public interface CatalogRepository {

    public boolean save(SkuInfo skuInfo);

    public Optional<List<SkuInfo>> getSku(int total);

    public Optional<SkuInfo> getSku(String skuId);

    public Optional<ProductInfo> getProduct(String id);

    public boolean save(ProductInfo productInfo);

    Optional<List<ProductInfo>> getProducts(int total);
}
