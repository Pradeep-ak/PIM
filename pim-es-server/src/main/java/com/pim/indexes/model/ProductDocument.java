package com.pim.indexes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pkulkar4 on 9/12/18.
 */

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Document(indexName = "catalog", type = "product", shards = 1, replicas = 0, refreshInterval = "-1")
public class ProductDocument {

    @Id
    private String id;

    private Long daysAvailable;

    @Field(type = FieldType.Object, store = true)
    private List<String> childSKUs;

    @Field(type = FieldType.Text, store = true, index = false)
    private String description;

    @Field(type = FieldType.Object, store = true)
    private List<String> alternateImages;

    @Field(type = FieldType.Text, store = true)
    private String shortName;

    @Field(type = FieldType.Date, store = true)
    private Date endDate;

    private Boolean enabledShiptoStore;

    private List<String> dynamicRelatedProducts;

    @Field(type = FieldType.Text, store = true, index = true)
    private String displayName;


    private List<String> dynamicUpsellProducts;

    @Field(type = FieldType.Object, store = true, index = true)
    private List<String> activeSku;

    private List<String> relatedProductsRules;

    @Field(type = FieldType.Object, store = true, index = true)
    private Map<String, Double> price;

    @Field(type = FieldType.Date, store = true, index = true)
    private Date creationDate;

    @Field(type = FieldType.Date, store = true, index = true)
    private Date startDate;

    @Field(type = FieldType.Text, store = true, index = true)
    private String parentCategories;

    private List<String> upsellProductsRules;

    private String lotSelectionType;

    @Field(type = FieldType.Text, store = false, index = true)
    private String manufacturer;

    private Long unusualDemandQuantity;

    @Field(type = FieldType.Text, store = true, index = true)
    private String primaryImage;

    private String countryOfOrigin;

    private String programType;

    private Double width;

    @Field(type = FieldType.Text, store = true, index = true)
    private String brand;

    private Boolean isMailableItem;

    private String lotType;

    private String weightUnit;

    private String sabrixCommodityCode;

    @Field(type = FieldType.Object, store = false, index = true)
    private Map<String, String> attributes;

    private String wareHouseClass;

    private Double length;

    private Double weight;

    private String sizeUnit;

    @Field(type = FieldType.Text, store = false, index = true)
    private String factoryShipVendor;

    private String nonContinentalTransportationPrePaid;

    private Double height;

    @Field(type = FieldType.Long, store = false, index = true)
    private Long subDivision;

    @Field(type = FieldType.Text, store = false, index = true)
    private String sizeRange;

    @Field(type = FieldType.Object, index = true)
    private List<String> channelAvailability;

    @Field(type = FieldType.Boolean, store = false, index = true)
    private Boolean hideDisplay;

    @Field(type = FieldType.Boolean, index = true)
    private Boolean status;

    private Boolean recycleFeeIndicator;

    @Field(type = FieldType.Boolean, store = false, index = true)
    private Boolean internationalShippable;

    private Boolean specialIndicator;

    private Boolean isTruckItem;

    @Field(type = FieldType.Double, store = false, index = true)
    private Double division;

}
