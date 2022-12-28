package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;

interface CreateTransactionCategoryUseCase {

    void createTransactionCategory(CreateTransactionCategoryDTO dto);

}
