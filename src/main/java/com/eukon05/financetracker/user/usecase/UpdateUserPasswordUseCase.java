package com.eukon05.financetracker.user.usecase;

interface UpdateUserPasswordUseCase {

    void updateUserPassword(String username, String newPassword, String rootUrl);

}
