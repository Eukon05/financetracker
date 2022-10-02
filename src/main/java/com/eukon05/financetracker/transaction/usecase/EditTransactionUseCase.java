package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;

interface EditTransactionUseCase {

    void editTransaction(Transaction transaction, EditTransactionDTO dto);

}
