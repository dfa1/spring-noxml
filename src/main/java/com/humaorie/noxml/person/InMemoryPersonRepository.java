package com.humaorie.noxml.person;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPersonRepository implements PersonRepository {

    private Map<Long, Person> repository = new HashMap<Long, Person>();

    @Override
    public void save(Person person) {
        repository.put(person.getId(), person);
    }
}
