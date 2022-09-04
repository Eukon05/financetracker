package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.TransactionDTO;

interface GetTransactionDTOByIdUseCase {

    TransactionDTO getTransactionDTOById(String username, long transactionID);

}
