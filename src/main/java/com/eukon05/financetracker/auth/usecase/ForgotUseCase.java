package com.eukon05.financetracker.auth.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.user.User;

interface ForgotUseCase {

    Token forgot(User user);

}
