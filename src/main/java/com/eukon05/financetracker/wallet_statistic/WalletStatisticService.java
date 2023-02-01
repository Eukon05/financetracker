package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction_category.TransactionCategory;

import java.util.List;
import java.util.Map;

interface WalletStatisticService {
    List<WalletStatisticDTO> generateWalletStatistics(Map<TransactionCategory, List<Transaction>> transactionsByCategory);
}
