package com.eukon05.financetracker.service.user.usecase.createUser;

import com.eukon05.financetracker.dto.RegisterDTO;

public interface CreateUserUseCase {
    void createUser(RegisterDTO dto, String rootUrl);
}
