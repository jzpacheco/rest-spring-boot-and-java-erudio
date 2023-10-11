package br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(inheritInitializers = AbstractionIntegrationTest.Initializer.class)
public class AbstractionIntegrationTest {

    public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    }
}
