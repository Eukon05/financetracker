package com.eukon05.financetracker.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthConstants {

    TOKEN_PREFIX("Bearer "), ACCESS_TOKEN("access_token"), REFRESH_TOKEN("refresh_token");

    private final String value;
}
