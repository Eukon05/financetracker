package com.eukon05.financetracker.wallet.usecase;

import com.eukon05.financetracker.user.User;
import com.eukon05.financetracker.user.usecase.UserFacade;
import com.eukon05.financetracker.wallet.Wallet;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapper;
import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletFacade {

    private final UserFacade userFacade;
    private final WalletModelMapper mapper;
    private final CreateWalletUseCase createWalletUseCase;
    private final DeleteWalletUseCase deleteWalletUseCase;
    private final EditWalletUseCase editWalletUseCase;
    private final GetUserWalletDTOsUseCase getUserWalletDTOsUseCase;
    private final GetWalletByIdUseCase getWalletByIdUseCase;
    private final GetWalletStatisticsUseCase getWalletStatisticsUseCase;

    public void createWallet(String username, String name, String currency) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        createWalletUseCase.createWallet(user, name, currency);
    }

    public void deleteWallet(String username, long walletID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Wallet wallet = getWalletByIdUseCase.getWalletById(user, walletID);
        deleteWalletUseCase.deleteWallet(wallet);
    }

    public void editWallet(String username, long walletID, EditWalletDTO dto) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Wallet wallet = getWalletByIdUseCase.getWalletById(user, walletID);
        editWalletUseCase.editWallet(wallet, dto);
    }

    public List<WalletDTO> getUserWalletDTOs(String username) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        return getUserWalletDTOsUseCase.getUserWalletDTOs(user);
    }

    public WalletDTO getWalletDTOById(String username, long walletID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        Wallet wallet = getWalletByIdUseCase.getWalletById(user, walletID);
        return mapper.mapWalletToWalletDTO(wallet);
    }

    public Wallet getWalletById(String username, long walletID) {
        User user = userFacade.getUserByUsernameOrThrow(username);
        return getWalletByIdUseCase.getWalletById(user, walletID);
    }

    public List<WalletStatisticProjection> getWalletStatisticsById(String username, long walletID, Long categoryID) {
        Wallet wallet = getWalletById(username, walletID);

        if (categoryID != null)
            return List.of(getWalletStatisticsUseCase.getWalletStatisticsForCategory(wallet, categoryID));

        return getWalletStatisticsUseCase.getWalletStatistics(wallet);
    }

}
