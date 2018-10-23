package com.pim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by pkulkar4 on 8/1/18.
 */
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class SkuModel {

    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "displayName")
    private String displayName;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "parentProducts")
    private String parentProducts;
    @JsonProperty(value = "creationDate")
    private Date creationDate;
    @JsonProperty(value = "startDate")
    private Date startDate;
    @JsonProperty(value = "endDate")
    private Date endDate;
    @JsonProperty(value = "lastUpatedTime")
    private Date lastUpatedTime;
    @JsonProperty(value = "swatchImageReference")
    private String swatchImageReference;
    @JsonProperty(value = "type")
    private String type;
    @JsonProperty(value = "skuNum")
    private String skuNum;
    @JsonProperty(value = "barcode")
    private String barcode;
    @JsonProperty(value = "hideDisplay")
    private Boolean hideDisplay;
    @JsonProperty(value = "status")
    private Boolean status;
    @JsonProperty(value = "dynamicAttributes")
    private Map<String,String> dynamicAttributes;
    @JsonProperty(value = "lineAttributes")
    private Map<String,String> lineAttributes;
    @JsonProperty(value = "skuPrice")
    private Map<String,String> skuPrice;
}
