package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;

public interface GetUserUseCase {

    User getUserByUsernameOrThrow(String username);

}
