package com.pim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.*;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Collections;
import java.util.List;

/**
 * Created by pkulkar4 on 7/23/18.
 */

@Configuration
@EnableCassandraRepositories(basePackages = "com.pim.repository.cassandra")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${catalog.cassandra.schemaName:pimcatalog_test}")
    private String schemaName;

    @Value("${catalog.cassandra.hostName:127.0.0.1}")
    private String cassandraHost;

    @Value("${catalog.cassandra.port:9042}")
    private int cassandraPort;


    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(schemaName)
                .ifNotExists(true).withSimpleReplication()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                );
    }


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return schemaName;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        System.out.println("Cassandra host : " + cassandraHost
        + ",Port : " + cassandraPort);
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        cluster.setContactPoints(cassandraHost);
        cluster.setPort(cassandraPort);
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        return cluster;
    }

    @Bean
    public CassandraMappingContext mappingContext() throws ClassNotFoundException {
        BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();
        mappingContext.setInitialEntitySet(CassandraEntityClassScanner.scan("com.pim.repository.domain"));
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(),getKeyspaceName()));
        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() throws ClassNotFoundException {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        try {
            session.setConverter(converter());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
        return session;
    }

}
