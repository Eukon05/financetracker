package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletStatisticRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT t.category.id as categoryID, SUM(t.value) as sum FROM Transaction t WHERE t.wallet = :wallet GROUP BY categoryID")
    List<WalletStatisticProjection> getWalletStatistics(@Param("wallet") Wallet wallet);

    @Query("SELECT t.category.id as categoryID, SUM(t.value) as sum FROM Transaction t WHERE t.wallet = :wallet AND t.category.id = :categoryID GROUP BY categoryID")
    WalletStatisticProjection getWalletStatisticsForCategory(@Param("wallet") Wallet wallet, @Param("categoryID") long categoryID);

}
