package com.humaorie.noxml.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class UnusedPropertiesConfig {

    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;
    @Value("${hibernate.connection.driver_class}")
    private String driver;
    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);
        ppc.setIgnoreUnresolvablePlaceholders(false);
        ppc.setSearchSystemEnvironment(true);
        ppc.setLocalOverride(true);
        final ClassPathResource classPathResource = new ClassPathResource("/net/emaze/upk/persistence.properties");
        System.out.println(classPathResource.exists());
        ppc.setLocation(classPathResource);
        return ppc;
    }
}
