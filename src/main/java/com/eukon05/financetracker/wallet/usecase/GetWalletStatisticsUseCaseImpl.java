package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.WalletStatisticRepository;
import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetWalletStatisticsUseCaseImpl implements GetWalletStatisticsUseCase {

    private final WalletStatisticRepository repository;

    @Override
    public List<WalletStatisticProjection> getWalletStatistics(Wallet wallet) {
        return repository.getWalletStatistics(wallet);
    }

    @Override
    public WalletStatisticProjection getWalletStatisticsForCategory(Wallet wallet, long categoryID) {
        return repository.getWalletStatisticsForCategory(wallet, categoryID);
    }

}
