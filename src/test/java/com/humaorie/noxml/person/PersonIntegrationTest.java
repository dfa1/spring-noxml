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
@ActiveProfiles("development")
public class PersonIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void developmentDependenciesAreInjected() {
        Assert.assertTrue(personRepository instanceof InMemoryPersonRepository);
    }
}
