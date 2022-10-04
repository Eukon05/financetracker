package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.user.User;

interface GetTransactionByIdUseCase {

    Transaction getTransactionById(User user, long transactionID);

}