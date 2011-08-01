package com.humaorie.noxml.person;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernatePersonRepository implements PersonRepository {

    private final HibernateTemplate hibernateTemplate;

    public HibernatePersonRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(Person person) {
        hibernateTemplate.merge(person);
    }
}
