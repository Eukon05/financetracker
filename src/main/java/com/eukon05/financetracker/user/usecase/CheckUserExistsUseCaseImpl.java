package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CheckUserExistsUseCaseImpl implements CheckUserExistsUseCase {

    private final UserRepository repository;

    @Override
    public boolean checkUserExistsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
