package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;

import java.util.List;

interface GetWalletStatisticsUseCase {

    List<WalletStatisticProjection> getWalletStatistics(Wallet wallet);

    WalletStatisticProjection getWalletStatisticsForCategory(Wallet wallet, long categoryID);

}
