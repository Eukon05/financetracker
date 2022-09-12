package com.eukon05.financetracker.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WalletNotFoundException extends ResponseStatusException {

    private static final String MESSAGE_WALLET_WITH_ID_NOT_FOUND = "Wallet with id '%d' does not exist or does not belong to your account";

    public WalletNotFoundException(long walletID) {
        super(HttpStatus.NOT_FOUND, String.format(MESSAGE_WALLET_WITH_ID_NOT_FOUND, walletID));
    }

}
