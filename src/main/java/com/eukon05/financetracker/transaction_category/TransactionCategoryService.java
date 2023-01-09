package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;

import java.util.List;

public interface TransactionCategoryService {

    List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type);

    void createTransactionCategory(CreateTransactionCategoryDTO dto);

    void deleteTransactionCategory(long id);

    void editTransactionCategory(long id, EditTransactionCategoryDTO dto);

    TransactionCategory getTransactionCategory(long id);

    TransactionCategoryDTO getTransactionCategoryDTO(long id);

}
