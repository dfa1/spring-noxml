package com.humaorie.noxml.account;

import com.humaorie.noxml.person.PersonConfig;
import com.humaorie.noxml.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(PersonConfig.class)
public class AccountConfig {

    @Autowired
    private PersonRepository personRepository;
    
    @Bean
    public AccountFacade accountFacade() {
        return new DefaultAccountFacade(personRepository);
    }
}
