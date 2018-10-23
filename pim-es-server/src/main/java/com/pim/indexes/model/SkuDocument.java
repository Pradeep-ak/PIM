package com.pim.indexes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by pkulkar4 on 9/12/18.
 */
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Document(indexName = "catalog", type = "sku", shards = 1, replicas = 0, refreshInterval = "-1")
public class SkuDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, store = true)
    private String displayName;

    private String description;

    @Builder.Default
    @Parent(type = "product")
    private String parentProducts="d0000";

    private Date creationDate;

    @Field(type = FieldType.Date, store = true)
    private Date startDate;

    @Field(type = FieldType.Date, store = true)
    private Date endDate;

    @Field(type = FieldType.Date, store = true)
    private Date lastUpatedTime;

    @Field(type = FieldType.Text, store = true)
    private String swatchImageReference;

    private String type;

    private String skuNum;

    @Field(type = FieldType.Text, store = false)
    private String barcode;

    @Field(type = FieldType.Boolean, store = false)
    private Boolean hideDisplay;

    @Field(type = FieldType.Boolean, store = false)
    private Boolean status;

    @Field(type = FieldType.Object, store = false)
    private Map<String,String> dynamicAttributes;

    private Map<String,String> lineAttributes;

    @Field(type = FieldType.Object, store = false)
    private Map<String,String> skuPrice;

}
