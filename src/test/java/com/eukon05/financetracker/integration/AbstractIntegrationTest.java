package com.eukon05.financetracker.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
@Sql(statements = "TRUNCATE TABLE app_user RESTART IDENTITY CASCADE;", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
abstract class AbstractIntegrationTest {

    @Autowired
    protected IntegrationTestsUtils utils;

    @Autowired
    protected MockMvc mockMvc;

    protected final ObjectMapper objectMapper = new ObjectMapper();

    @Container
    private static final PostgreSQLContainer<?> databaseContainer = new PostgreSQLContainer<>("postgres")
            .withReuse(true);

    static {
        databaseContainer.start();
    }

    @DynamicPropertySource
    private static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", databaseContainer::getJdbcUrl);
        registry.add("spring.datasource.username", databaseContainer::getUsername);
        registry.add("spring.datasource.password", databaseContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        utils.registerTestUser();
    }

}
