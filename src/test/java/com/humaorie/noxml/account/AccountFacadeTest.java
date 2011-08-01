package com.humaorie.noxml.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountConfig.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("development")
public class AccountFacadeTest {

    @Autowired
    private AccountFacade accountFacade;

    @Test
    public void canInjectADevelopmentFacade() {
        Assert.assertNotNull(accountFacade);
    }
}
