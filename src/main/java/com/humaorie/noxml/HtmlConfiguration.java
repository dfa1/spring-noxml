package com.humaorie.noxml;

import com.humaorie.noxml.CoreConfiguration;
import com.humaorie.noxml.webapp.IndexController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@Import(CoreConfiguration.class)
@ComponentScan(basePackageClasses=IndexController.class)
public class HtmlConfiguration {

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        final InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setViewClass(JstlView.class);
        irvr.setPrefix("/WEB-INF/jsp/");
        irvr.setSuffix(".jsp");
        return irvr;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
}
