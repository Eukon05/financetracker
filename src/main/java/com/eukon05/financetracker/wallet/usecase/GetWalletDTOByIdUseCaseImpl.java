package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetWalletDTOByIdUseCaseImpl implements GetWalletDTOByIdUseCase {

    private final GetWalletByIdUseCase getWalletByIdUseCase;
    private final WalletModelMapper mapper;

    @Override
    public WalletDTO getWalletDTOById(String username, long walletID) {
        return mapper.mapWalletToWalletDTO(getWalletByIdUseCase.getWalletById(username, walletID));
    }
}
