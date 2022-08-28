package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import com.eukon05.financetracker.wallet.exception.WalletNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class EditWalletUseCaseImpl implements EditWalletUseCase {

    private final UserFacade userFacade;

    @Override
    @Transactional
    public void editWallet(String username, long walletID, String name) {
        User user = userFacade.getUserByUsernameOrThrow(username);

        Wallet wallet = user.getWallets().stream().filter(wlt -> wlt.getId() == walletID).findFirst().orElseThrow(() -> new WalletNotFoundException(walletID));

        user.getWallets().stream().filter(found -> found.getName().equals(name)).findFirst().ifPresent(found -> {
            if (!found.equals(wallet)) {
                throw new WalletNameTakenException(name);
            }
        });

        wallet.setName(name);
    }

}
