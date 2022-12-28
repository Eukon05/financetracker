package com.eukon05.financetracker.token.usecase;

import com.eukon05.financetracker.token.Token;
import com.eukon05.financetracker.token.TokenType;

interface GetTokenByIdUseCase {

    Token getTokenById(String id, TokenType type);

}
