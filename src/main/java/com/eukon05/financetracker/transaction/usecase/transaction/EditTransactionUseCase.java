package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;

interface EditTransactionUseCase {

    void editTransaction(Transaction transaction, EditTransactionDTO dto, TransactionCategory category);

}
