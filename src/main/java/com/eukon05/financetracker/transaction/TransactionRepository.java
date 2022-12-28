package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> getTransactionByWallet_UserIdAndId(String userId, long id);

    Page<Transaction> getTransactionsByWallet(Wallet wallet, Pageable pageable);
}
