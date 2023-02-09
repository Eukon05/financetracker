package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionFacade;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletStatisticFacade {
    private final TransactionFacade transactionFacade;
    private final WalletStatisticService service;

    public List<WalletStatisticDTO> getWalletStatistics(String userId, long walletID, Map<String, String> params) {
        Map<TransactionCategory, List<Transaction>> transactions = transactionFacade.getTransactionsForSpecification(userId, walletID, params)
                .stream()
                .collect(Collectors.groupingBy(Transaction::getCategory));

        return service.generateWalletStatistics(transactions);
    }
}
