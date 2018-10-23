package com.pim.utils;

import com.pim.model.ESSkuData;
import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import com.pim.repository.domain.SkuInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pkulkar4 on 9/10/18.
 */

@Component
public class BeanConverter {

    public SkuModel convertSKUInfotoModel(SkuInfo skuInfo){
        return SkuModel.builder()
                .id(skuInfo.getId())
        .displayName(skuInfo.getDisplayName())
        .description(skuInfo.getDescription())
        .parentProducts(skuInfo.getParentProducts())
        .creationDate(skuInfo.getCreationDate())
        .startDate(skuInfo.getStartDate())
        .endDate(skuInfo.getEndDate())
        .lastUpatedTime(skuInfo.getLastUpatedTime())
        .swatchImageReference(skuInfo.getSwatchImageReference())
        .type(skuInfo.getType())
        .skuNum(skuInfo.getSkuNum())
        .barcode(skuInfo.getBarcode())
        .hideDisplay(skuInfo.getHideDisplay())
        .status(skuInfo.getStatus())
        .dynamicAttributes(skuInfo.getDynamicAttributes())
        .lineAttributes(skuInfo.getLineAttributes())
        .skuPrice(skuInfo.getSkuPrice())
        .build();
    }

    public SkuInfo convertSKUModeltoInfo(SkuModel skuModel){
        return SkuInfo.builder()
                .id(skuModel.getId())
                .displayName(skuModel.getDisplayName())
                .description(skuModel.getDescription())
                .parentProducts(skuModel.getParentProducts())
                .creationDate(skuModel.getCreationDate())
                .startDate(skuModel.getStartDate())
                .endDate(skuModel.getEndDate())
                .lastUpatedTime(skuModel.getLastUpatedTime())
                .swatchImageReference(skuModel.getSwatchImageReference())
                .type(skuModel.getType())
                .skuNum(skuModel.getSkuNum())
                .barcode(skuModel.getBarcode())
                .hideDisplay(skuModel.getHideDisplay())
                .status(skuModel.getStatus())
                .dynamicAttributes(skuModel.getDynamicAttributes())
                .lineAttributes(skuModel.getLineAttributes())
                .skuPrice(skuModel.getSkuPrice())
                .build();
    }

    public ESSkuData convertSKUInfotoESData(SkuInfo skuInfo){
        return ESSkuData.builder()
                .id(skuInfo.getId())
                .displayName(skuInfo.getDisplayName())
                .description(skuInfo.getDescription())
                .parentProducts(skuInfo.getParentProducts())
                .creationDate(skuInfo.getCreationDate())
                .startDate(skuInfo.getStartDate())
                .endDate(skuInfo.getEndDate())
                .lastUpatedTime(skuInfo.getLastUpatedTime())
                .swatchImageReference(skuInfo.getSwatchImageReference())
                .type(skuInfo.getType())
                .skuNum(skuInfo.getSkuNum())
                .barcode(skuInfo.getBarcode())
                .hideDisplay(skuInfo.getHideDisplay())
                .status(skuInfo.getStatus())
                .dynamicAttributes(skuInfo.getDynamicAttributes())
                .lineAttributes(skuInfo.getLineAttributes())
                .skuPrice(skuInfo.getSkuPrice())
                .build();
    }
}
