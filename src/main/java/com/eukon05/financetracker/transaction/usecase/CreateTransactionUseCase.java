package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;

interface CreateTransactionUseCase {

    void createTransaction(String username, long walletID, CreateTransactionDTO dto);

}
