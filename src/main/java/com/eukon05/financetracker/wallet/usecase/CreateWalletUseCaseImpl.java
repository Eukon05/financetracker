package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateWalletUseCaseImpl implements CreateWalletUseCase {

    private final UserFacade userFacade;

    @Override
    @Transactional
    public void createWallet(String username, String name) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        user.getWallets().stream().filter(wallet -> wallet.getName().equals(name)).findFirst().ifPresent(found -> {
            throw new WalletNameTakenException(name);
        });
        user.getWallets().add(new Wallet(name));
    }

}
