package com.eukon05.financetracker.transaction.service.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.transaction.exceptions.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryAlreadyExistsException;
import com.eukon05.financetracker.transaction.exceptions.TransactionCategoryNotFoundException;
import com.eukon05.financetracker.transaction.mapper.TransactionCategoryModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionCategoryService {

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

    public void deleteTransactionCategory(long id) {
        TransactionCategory category = getTransactionCategory(id);

        if (category.getType().equals(TransactionCategoryType.DEFAULT)) {
            throw new DefaultTransactionCategoryModificationException();
        }

        //We can skip checking the optional's content, because the default category always exists
        TransactionCategory defaultCategory = repository.findByType(TransactionCategoryType.DEFAULT).get();
        category.getTransactions().forEach(t -> t.setCategory(defaultCategory));

        repository.delete(category);
    }

    @Transactional
    public void editTransactionCategory(long id, EditTransactionCategoryDTO dto) {
        TransactionCategory category = getTransactionCategory(id);

        if (category.getType().equals(TransactionCategoryType.DEFAULT)) {
            throw new DefaultTransactionCategoryModificationException();
        }

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

}
