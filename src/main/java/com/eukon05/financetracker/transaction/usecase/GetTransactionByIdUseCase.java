package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;

interface GetTransactionByIdUseCase {

    Transaction getTransactionById(String username, long transactionID);

}
