package com.eukon05.financetracker.transaction.usecase;

interface DeleteTransactionUseCase {

    void deleteTransaction(String username, long transactionID);

}
