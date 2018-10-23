package com.pim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pkulkar4 on 9/19/18.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "daysAvailable")
    private Long daysAvailable;

    @JsonProperty(value = "childSKUs")
    private List<String> childSKUs;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "alternateImages")
    private List<String> alternateImages;

    @JsonProperty(value = "shortName")
    private String shortName;

    @JsonProperty(value = "endDate")
    private Date endDate;

    @JsonProperty(value = "enabledShiptoStore")
    private Boolean enabledShiptoStore;

    @JsonProperty(value = "dynamicRelatedProducts")
    private List<String> dynamicRelatedProducts;

    @JsonProperty(value = "displayName")
    private String displayName;

    @JsonProperty(value = "dynamicUpsellProducts")
    private List<String> dynamicUpsellProducts;

    @JsonProperty(value = "activeSku")
    private List<String> activeSku;

    @JsonProperty(value = "relatedProductsRules")
    private List<String> relatedProductsRules;

    @JsonProperty(value = "price")
    private Map<String, Double> price;

    @JsonProperty(value = "creationDate")
    private Date creationDate;

    @JsonProperty(value = "startDate")
    private Date startDate;

    @JsonProperty(value = "parentCategories")
    private String parentCategories;

    @JsonProperty(value = "upsellProductsRules")
    private List<String> upsellProductsRules;

    @JsonProperty(value = "lotSelectionType")
    private String lotSelectionType;

    @JsonProperty(value = "manufacturer")
    private String manufacturer;

    @JsonProperty(value = "unusualDemandQuantity")
    private Long unusualDemandQuantity;

    @JsonProperty(value = "primaryImage")
    private String primaryImage;

    @JsonProperty(value = "countryOfOrigin")
    private String countryOfOrigin;

    @JsonProperty(value = "programType")
    private String programType;

    @JsonProperty(value = "width")
    private Double width;

    @JsonProperty(value = "brand")
    private String brand;

    @JsonProperty(value = "isMailableItem")
    private Boolean isMailableItem;

    @JsonProperty(value = "lotType")
    private String lotType;

    @JsonProperty(value = "weightUnit")
    private String weightUnit;

    @JsonProperty(value = "sabrixCommodityCode")
    private String sabrixCommodityCode;

    @JsonProperty(value = "attributes")
    private Map<String, String> attributes;

    @JsonProperty(value = "wareHouseClass")
    private String wareHouseClass;

    @JsonProperty(value = "length")
    private Double length;

    @JsonProperty(value = "weight")
    private Double weight;

    @JsonProperty(value = "sizeUnit")
    private String sizeUnit;

    @JsonProperty(value = "factoryShipVendor")
    private String factoryShipVendor;

    @JsonProperty(value = "nonContinentalTransportationPrePaid")
    private String nonContinentalTransportationPrePaid;

    @JsonProperty(value = "height")
    private Double height;

    @JsonProperty(value = "subDivision")
    private Long subDivision;

    @JsonProperty(value = "sizeRange")
    private String sizeRange;

    @JsonProperty(value = "channelAvailability")
    private List<String> channelAvailability;

    @JsonProperty(value = "hideDisplay")
    private Boolean hideDisplay;

    @JsonProperty(value = "status")
    private Boolean status;

    @JsonProperty(value = "recycleFeeIndicator")
    private Boolean recycleFeeIndicator;

    @JsonProperty(value = "internationalShippable")
    private Boolean internationalShippable;

    @JsonProperty(value = "specialIndicator")
    private Boolean specialIndicator;

    @JsonProperty(value = "isTruckItem")
    private Boolean isTruckItem;

    @JsonProperty(value = "division")
    private Double division;
}
