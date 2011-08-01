package com.humaorie.noxml.account;

import com.humaorie.noxml.person.PersonRepository;

public class DefaultAccountFacade implements AccountFacade {

    private final PersonRepository personRepository;

    public DefaultAccountFacade(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save() {
        personRepository.save(null);
    }
}
