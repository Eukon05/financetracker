package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WalletStatisticRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT t.category.id AS categoryID, SUM(t.value) AS sum FROM Transaction t WHERE t.wallet = :wallet GROUP BY t.category")
    List<WalletStatistic> getWalletStatistics(@Param("wallet") Wallet wallet);

    @Query("SELECT t.category.id AS categoryID, SUM(t.value) AS sum FROM Transaction t WHERE t.wallet = :wallet AND t.category = :category GROUP BY t.category")
    Optional<WalletStatistic> getWalletStatisticsForCategory(@Param("wallet") Wallet wallet, @Param("category") TransactionCategory category);

}
