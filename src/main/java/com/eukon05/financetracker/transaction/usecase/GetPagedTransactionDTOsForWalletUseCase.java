package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface GetPagedTransactionDTOsForWalletUseCase {

    Page<TransactionDTO> getPagedTransactionDTOsForWallet(String username, long walletID, Pageable pageable);

}
