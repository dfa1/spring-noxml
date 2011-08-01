package com.humaorie.noxml.person;

import org.springframework.transaction.annotation.Transactional;

public class DefaultPersonFacade implements PersonFacade {

    private final PersonRepository personRepository;

    public DefaultPersonFacade(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public PersonDto createPerson(NewPersonDto newPersonDto) {
        final Person person = new Person();
        person.setName(newPersonDto.getName());
        personRepository.save(person);
        final PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        return personDto;
    }
}
