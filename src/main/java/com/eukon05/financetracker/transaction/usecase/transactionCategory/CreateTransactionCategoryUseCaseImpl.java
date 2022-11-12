package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.exceptions.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryAlreadyExistsException;
import com.eukon05.financetracker.transaction.mapper.TransactionCategoryModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateTransactionCategoryUseCaseImpl implements CreateTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;
    private final TransactionCategoryModelMapper mapper;

    @Override
    public void createTransactionCategory(CreateTransactionCategoryDTO dto) {
        if (repository.existsByNameAndType(dto.name(), dto.type())) {
            throw new TransactionCategoryAlreadyExistsException(dto.name());
        }
        if (dto.type().equals(TransactionCategoryType.DEFAULT)) {
            throw new DefaultTransactionCategoryModificationException();
        }
        repository.save(mapper.mapCreateTransactionCategoryDTOToTransactionCategory(dto));
    }

}
