package com.pim.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pim.model.ESSkuData;
import com.pim.model.ProductModel;
import com.pim.model.SkuModel;
import com.pim.repository.domain.ProductInfo;
import com.pim.repository.domain.SkuInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pkulkar4 on 9/10/18.
 */

@Component
public class BeanConverter {

    @Autowired
    DozerBeanMapper dozerBeanMapper;

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

    public ProductModel convertProductInfotoModel(ProductInfo productInfo) {
        return dozerBeanMapper.map(productInfo, ProductModel.class);

//        return ProductModel.builder()
//        .id(productInfo.getId())
//        .daysAvailable(productInfo.getDaysAvailable())
//        .childSKUs(productInfo.getChildSKUs())
//        .description(productInfo.getDescription())
//        .alternateImages(productInfo.getAlternateImages())
//        .shortName(productInfo.getShortName())
//        .endDate(productInfo.getEndDate())
//        .enabledShiptoStore(productInfo.getEnabledShiptoStore())
//        .dynamicRelatedProducts(productInfo.getDynamicRelatedProducts())
//        .displayName(productInfo.getDisplayName())
//        .dynamicUpsellProducts(productInfo.getDynamicUpsellProducts())
//        .activeSku(productInfo.getActiveSku())
//        .relatedProductsRules(productInfo.getRelatedProductsRules())
//        .price(productInfo.getPrice())
//        .creationDate(productInfo.getCreationDate())
//        .startDate(productInfo.getStartDate())
//        .parentCategories(productInfo.getParentCategories())
//        .upsellProductsRules(productInfo.getUpsellProductsRules())
//        .lotSelectionType(productInfo.getLotSelectionType())
//        .manufacturer(productInfo.getManufacturer())
//        .unusualDemandQuantity(productInfo.getUnusualDemandQuantity())
//        .primaryImage(productInfo.getPrimaryImage())
//        .countryOfOrigin(productInfo.getCountryOfOrigin())
//        .programType(productInfo.getProgramType())
//        .width(productInfo.getWidth())
//        .brand(productInfo.getBrand())
//        .isMailableItem(productInfo.getIsMailableItem())
//        .lotType(productInfo.getLotType())
//        .weightUnit(productInfo.getWeightUnit())
//        .sabrixCommodityCode(productInfo.getSabrixCommodityCode())
//        .attributes(productInfo.getAttributes())
//        .wareHouseClass(productInfo.getWareHouseClass())
//        .length(productInfo.getLength())
//        .weight(productInfo.getWeight())
//        .sizeUnit(productInfo.getSizeUnit())
//        .factoryShipVendor(productInfo.getFactoryShipVendor())
//        .nonContinentalTransportationPrePaid(productInfo.getNonContinentalTransportationPrePaid())
//        .height(productInfo.getHeight())
//        .subDivision(productInfo.getSubDivision())
//        .sizeRange(productInfo.getSizeRange())
//        .channelAvailability(productInfo.getChannelAvailability())
//        .hideDisplay(productInfo.getHideDisplay())
//        .status(productInfo.getStatus())
//        .recycleFeeIndicator(productInfo.getRecycleFeeIndicator())
//        .internationalShippable(productInfo.getInternationalShippable())
//        .specialIndicator(productInfo.getSpecialIndicator())
//        .isTruckItem(productInfo.getIsTruckItem())
//        .division(productInfo.getDivision())
//        .build();
    }

    public ProductInfo convertProductModeltoInfo(ProductModel productModel) {
        return dozerBeanMapper.map(productModel, ProductInfo.class);

//                ProductInfo.builder()
//                .id(productModel.getId())
//                .daysAvailable(productModel.getDaysAvailable())
//                .childSKUs(productModel.getChildSKUs())
//                .description(productModel.getDescription())
//                .alternateImages(productModel.getAlternateImages())
//                .shortName(productModel.getShortName())
//                .endDate(productModel.getEndDate())
//                .enabledShiptoStore(productModel.getEnabledShiptoStore())
//                .dynamicRelatedProducts(productModel.getDynamicRelatedProducts())
//                .displayName(productModel.getDisplayName())
//                .dynamicUpsellProducts(productModel.getDynamicUpsellProducts())
//                .activeSku(productModel.getActiveSku())
//                .relatedProductsRules(productModel.getRelatedProductsRules())
//                .price(productModel.getPrice())
//                .creationDate(productModel.getCreationDate())
//                .startDate(productModel.getStartDate())
//                .parentCategories(productModel.getParentCategories())
//                .upsellProductsRules(productModel.getUpsellProductsRules())
//                .lotSelectionType(productModel.getLotSelectionType())
//                .manufacturer(productModel.getManufacturer())
//                .unusualDemandQuantity(productModel.getUnusualDemandQuantity())
//                .primaryImage(productModel.getPrimaryImage())
//                .countryOfOrigin(productModel.getCountryOfOrigin())
//                .programType(productModel.getProgramType())
//                .width(productModel.getWidth())
//                .brand(productModel.getBrand())
//                .isMailableItem(productModel.getIsMailableItem())
//                .lotType(productModel.getLotType())
//                .weightUnit(productModel.getWeightUnit())
//                .sabrixCommodityCode(productModel.getSabrixCommodityCode())
//                .attributes(productModel.getAttributes())
//                .wareHouseClass(productModel.getWareHouseClass())
//                .length(productModel.getLength())
//                .weight(productModel.getWeight())
//                .sizeUnit(productModel.getSizeUnit())
//                .factoryShipVendor(productModel.getFactoryShipVendor())
//                .nonContinentalTransportationPrePaid(productModel.getNonContinentalTransportationPrePaid())
//                .height(productModel.getHeight())
//                .subDivision(productModel.getSubDivision())
//                .sizeRange(productModel.getSizeRange())
//                .channelAvailability(productModel.getChannelAvailability())
//                .hideDisplay(productModel.getHideDisplay())
//                .status(productModel.getStatus())
//                .recycleFeeIndicator(productModel.getRecycleFeeIndicator())
//                .internationalShippable(productModel.getInternationalShippable())
//                .specialIndicator(productModel.getSpecialIndicator())
//                .isTruckItem(productModel.getIsTruckItem())
//                .division(productModel.getDivision())
//                .build();
    }
}
