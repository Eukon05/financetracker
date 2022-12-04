package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.dto.RegisterDTO;

interface CreateUserUseCase {
    User createUser(RegisterDTO dto, String rootUrl);

    void createAdminUser(String masterPassword, RegisterDTO dto, String rootUrl);
}
