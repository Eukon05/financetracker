package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class EditWalletUseCaseImpl implements EditWalletUseCase {
    private final GetWalletByIdUseCase getWalletByIdUseCase;

    @Override
    @Transactional
    public void editWallet(String username, long walletID, String name) {
        Wallet wallet = getWalletByIdUseCase.getWalletById(username, walletID);

        wallet.getUser().getWallets().stream().filter(found -> found.getName().equals(name)).findFirst().ifPresent(found -> {
            if (!found.equals(wallet)) {
                throw new WalletNameTakenException(name);
            }
        });

        wallet.setName(name);
    }

}
