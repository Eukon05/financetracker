package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryType;

import java.util.List;

interface GetTransactionCategoriesUseCase {

    List<TransactionCategory> getTransactionCategoryDTOs();

    List<TransactionCategory> getTransactionCategoryDTOs(TransactionCategoryType type);

}
