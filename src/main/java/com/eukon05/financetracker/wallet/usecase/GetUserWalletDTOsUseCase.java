package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.wallet.dto.WalletDTO;

import java.util.List;

interface GetUserWalletDTOsUseCase {

    List<WalletDTO> getUserWalletDTOs(User user);

}
