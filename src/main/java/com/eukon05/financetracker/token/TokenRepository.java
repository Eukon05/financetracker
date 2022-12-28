package com.eukon05.financetracker.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface TokenRepository extends JpaRepository<Token, String> {

    List<Token> findTokensByExpiresAtBefore(Instant instant);

}
