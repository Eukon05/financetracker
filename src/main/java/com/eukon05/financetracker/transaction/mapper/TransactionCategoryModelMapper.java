package com.eukon05.financetracker.transaction.mapper;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionCategoryModelMapper {

    TransactionCategory mapCreateTransactionCategoryDTOToTransactionCategory(CreateTransactionCategoryDTO dto);

    TransactionCategoryDTO mapTransactionCategoryToTransactionCategoryDTO(TransactionCategory category);

}
