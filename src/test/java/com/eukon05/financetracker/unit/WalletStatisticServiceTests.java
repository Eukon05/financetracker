package com.eukon05.financetracker.unit;

import com.eukon05.financetracker.transaction_category.TransactionCategory;
import com.eukon05.financetracker.transaction_category.TransactionCategoryService;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.WalletService;
import com.eukon05.financetracker.wallet_statistic.WalletStatistic;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticRepository;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticService;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.eukon05.financetracker.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletStatisticServiceTests {

    private final WalletService walletService = Mockito.mock(WalletService.class);
    private final WalletStatisticRepository repository = Mockito.mock(WalletStatisticRepository.class);
    private final TransactionCategoryService categoryService = Mockito.mock(TransactionCategoryService.class);
    private final WalletStatisticService service = new WalletStatisticServiceImpl(walletService, categoryService, repository);

    private static final WalletStatistic projection = new WalletStatistic() {
        @Override
        public long getCategoryID() {
            return 1;
        }

        @Override
        public BigDecimal getSum() {
            return BigDecimal.valueOf(10000);
        }
    };

    @Test
    void should_get_wallet_statistics() {
        Mockito.when(walletService.getWalletById(Mockito.anyString(), Mockito.anyLong())).thenReturn(testWallet);
        Mockito.when(repository.getWalletStatistics(Mockito.any(Wallet.class))).thenReturn(List.of(projection));
        assertEquals(List.of(projection), service.getWalletStatisticsById(userID, walletID, null));
    }

    @Test
    void should_get_wallet_statistics_for_category() {
        Mockito.when(walletService.getWalletById(Mockito.anyString(), Mockito.anyLong())).thenReturn(testWallet);
        Mockito.when(categoryService.getTransactionCategory(Mockito.anyLong())).thenReturn(testDefaultCategory);
        Mockito.when(repository.getWalletStatisticsForCategory(Mockito.any(Wallet.class), Mockito.any(TransactionCategory.class)))
                .thenReturn(Optional.of(projection));
        assertEquals(List.of(projection), service.getWalletStatisticsById(userID, walletID, 0L));
    }

}
