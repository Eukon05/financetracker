package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionNotFoundException;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetTransactionDTOByIdUseCaseImpl implements GetTransactionDTOByIdUseCase {

    private final TransactionRepository repository;
    private final UserFacade userFacade;
    private final TransactionModelMapper mapper;

    @Override
    public TransactionDTO getTransactionDTOById(String username, long transactionID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Transaction transaction = repository.findById(transactionID).orElseThrow(() -> new TransactionNotFoundException(transactionID));

        if (!transaction.getWallet().getUser().equals(user)) {
            throw new TransactionNotFoundException(transactionID);
        }

        return mapper.mapTransactionToTransactionDTO(transaction);
    }

}
