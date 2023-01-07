package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.TransactionCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionCategoryModelMapper {

    TransactionCategory mapCreateTransactionCategoryDTOToTransactionCategory(CreateTransactionCategoryDTO dto);

    TransactionCategoryDTO mapTransactionCategoryToTransactionCategoryDTO(TransactionCategory category);

}
