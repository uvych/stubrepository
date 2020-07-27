package com.vtb.test.dataconfig;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class  DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:tasks");
        dataSourceBuilder.username("${spring.datasource.username}");
        dataSourceBuilder.password("${spring.datasource.password}");
        return dataSourceBuilder.build();
    }
}
