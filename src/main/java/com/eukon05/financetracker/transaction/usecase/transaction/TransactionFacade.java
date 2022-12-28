package com.eukon05.financetracker.transaction.usecase.transaction;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.transaction.usecase.transactionCategory.TransactionCategoryFacade;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final UserFacade userFacade;
    private final WalletFacade walletFacade;
    private final TransactionCategoryFacade categoryFacade;
    private final TransactionModelMapper mapper;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final EditTransactionUseCase editTransactionUseCase;
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final GetPagedTransactionDTOsForWalletUseCase getPagedTransactionDTOsForWalletUseCase;

    public void createTransaction(String username, CreateTransactionDTO dto) {
        Wallet wallet = walletFacade.getWalletById(username, dto.walletID());
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());

        createTransactionUseCase.createTransaction(wallet, category, dto);
    }

    public void deleteTransaction(String username, long transactionID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Transaction transaction = getTransactionByIdUseCase.getTransactionById(user, transactionID);
        deleteTransactionUseCase.deleteTransaction(transaction);
    }

    public TransactionDTO getTransactionDTOById(String username, long transactionID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        return mapper.mapTransactionToTransactionDTO(getTransactionByIdUseCase.getTransactionById(user, transactionID));
    }

    public void editTransaction(String username, long transactionID, EditTransactionDTO dto) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Transaction transaction = getTransactionByIdUseCase.getTransactionById(user, transactionID);
        TransactionCategory category = categoryFacade.getTransactionCategory(dto.categoryID());

        editTransactionUseCase.editTransaction(transaction, dto, category);
    }

    public Page<TransactionDTO> getPagedTransactionDTOsForWallet(String username, long walletID, Pageable pageable) {
        Wallet wallet = walletFacade.getWalletById(username, walletID);
        return getPagedTransactionDTOsForWalletUseCase.getPagedTransactionDTOsForWallet(wallet, pageable);
    }

}
