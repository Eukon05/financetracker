package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;

interface EditTransactionUseCase {

    void editTransaction(String username, long transactionID, EditTransactionDTO dto);

}
