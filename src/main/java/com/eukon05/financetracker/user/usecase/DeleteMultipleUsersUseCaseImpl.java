package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteMultipleUsersUseCaseImpl implements DeleteMultipleUsersUseCase {

    private final UserRepository repository;

    @Override
    public void deleteMultipleUsers(List<User> users) {
        repository.deleteAll(users);
    }

}
