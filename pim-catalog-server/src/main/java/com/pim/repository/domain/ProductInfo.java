package com.pim.repository.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pkulkar4 on 7/23/18.
 */

@Table(value = "product_info")
@Data
@Setter
@Getter
public class ProductInfo {

    @PrimaryKeyColumn(
            name = "productid",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column(value = "daysAvailable")
    private Long daysAvailable;

    @Column(value = "childSKUs")
    private List<String> childSKUs;

    @Column(value = "description")
    private String description;

    @Column(value = "alternateImages")
    private List<String> alternateImages;

    @Column(value = "shortName")
    private String shortName;

    @Column(value = "endDate")
    private Date endDate;

    @Column(value = "enabledShiptoStore")
    private Boolean enabledShiptoStore;

    @Column(value = "dynamicRelatedProducts")
    private List<String> dynamicRelatedProducts;

    @Column(value = "displayName")
    private String displayName;

    @Column(value = "dynamicUpsellProducts")
    private List<String> dynamicUpsellProducts;

    @Column(value = "activeSku")
    private List<String> activeSku;

    @Column(value = "relatedProductsRules")
    private List<String> relatedProductsRules;

    @Column(value = "price")
    private Map<String, Double> price;

    @Column(value = "creationDate")
    private Date creationDate;

    @Column(value = "startDate")
    private Date startDate;

    @Column(value = "parentCategories")
    private String parentCategories;

    @Column(value = "upsellProductsRules")
    private List<String> upsellProductsRules;

    @Column(value = "lotSelectionType")
    private String lotSelectionType;

    @Column(value = "manufacturer")
    private String manufacturer;

    @Column(value = "unusualDemandQuantity")
    private Long unusualDemandQuantity;

    @Column(value = "primaryImage")
    private String primaryImage;

    @Column(value = "countryOfOrigin")
    private String countryOfOrigin;

    @Column(value = "programType")
    private String programType;

    @Column(value = "width")
    private Double width;

    @Column(value = "brand")
    private String brand;

    @Column(value = "isMailableItem")
    private Boolean isMailableItem;

    @Column(value = "lotType")
    private String lotType;

    @Column(value = "weightUnit")
    private String weightUnit;

    @Column(value = "sabrixCommodityCode")
    private String sabrixCommodityCode;

    @Column(value = "attributes")
    private Map<String, String> attributes;

    @Column(value = "wareHouseClass")
    private String wareHouseClass;

    @Column(value = "length")
    private Double length;

    @Column(value = "weight")
    private Double weight;

    @Column(value = "sizeUnit")
    private String sizeUnit;

    @Column(value = "factoryShipVendor")
    private String factoryShipVendor;

    @Column(value = "nonContinentalTransportationPrePaid")
    private String nonContinentalTransportationPrePaid;

    @Column(value = "height")
    private Double height;

    @Column(value = "subDivision")
    private Long subDivision;

    @Column(value = "sizeRange")
    private String sizeRange;

    @Column(value = "channelAvailability")
    private List<String> channelAvailability;

    @Column(value = "hideDisplay")
    private Boolean hideDisplay;

    @Column(value = "status")
    private Boolean status;

    @Column(value = "recycleFeeIndicator")
    private Boolean recycleFeeIndicator;

    @Column(value = "internationalShippable")
    private Boolean internationalShippable;

    @Column(value = "specialIndicator")
    private Boolean specialIndicator;

    @Column(value = "isTruckItem")
    private Boolean isTruckItem;

    @Column(value = "division")
    private Double division;

}
