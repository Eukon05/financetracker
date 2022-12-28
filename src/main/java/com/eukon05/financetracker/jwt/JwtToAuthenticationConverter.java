package com.eukon05.financetracker.jwt;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JwtToAuthenticationConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), jwt, jwt.getClaimAsStringList("roles").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }
}
