package com.eukon05.financetracker.jwt;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.eukon05.financetracker.jwt.usecase.JwtFacade;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eukon05.financetracker.auth.AuthFinals.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final List<String> urls = new LinkedList<>();

    static {
        urls.add("/login");
        urls.add("/refresh");
        urls.add("/users");
        urls.add("/confirm-registration");
    }

    private final JwtFacade jwtFacade;
    private final UserFacade userFacade;

    private static final String MESSAGE_INVALID_TOKEN = "Invalid token";
    private static final String MESSAGE_MISSING_TOKEN = "Missing token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(AUTHORIZATION));
        if (tokenOptional.isEmpty()) {
            printForbidden(response, MESSAGE_MISSING_TOKEN);
            return;
        }

        String token = tokenOptional.get();
        token = token.replace(TOKEN_PREFIX, "").trim();

        DecodedJWT jwt;
        try {
            jwt = jwtFacade.validateAccessToken(token);
        } catch (JWTDecodeException ex) {
            printForbidden(response, MESSAGE_INVALID_TOKEN);
            return;
        } catch (JWTVerificationException ex) {
            printForbidden(response, ex.getMessage());
            return;
        }

        if (!userFacade.checkUserExistsByUsername(jwt.getSubject())) {
            printForbidden(response, MESSAGE_INVALID_TOKEN);
            return;
        }

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, Arrays.stream(jwt.getClaim("roles").asArray(String.class)).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        SecurityContextHolder.getContext().setAuthentication(upToken);
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return urls.contains(request.getServletPath()) || request.getMethod().equals(HttpMethod.POST.toString());
    }

    private void printForbidden(HttpServletResponse response, String message) throws IOException {
        response.getWriter().println(message);
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}
