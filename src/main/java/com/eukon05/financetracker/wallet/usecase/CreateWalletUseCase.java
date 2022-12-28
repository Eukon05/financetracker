package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;

public interface CreateWalletUseCase {

    void createWallet(User user, String name, String currency);

}
