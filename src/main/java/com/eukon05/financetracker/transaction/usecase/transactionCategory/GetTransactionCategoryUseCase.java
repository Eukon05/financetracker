package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;

interface GetTransactionCategoryUseCase {

    TransactionCategory getTransactionCategory(long id);

}
