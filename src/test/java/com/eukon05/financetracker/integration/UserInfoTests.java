package com.eukon05.financetracker.integration;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserInfoTests extends AbstractIntegrationTest {

    @Test
    void should_get_user_info() throws Exception {
        String token = utils.getTestAccessToken();

        mockMvc.perform(get("/users/me").header(AUTHORIZATION, token))
                .andExpectAll(status().isOk(),
                        jsonPath("$.username").value(utils.getLoginDTO().username()),
                        jsonPath("$.balance").value(BigDecimal.ZERO));
    }

}
