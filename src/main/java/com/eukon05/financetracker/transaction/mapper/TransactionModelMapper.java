package com.eukon05.financetracker.transaction.mapper;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionModelMapper {

    Transaction mapCreateTransactionDTOtoTransaction(CreateTransactionDTO dto);

    @Mapping(source = "wallet.id", target = "walletID")
    @Mapping(source = "category.id", target = "categoryID")
    TransactionDTO mapTransactionToTransactionDTO(Transaction transaction);

}
