package com.humaorie.noxml.person;

import com.humaorie.noxml.persistence.WithHibernateMappings;
import com.humaorie.noxml.persistence.HibernateConfig;
import com.humaorie.noxml.person.PersonConfig.Development;
import com.humaorie.noxml.person.PersonConfig.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate3.HibernateTemplate;

@Configuration
@Import({Production.class, Development.class})
public class PersonConfig {

    @Autowired
    private PersonRepository personRepository;

    @Bean
    public PersonFacade personFacade() {
        return new DefaultPersonFacade(personRepository);
    }
    
    @Configuration
    @Profile("production")
    @Import(HibernateConfig.class)
    @WithHibernateMappings(Person.class)
    public static class Production {

        @Autowired
        private HibernateTemplate hibernateTemplate;

        @Bean
        public PersonRepository personRepository() {
            return new HibernatePersonRepository(hibernateTemplate);
        }
    }

    @Configuration
    @Profile("development")
    public static class Development {

        @Bean
        public PersonRepository personRepository() {
            return new InMemoryPersonRepository();
        }
    }
}
