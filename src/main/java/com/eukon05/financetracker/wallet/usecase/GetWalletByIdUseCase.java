package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.wallet.Wallet;

interface GetWalletByIdUseCase {

    Wallet getWalletById(User user, long walletID);

}
