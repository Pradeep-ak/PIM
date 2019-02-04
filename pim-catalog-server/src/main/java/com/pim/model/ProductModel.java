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

    private String productId;
    private String name;
    private String description;
    private String channelOffering;
    private Boolean canShipToStore;
    private String division;
    private String subdivision;
    private String entity;
    private String maxQuantity;
    private String productType;
    private String itemType;
    private Boolean shipFromFactory;
    private Boolean international;
    private Boolean truckable;
    private List<String> activeskus;
    private Map<String, String> attributes;
    private String brand;
    private Boolean bopisEligibility;
    private Boolean enableMiniPDP;
    private String type;
    private String parent;
    private String category;
    private String deparment;
    private String primary;
    private List<String> alternateImg;

}
