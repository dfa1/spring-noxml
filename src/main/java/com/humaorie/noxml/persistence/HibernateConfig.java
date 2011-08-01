package com.humaorie.noxml.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import net.sf.cglib.proxy.Enhancer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@org.springframework.context.annotation.Configuration
@Profile("production")
@EnableTransactionManagement
public class HibernateConfig implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(HibernateConfig.class);
    private ApplicationContext applicationContext;

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        final ClassPathResource classPathResource = new ClassPathResource("/com/humaorie/noxml/persistence.properties");
        final Properties properties = new Properties();
        properties.load(classPathResource.getInputStream());
        final Configuration configuration = new Configuration();
        final Map<String, Object> beansWithHibernateMappings = applicationContext.getBeansWithAnnotation(WithHibernateMappings.class);

        for (Object o : beansWithHibernateMappings.values()) {
            WithHibernateMappings mappings = o.getClass().getAnnotation(WithHibernateMappings.class);

            // HACK: cglib does not copy annotations in the enhanced class
            if (Enhancer.isEnhanced(o.getClass())) {
                mappings = o.getClass().getSuperclass().getAnnotation(WithHibernateMappings.class);
            }

            for (Class entityClass : mappings.value()) {
                configuration.addAnnotatedClass(entityClass);
                logger.debug("adding entity " + entityClass);
            }
        }

        return configuration. //
                mergeProperties(properties).
                buildSessionFactory();
    }

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

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}