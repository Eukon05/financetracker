package com.eukon05.financetracker.auth;

import com.eukon05.financetracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private static final String MESSAGE_USER_WITH_USERNAME_NOT_FOUND = "User %s not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE_USER_WITH_USERNAME_NOT_FOUND, username)));
    }
}
