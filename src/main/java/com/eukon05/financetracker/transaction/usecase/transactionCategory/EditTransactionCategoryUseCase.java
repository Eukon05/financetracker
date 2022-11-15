package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;

interface EditTransactionCategoryUseCase {

    void editTransactionCategory(TransactionCategory category, EditTransactionCategoryDTO dto);

}
