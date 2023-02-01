package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.transaction_category.TransactionCategoryFacade;
import com.eukon05.financetracker.transaction_category.exception.TransactionCategoryNotFoundException;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.WalletFacade;
import com.eukon05.financetracker.wallet.exception.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class TransactionFacadeImpl implements TransactionFacade {
    private final TransactionService transactionService;
    private final WalletFacade walletFacade;
    private final TransactionCategoryFacade categoryFacade;

    private Transaction getTransaction(String userId, long transactionId) {
        return transactionService.getTransaction(userId, transactionId);
    }

    @Transactional
    public void createTransaction(String userId, CreateTransactionDTO dto) {
        Wallet wallet = walletFacade.getWallet(userId, dto.walletID());
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());

        transactionService.createTransaction(wallet, category, dto);
    }

    public void deleteTransaction(String userId, long transactionID) {
        Transaction transaction = getTransaction(userId, transactionID);
        transactionService.deleteTransaction(transaction);
    }

    @Transactional
    public void editTransaction(String userId, long transactionID, EditTransactionDTO dto) {
        Transaction transaction = getTransaction(userId, transactionID);
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());
        transactionService.editTransaction(transaction, category, dto);
    }

    public TransactionDTO getTransactionDTO(String userId, long transactionID) {
        return transactionService.getTransactionDTO(userId, transactionID);
    }

    public Page<TransactionDTO> getPagedTransactionDTOsForWallet(String userId, long walletID, Map<String, String> params, Pageable pageable) {
        checkUserOwnsWallet(userId, walletID);

        if (params.containsKey(TransactionSpecification.CATEGORY)) {
            checkCategoryExists(Long.parseLong(params.get(TransactionSpecification.CATEGORY)));
        }

        return transactionService.getPagedTransactionDTOsForSpecification(TransactionSpecification.build(walletID, params), pageable);
    }

    public List<Transaction> getTransactionsForSpecification(String userId, long walletID, Map<String, String> params) {
        checkUserOwnsWallet(userId, walletID);

        if (params.containsKey(TransactionSpecification.CATEGORY)) {
            checkCategoryExists(Long.parseLong(params.get(TransactionSpecification.CATEGORY)));
        }

        return transactionService.getTransactionsForSpecification(TransactionSpecification.build(walletID, params));
    }

    private void checkUserOwnsWallet(String userId, long walletID) {
        if (!walletFacade.checkOwnership(userId, walletID)) {
            throw new WalletNotFoundException(walletID);
        }
    }

    private void checkCategoryExists(long categoryID) {
        if (!categoryFacade.exists(categoryID)) {
            throw new TransactionCategoryNotFoundException(categoryID);
        }
    }
}
