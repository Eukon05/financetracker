package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import com.eukon05.financetracker.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepository repository;

    @Override
    public User getUserByUsernameOrThrow(String username) {
        return repository.getUserByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User getUserByEmailOrThrow(String email) {
        return repository.getUserByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

}
