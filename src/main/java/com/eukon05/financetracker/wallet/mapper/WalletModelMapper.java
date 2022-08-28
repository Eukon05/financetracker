package com.eukon05.financetracker.wallet.mapper;

import com.eukon05.financetracker.user.mapper.PasswordEncoderMapper;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface WalletModelMapper {

    WalletDTO mapWalletToWalletDTO(Wallet wallet);

}
