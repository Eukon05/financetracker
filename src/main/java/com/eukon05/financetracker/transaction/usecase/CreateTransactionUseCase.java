package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.wallet.Wallet;

interface CreateTransactionUseCase {

    void createTransaction(Wallet wallet, CreateTransactionDTO dto);

}
