package com.eukon05.financetracker.wallet_statistic;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction_category.TransactionCategory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.eukon05.financetracker.unit.TestUtils.testTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletStatisticServiceTests {
    private final WalletStatisticService service = new WalletStatisticServiceImpl();

    @Test
    void should_get_wallet_statistics() {
        Map<TransactionCategory, List<Transaction>> map = new HashMap<>();
        map.put(testTransaction.getCategory(), List.of(testTransaction));
        WalletStatisticDTO dto = new WalletStatisticDTO(testTransaction.getCategory().getId(), testTransaction.getValue());

        assertEquals(dto, service.generateWalletStatistics(map).get(0));
    }

}
