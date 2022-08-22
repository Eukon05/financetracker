package com.eukon05.financetracker.repository;

import com.eukon05.financetracker.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
}
