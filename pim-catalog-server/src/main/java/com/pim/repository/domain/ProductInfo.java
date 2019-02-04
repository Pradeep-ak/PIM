package com.pim.repository.domain;

import lombok.*;
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
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {

    @PrimaryKeyColumn(
            name = "productid",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String productId;
    @Column(value = "name")
    private String name;
    @Column(value = "description")
    private String description;
    @Column(value = "channelOffering")
    private String channelOffering;
    @Column(value = "canShipToStore")
    private Boolean canShipToStore;
    @Column(value = "division")
    private String division;
    @Column(value = "subdivision")
    private String subdivision;
    @Column(value = "entity")
    private String entity;
    @Column(value = "maxQuantity")
    private String maxQuantity;
    @Column(value = "productType")
    private String productType;
    @Column(value = "itemType")
    private String itemType;
    @Column(value = "shipFromFactory")
    private Boolean shipFromFactory;
    @Column(value = "international")
    private Boolean international;
    @Column(value = "truckable")
    private Boolean truckable;
    @Column(value = "activeskus")
    private List<String> activeskus;
    @Column(value = "attributes")
    private Map<String, String> attributes;
    @Column(value = "brand")
    private String brand;
    @Column(value = "bopisEligibility")
    private Boolean bopisEligibility;
    @Column(value = "enableMiniPDP")
    private Boolean enableMiniPDP;
    @Column(value = "type")
    private String type;
    @Column(value = "parent")
    private String parent;
    @Column(value = "category")
    private String category;
    @Column(value = "deparment")
    private String deparment;
    @Column(value = "primary")
    private String primary;
    @Column(value = "alternateImg")
    private List<String> alternateImg;

}
