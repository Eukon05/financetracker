package com.eukon05.financetracker.transaction.mapper;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionModelMapper {

    Transaction mapCreateTransactionDTOtoTransaction(CreateTransactionDTO dto);

}
