package com.eukon05.financetracker.auth.usecase;

import java.util.Map;

interface RefreshUseCase {
    Map<String, String> refresh(String auth, String issuer);
}
