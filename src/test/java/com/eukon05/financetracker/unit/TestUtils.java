package com.eukon05.financetracker.unit;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.transaction_category.TransactionCategoryType;
import com.eukon05.financetracker.wallet.Wallet;

import java.math.BigDecimal;
import java.time.Instant;

final class TestUtils {

    static final String userID = "SOMEUSERID";
    static final long walletID = 1;

    static final Wallet testWallet = new Wallet();
    static final Transaction testTransaction = new Transaction();
    static final TransactionCategory testDefaultCategory = new TransactionCategory();
    static final TransactionCategory testExpenseCategory = new TransactionCategory();

    static {
        testWallet.setName("test");
        testWallet.setCurrency("EUR");

        testDefaultCategory.setId(0);
        testDefaultCategory.setType(TransactionCategoryType.DEFAULT);
        testDefaultCategory.setName(TransactionCategoryType.DEFAULT.name());

        testExpenseCategory.setId(1);
        testExpenseCategory.setType(TransactionCategoryType.EXPENSE);
        testExpenseCategory.setName(TransactionCategoryType.EXPENSE.name());

        testTransaction.setId(1);
        testTransaction.setName("TEST");
        testTransaction.setCategory(testDefaultCategory);
        testTransaction.setValue(BigDecimal.valueOf(120));
        testTransaction.setWallet(testWallet);
        testTransaction.setCreatedAt(Instant.now());
        testWallet.getTransactions().add(testTransaction);
    }

}
