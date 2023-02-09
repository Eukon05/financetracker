package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.exception.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction_category.exception.TransactionCategoryAlreadyExistsException;
import com.eukon05.financetracker.transaction_category.exception.TransactionCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class TransactionCategoryService {

    private final TransactionCategoryRepository repository;
    private final TransactionCategoryModelMapper mapper;

    public List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type) {
        List<TransactionCategory> categories;

        if (Optional.ofNullable(type).isEmpty()) {
            categories = repository.findAll();
        } else {
            categories = repository.findAllByType(type);
        }

        return categories.stream().map(mapper::mapTransactionCategoryToTransactionCategoryDTO).toList();
    }

    public void createTransactionCategory(CreateTransactionCategoryDTO dto) {
        if (repository.existsByNameAndType(dto.name(), dto.type())) {
            throw new TransactionCategoryAlreadyExistsException(dto.name());
        }
        if (dto.type().equals(TransactionCategoryType.DEFAULT)) {
            throw new DefaultTransactionCategoryModificationException();
        }
        repository.save(mapper.mapCreateTransactionCategoryDTOToTransactionCategory(dto));
    }

    @Transactional
    public void deleteTransactionCategory(TransactionCategory category) {
        blockDefaultCategoryModification(category);

        //We can skip checking the optional's content, because the default category always exists
        TransactionCategory defaultCategory = repository.findByType(TransactionCategoryType.DEFAULT).get();
        category.getTransactions().forEach(t -> t.setCategory(defaultCategory));

        repository.delete(category);
    }

    @Transactional
    public void editTransactionCategory(TransactionCategory category, EditTransactionCategoryDTO dto) {
        blockDefaultCategoryModification(category);

        Optional.ofNullable(dto.name()).ifPresent(name -> {
            if (repository.existsByNameAndType(name, category.getType())) {
                throw new TransactionCategoryAlreadyExistsException(name);
            }
            category.setName(name);
        });
    }

    public TransactionCategory getTransactionCategory(long id) {
        return repository.findById(id).orElseThrow(() -> new TransactionCategoryNotFoundException(id));
    }

    public TransactionCategoryDTO getTransactionCategoryDTO(long id) {
        return mapper.mapTransactionCategoryToTransactionCategoryDTO(getTransactionCategory(id));
    }

    private void blockDefaultCategoryModification(TransactionCategory category) {
        if (category.getType().equals(TransactionCategoryType.DEFAULT)) {
            throw new DefaultTransactionCategoryModificationException();
        }
    }

    public boolean exists(long id) {
        return repository.existsById(id);
    }
}
