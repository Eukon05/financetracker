package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.wallet.Wallet;

interface CreateTransactionUseCase {

    void createTransaction(Wallet wallet, TransactionCategory category, CreateTransactionDTO dto);

}
