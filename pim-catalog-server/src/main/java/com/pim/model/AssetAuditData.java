package com.pim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by pkulkar4 on 8/11/18.
 */
@Data
@Builder
public class AssetAuditData {

    @JsonProperty(value = "Id")
    private String Id;

    @JsonProperty(value = "type")
    private String type;

    @JsonIgnore
    private Date updatedTime;

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "changedProperties")
    private List<String> changedProperties;

    @JsonProperty(value = "actionPerformed")
    private String actionPerformed;


}
