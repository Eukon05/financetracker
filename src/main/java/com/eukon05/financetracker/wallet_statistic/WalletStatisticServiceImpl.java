package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class WalletStatisticServiceImpl implements WalletStatisticService {
    public List<WalletStatisticDTO> generateWalletStatistics(Map<TransactionCategory, List<Transaction>> transactionsByCategory) {
        List<WalletStatisticDTO> result = new LinkedList<>();

        long id;
        BigDecimal sum;

        for (Map.Entry<TransactionCategory, List<Transaction>> e : transactionsByCategory.entrySet()) {
            id = e.getKey().getId();
            sum = e.getValue().stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
            result.add(new WalletStatisticDTO(id, sum));
        }

        return result;
    }
}
