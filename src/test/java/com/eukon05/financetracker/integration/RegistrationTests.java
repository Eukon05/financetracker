package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.email.usecase.EmailFacade;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.Validator;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.testUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistrationTests extends AbstractIntegrationTest {

    @Autowired
    private Validator validator;

    @Autowired
    private EmailFacade emailFacade;

    private static final RegisterDTO registerDTO = new RegisterDTO(testUser.getUsername(), "test2134", "test2134", testUser.getEmail());

    @Test
    void should_register_user() throws Exception {
        makeRegisterRequest().andExpect(status().isCreated());

        Mockito.verify(userRepository).save(Mockito.any(User.class));
        Mockito.verify(emailFacade).sendRegistrationEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void should_validate_user_already_exists() throws Exception {
        Mockito.when(userRepository.existsByUsername(registerDTO.username())).thenReturn(true);
        Mockito.when(userRepository.existsByEmail(registerDTO.username())).thenReturn(true);

        makeRegisterRequest().andExpect(status().isConflict());
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

    private ResultActions makeRegisterRequest() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .content(objectMapper.writeValueAsString(registerDTO))
                .contentType(MediaType.APPLICATION_JSON));
    }

}
