package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetUserWalletsUseCaseImpl implements GetUserWalletsUseCase {

    private final UserFacade userFacade;
    private final WalletModelMapper walletModelMapper;

    @Override
    public List<WalletDTO> getUserWallets(String username) {
        return userFacade.getUserByUsernameOrThrow(username).getWallets().stream().map(walletModelMapper::mapWalletToWalletDTO).toList();
    }
}