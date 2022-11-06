package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;

interface EditTransactionCategoryUseCase {

    void editTransactionCategory(long id, EditTransactionCategoryDTO dto);

}
