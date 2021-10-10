package com.wob.homework.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class HomeworkBeanConfiguration {
    private final DataSource dataSource;

    @Autowired
    public HomeworkBeanConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SpringLiquibase main() {
        final SpringLiquibase main = new SpringLiquibase();
        main.setChangeLog("classpath:liquibase/changelog-master.xml");
        main.setDataSource(dataSource);
        return main;
    }
}
