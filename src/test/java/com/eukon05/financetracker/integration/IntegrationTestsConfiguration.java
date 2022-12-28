package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class IntegrationTestsConfiguration {

    @Bean
    @Primary
    public EmailFacade emailFacadeMock() {
        return Mockito.mock(EmailFacade.class);
    }

}
