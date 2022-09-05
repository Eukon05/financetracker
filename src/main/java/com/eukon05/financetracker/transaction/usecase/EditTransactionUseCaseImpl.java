package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionNotFoundException;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EditTransactionUseCaseImpl implements EditTransactionUseCase {

    private final UserFacade userFacade;
    private final TransactionRepository repository;

    @Override
    @Transactional
    public void editTransaction(String username, long transactionID, EditTransactionDTO dto) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Transaction transaction = repository.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException(transactionID));

        if (!transaction.getWallet().getUser().equals(user)) {
            throw new TransactionNotFoundException(transactionID);
        }

        Optional.ofNullable(dto.name()).ifPresent(transaction::setName);
        Optional.ofNullable(dto.value()).ifPresent(transaction::setValue);
        Optional.ofNullable(dto.type()).ifPresent(transaction::setType);
    }
}
