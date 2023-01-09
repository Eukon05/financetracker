package com.eukon05.financetracker.wallet_statistic;

import java.util.List;

public interface WalletStatisticService {

    List<WalletStatistic> getWalletStatisticsById(String userId, long walletID, Long categoryID);

}
