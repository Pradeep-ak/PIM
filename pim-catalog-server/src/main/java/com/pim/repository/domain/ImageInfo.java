package com.pim.repository.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Created by pkulkar4 on 9/10/18.
 */

@Table(value = "image_info")
@Data
@Setter
@Getter
public class ImageInfo {

    @PrimaryKeyColumn(
            name = "id",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column(value = "desc")
    private String desc;
    @Column(value = "name")
    private String name;
    @Column(value = "url")
    private String url;
    @Column(value = "altText")
    private String altText;
}
