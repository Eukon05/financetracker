package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.user.dto.RegisterDTO;
import com.eukon05.financetracker.user.usecase.UserFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Transactional
@Import({IntegrationTestsUtils.class, IntegrationTestsConfiguration.class})
class RegistrationTests {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Validator validator;

    @Autowired
    private IntegrationTestsUtils utils;

    @Autowired
    private UserFacade facade;

    @Test
    @DirtiesContext
    void should_register_user() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(objectMapper.writeValueAsString(utils.getRegisterDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertTrue(facade.checkUserExistsByUsername(utils.getRegisterDTO().username()));
    }

    @Test
    @DirtiesContext
    void should_validate_user_already_exists() throws Exception {
        utils.registerTestUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(objectMapper.writeValueAsString(utils.getRegisterDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_validate_password_mismatch() {
        RegisterDTO dto = new RegisterDTO("test", "test1234", "invalid123", "email@email.com");
        assertEquals(1, validator.validate(dto).size());
    }

    @Test
    void should_validate_email_invalid() {
        RegisterDTO dto = new RegisterDTO("test", "test1234", "test1234", "email");
        assertEquals(1, validator.validate(dto).size());
    }

    @Test
    void should_validate_password_length() {
        RegisterDTO dto = new RegisterDTO("test", "h", "h", "email@email.com");
        assertEquals(2, validator.validate(dto).size());
    }

    @Test
    void should_validate_blank_fields() {
        RegisterDTO dto = new RegisterDTO(" ", " ", " ", " ");
        assertEquals(6, validator.validate(dto).size());
    }

}
