package com.humaorie.noxml;

import com.humaorie.noxml.account.AccountConfig;
import com.humaorie.noxml.person.PersonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("development")
@Import(value = {AccountConfig.class, PersonConfig.class})
public class CoreConfiguration {
}
