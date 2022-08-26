package com.eukon05.financetracker.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WalletNameTakenException extends ResponseStatusException {

    private static final String MESSAGE_WALLET_NAME_TAKEN = "You have already created a wallet with name \"%s\"";

    public WalletNameTakenException(String name) {
        super(HttpStatus.CONFLICT, String.format(MESSAGE_WALLET_NAME_TAKEN, name));
    }
}
