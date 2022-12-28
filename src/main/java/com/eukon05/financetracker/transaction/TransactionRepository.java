package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> getTransactionsByWallet(Wallet wallet, Pageable pageable);
}
