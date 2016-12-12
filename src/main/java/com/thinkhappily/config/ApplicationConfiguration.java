package com.thinkhappily.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfiguration
{
    /**
     * Reconfigure custom PropertySourcesPlaceholder to avoid conflicts with configuration in
     * /META-INF/spring/batch/bootstrap/manager/env-context.xml (spring-batch-admin-manager-1.3.1.RELEASE.jar)
     *
     * @return The PropertySourcesPlaceholderConfigurer.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {

        Resource[] resources = {
                new ClassPathResource("org/springframework/batch/admin/bootstrap/batch.properties"),
                new ClassPathResource("batch-mysql.properties"),
                new ClassPathResource("configuration.properties")};

        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
        bean.setLocations(resources);
        bean.setIgnoreResourceNotFound(true);
        bean.setIgnoreUnresolvablePlaceholders(false);
        bean.setOrder(1);

        return bean;
    }
}