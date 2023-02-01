package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.WalletDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface WalletModelMapper {
    WalletDTO mapWalletToWalletDTO(Wallet wallet);
}
