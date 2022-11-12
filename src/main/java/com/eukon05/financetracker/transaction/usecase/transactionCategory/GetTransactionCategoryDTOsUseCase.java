package com.eukon05.financetracker.transaction.usecase.transactionCategory;

import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;

import java.util.List;

interface GetTransactionCategoryDTOsUseCase {

    List<TransactionCategoryDTO> getTransactionCategoryDTOs();

    List<TransactionCategoryDTO> getTransactionCategoryDTOs(TransactionCategoryType type);

}
