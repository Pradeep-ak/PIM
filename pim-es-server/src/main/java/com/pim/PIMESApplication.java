package com.pim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by pkulkar4 on 7/27/18.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class PIMESApplication {

    public static void main(String[] args) {
        SpringApplication.run(PIMESApplication.class, args);
    }
}
