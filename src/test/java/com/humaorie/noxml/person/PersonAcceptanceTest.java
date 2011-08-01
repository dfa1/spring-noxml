package com.humaorie.noxml.person;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersonConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("production")
public class PersonAcceptanceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonFacade personFacade;

    @Test
    public void hibernatePersonRepositoryIsInjected() {
        Assert.assertEquals(true, personRepository instanceof HibernatePersonRepository);
    }

    @Test
    public void canSaveAPerson() {
        final NewPersonDto newPersonDto = new NewPersonDto();
        newPersonDto.setName("mario");
        final PersonDto personDto = personFacade.createPerson(newPersonDto);

        Assert.assertEquals("mario", personDto.getName());
    }
}
