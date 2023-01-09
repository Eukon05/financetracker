package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    void createTransaction(String userId, CreateTransactionDTO dto);

    void deleteTransaction(String userId, long transactionID);

    TransactionDTO getTransactionDTOById(String userId, long transactionID);

    void editTransaction(String userId, long transactionID, EditTransactionDTO dto);

    Page<TransactionDTO> getPagedTransactionDTOsForWallet(String userId, long walletID, Pageable pageable);
}
