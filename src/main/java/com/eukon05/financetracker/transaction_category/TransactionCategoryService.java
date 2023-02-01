package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;

import java.util.List;

interface TransactionCategoryService {

    List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type);

    void createTransactionCategory(CreateTransactionCategoryDTO dto);

    void deleteTransactionCategory(TransactionCategory category);

    void editTransactionCategory(TransactionCategory category, EditTransactionCategoryDTO dto);

    TransactionCategory getTransactionCategory(long id);

    TransactionCategoryDTO getTransactionCategoryDTO(long id);

    boolean exists(long id);

}
