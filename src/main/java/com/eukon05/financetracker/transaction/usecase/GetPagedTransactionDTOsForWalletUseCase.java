package com.eukon05.financetracker.transaction.usecase;

import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface GetPagedTransactionDTOsForWalletUseCase {

    Page<TransactionDTO> getPagedTransactionDTOsForWallet(Wallet wallet, Pageable pageable);

}
