package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.jwt.usecase.JwtFacade;
import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.user.RoleType;
import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.math.BigDecimal;
import java.time.Instant;

import static com.eukon05.financetracker.auth.AuthConstants.TOKEN_PREFIX;

@TestComponent
class IntegrationTestsUtils {

    @Autowired
    private JwtFacade jwtFacade;

    static final User testUser = new User();
    static final Wallet testWallet = new Wallet();
    static final Transaction testTransaction = new Transaction();
    static final TransactionCategory defaultCategory = new TransactionCategory();
    static final TransactionCategory testExpenseCategory = new TransactionCategory();
    static final TransactionCategory testIncomeCategory = new TransactionCategory();

    static {
        testUser.setEnabled(true);
        testUser.setId(1);
        testUser.setUsername("test");
        testUser.setEmail("test@test.com");
        testUser.getRoles().add(RoleType.USER);
        testUser.setPassword("$2a$12$HkAx9wUgrHqtPnGoyHwwNOdY6d1e/cOyLfkwUrfev2Gw9OXc7M0z6");

        testWallet.setId(1);
        testWallet.setCurrency("EUR");
        testWallet.setName("test wallet");
        testWallet.setUser(testUser);
        testUser.getWallets().add(testWallet);

        defaultCategory.setId(0);
        defaultCategory.setType(TransactionCategoryType.DEFAULT);
        defaultCategory.setName("No category");

        testIncomeCategory.setId(1);
        testIncomeCategory.setType(TransactionCategoryType.INCOME);
        testIncomeCategory.setName("INCOME");


        testTransaction.setValue(BigDecimal.valueOf(100));
        testTransaction.setWallet(testWallet);
        testTransaction.setCategory(testIncomeCategory);
        testTransaction.setCreatedAt(Instant.now());
        testTransaction.setId(2);
        testWallet.getTransactions().add(testTransaction);
    }

    String getDefaultToken() {
        return TOKEN_PREFIX.getValue() + jwtFacade.generateAccessToken(testUser, "test");
    }

    String getToken(User user) {
        return TOKEN_PREFIX.getValue() + jwtFacade.generateAccessToken(user, "test");
    }
}
