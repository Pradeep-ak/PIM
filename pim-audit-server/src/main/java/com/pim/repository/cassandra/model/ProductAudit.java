package com.pim.repository.cassandra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;

/**
 * Created by pkulkar4 on 8/11/18.
 */

@Data
@Builder
@Table(value = "product_audit")
public class ProductAudit {

    @PrimaryKeyColumn(
            name = "product_id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    @JsonProperty(value = "id")
    private String productId;

    @PrimaryKeyColumn(
            name = "update_time",
            ordinal = 1,
            type = PrimaryKeyType.CLUSTERED)
    private Date updatedTime;

    @Column(value = "user")
    private String user;

    @Column(value = "properties")
    private List<String> changedProperties;

    @Column(value = "action")
    private String actionPerformed;

    @Column(value = "type")
    private String type;

}
