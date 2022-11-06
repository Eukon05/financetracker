package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction.mapper.TransactionCategoryModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetTransactionCategoryDTOsUseCaseImpl implements GetTransactionCategoryDTOsUseCase {

    private final TransactionCategoryRepository repository;
    private final TransactionCategoryModelMapper mapper;

    @Override
    public List<TransactionCategoryDTO> getTransactionCategoryDTOs() {
        return repository.findAll().stream().map(mapper::mapTransactionCategoryToTransactionCategoryDTO).toList();
    }

    @Override
    public List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionType type) {
        return repository.findAllByType(type).stream().map(mapper::mapTransactionCategoryToTransactionCategoryDTO).toList();
    }

}
