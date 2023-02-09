package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.exception.TransactionNotFoundException;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
class TransactionService {
    private final TransactionModelMapper mapper;
    private final TransactionRepository repository;

    @Transactional
    public void createTransaction(Wallet wallet, TransactionCategory category, CreateTransactionDTO dto) {
        Transaction transaction = mapper.mapCreateTransactionDTOtoTransaction(dto);
        transaction.setCategory(category);
        wallet.getTransactions().add(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        repository.delete(transaction);
    }

    public TransactionDTO getTransactionDTO(String userId, long transactionID) {
        return mapper.mapTransactionToTransactionDTO(getTransaction(userId, transactionID));
    }

    @Transactional
    public void editTransaction(Transaction transaction, TransactionCategory category, EditTransactionDTO dto) {
        Optional.ofNullable(dto.name()).ifPresent(transaction::setName);
        Optional.ofNullable(dto.value()).ifPresent(transaction::setValue);
        transaction.setCategory(category);
    }

    public Page<TransactionDTO> getPagedTransactionDTOsForSpecification(Specification<Transaction> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(mapper::mapTransactionToTransactionDTO);
    }

    public Transaction getTransaction(String userId, long transactionId) {
        return repository.getTransactionByWallet_UserIdAndId(userId, transactionId).orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }

    public List<Transaction> getTransactionsForSpecification(Specification<Transaction> specification) {
        return repository.findAll(specification);
    }
}
