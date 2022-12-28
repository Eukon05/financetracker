package com.eukon05.financetracker.transaction.service.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionNotFoundException;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.transaction.service.transactionCategory.TransactionCategoryService;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final WalletService walletService;
    private final TransactionCategoryService categoryFacade;
    private final TransactionModelMapper mapper;
    private final TransactionRepository repository;

    @Transactional
    public void createTransaction(String userId, CreateTransactionDTO dto) {
        Wallet wallet = walletService.getWalletById(userId, dto.walletID());
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());

        Transaction transaction = mapper.mapCreateTransactionDTOtoTransaction(dto);
        transaction.setCategory(category);
        wallet.getTransactions().add(transaction);
    }

    public void deleteTransaction(String userId, long transactionID) {
        Transaction transaction = getTransactionById(userId, transactionID);
        repository.delete(transaction);
    }

    public TransactionDTO getTransactionDTOById(String userId, long transactionID) {
        return mapper.mapTransactionToTransactionDTO(getTransactionById(userId, transactionID));
    }

    @Transactional
    public void editTransaction(String userId, long transactionID, EditTransactionDTO dto) {
        Transaction transaction = getTransactionById(userId, transactionID);
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());

        Optional.ofNullable(dto.name()).ifPresent(transaction::setName);
        Optional.ofNullable(dto.value()).ifPresent(transaction::setValue);
        Optional.ofNullable(category).ifPresent(transaction::setCategory);
    }

    public Page<TransactionDTO> getPagedTransactionDTOsForWallet(String userId, long walletID, Pageable pageable) {
        Wallet wallet = walletService.getWalletById(userId, walletID);
        return repository.getTransactionsByWallet(wallet, pageable).map(mapper::mapTransactionToTransactionDTO);
    }

    private Transaction getTransactionById(String userId, long transactionId) {
        return repository.getTransactionByWallet_UserIdAndId(userId, transactionId).orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }

}
