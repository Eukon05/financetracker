package com.eukon05.financetracker.wallet_statistic;

import java.util.List;
import java.util.Map;

public interface WalletStatisticFacade {
    List<WalletStatisticDTO> getWalletStatistics(String userId, long walletID, Map<String, String> params);
}
