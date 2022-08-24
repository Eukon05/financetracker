package com.eukon05.financetracker.token;

import com.eukon05.financetracker.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@Entity
@Table(name = "token")
@NoArgsConstructor
public class Token {

    @Id
    private final String id = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    private Instant expiresAt = Instant.now().plus(30, ChronoUnit.MINUTES);

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
    }

}
