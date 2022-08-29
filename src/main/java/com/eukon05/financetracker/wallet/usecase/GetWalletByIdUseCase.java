package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;

interface GetWalletByIdUseCase {

    Wallet getWalletById(String username, long walletID);

}
