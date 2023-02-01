package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

interface TransactionService {

    void createTransaction(Wallet wallet, TransactionCategory category, CreateTransactionDTO dto);

    void deleteTransaction(Transaction transaction);

    TransactionDTO getTransactionDTO(String userId, long transactionID);

    void editTransaction(Transaction transaction, TransactionCategory category, EditTransactionDTO dto);

    Page<TransactionDTO> getPagedTransactionDTOsForSpecification(Specification<Transaction> specification, Pageable pageable);

    Transaction getTransaction(String userId, long transactionID);

    List<Transaction> getTransactionsForSpecification(Specification<Transaction> specification);
}
