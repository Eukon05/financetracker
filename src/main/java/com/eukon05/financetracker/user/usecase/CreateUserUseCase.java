package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.dto.RegisterDTO;

interface CreateUserUseCase {
    void createUser(RegisterDTO dto, String rootUrl);
}
