package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.wallet.CurrencyConverter;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class EditWalletUseCaseImpl implements EditWalletUseCase {

    private final CurrencyConverter converter;

    @Override
    @Transactional
    public void editWallet(Wallet wallet, EditWalletDTO dto) {
        wallet.getUser().getWallets().stream().filter(found -> found.getName().equals(dto.name())).findFirst().ifPresent(found -> {
            if (!found.equals(wallet)) {
                throw new WalletNameTakenException(dto.name());
            }
        });

        Optional.ofNullable(dto.name()).ifPresent(wallet::setName);
        Optional.ofNullable(dto.currency()).ifPresent(currency -> {
            for (Transaction transaction : wallet.getTransactions()) {
                transaction.setValue(converter.convert(wallet.getCurrency(), currency, transaction.getValue(), transaction.getCreatedAt()));
            }
            wallet.setCurrency(currency);
        });
    }



}
