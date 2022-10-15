package com.eukon05.financetracker.user.usecase;

import com.eukon05.financetracker.user.User;

import java.util.List;

public interface DeleteMultipleUsersUseCase {

    void deleteMultipleUsers(List<User> users);

}
