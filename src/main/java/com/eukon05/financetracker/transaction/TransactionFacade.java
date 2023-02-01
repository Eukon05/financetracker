package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TransactionFacade {
    void createTransaction(String userId, CreateTransactionDTO dto);

    void deleteTransaction(String userId, long transactionID);

    void editTransaction(String userId, long transactionID, EditTransactionDTO dto);

    TransactionDTO getTransactionDTO(String userId, long transactionID);

    Page<TransactionDTO> getPagedTransactionDTOsForWallet(String userId, long walletID, Map<String, String> params, Pageable pageable);

    List<Transaction> getTransactionsForSpecification(String userId, long walletID, Map<String, String> params);
}
