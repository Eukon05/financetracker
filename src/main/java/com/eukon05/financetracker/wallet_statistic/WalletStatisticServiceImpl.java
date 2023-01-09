package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.transaction_category.TransactionCategoryService;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletStatisticServiceImpl implements WalletStatisticService {

    private final WalletService walletService;
    private final TransactionCategoryService categoryService;
    private final WalletStatisticRepository repository;

    public List<WalletStatistic> getWalletStatisticsById(String userId, long walletID, Long categoryID) {
        Wallet wallet = walletService.getWalletById(userId, walletID);

        if (categoryID == null) {
            return repository.getWalletStatistics(wallet);
        }

        TransactionCategory category = categoryService.getTransactionCategory(categoryID);
        Optional<WalletStatistic> projection = repository.getWalletStatisticsForCategory(wallet, category);
        List<WalletStatistic> list = Collections.emptyList();

        if (projection.isPresent())
            list = List.of(projection.get());

        return list;
    }

}
