package com.eukon05.financetracker.service.security.usecase.refresh;

import java.util.Map;

public interface RefreshUseCase {
    Map<String, String> refresh(String auth, String issuer);
}
