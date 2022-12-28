package com.eukon05.financetracker.wallet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> getWalletsByUserId(String userId);

    Optional<Wallet> getWalletByUserIdAndId(String userId, long id);

    boolean existsByUserIdAndName(String userId, String name);

}
