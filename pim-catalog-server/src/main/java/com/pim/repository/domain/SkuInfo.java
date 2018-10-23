package com.pim.repository.domain;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by pkulkar4 on 7/23/18.
 */

@Table(value = "sku_info")
@Data
@Setter @Getter @Builder
public class SkuInfo {

    @PrimaryKeyColumn(
            name = "skuid",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column(value = "displayname")
    private String displayName;

    @Column(value = "description")
    private String description;

    @Column(value = "parentproducts")
    private String parentProducts;

    @Column(value = "creationdate")
    private Date creationDate;

    @Column(value = "startdate")
    private Date startDate;

    @Column(value = "enddate")
    private Date endDate;

    @Column(value = "lastupdatedtime")
    private Date lastUpatedTime;

    @Column(value = "swatchimagereference")
    private String swatchImageReference;

    @Column(value = "type")
    private String type;

    @Column(value = "skunum")
    private String skuNum;

    @Column(value = "barcode")
    private String barcode;

    @Column(value = "hidedisplay")
    private Boolean hideDisplay;

    @Column(value = "status")
    private Boolean status;

    @Column (value = "dynamicattributes")
    private Map<String,String> dynamicAttributes;

    @Column (value = "lineattributes")
    private Map<String,String> lineAttributes;

    @Column (value = "skuprice")
    private Map<String,String> skuPrice;

}
