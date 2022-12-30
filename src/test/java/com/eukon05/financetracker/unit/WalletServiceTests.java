package com.eukon05.financetracker.unit;

import com.eukon05.financetracker.wallet.*;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapperImpl;
import com.eukon05.financetracker.wallet.projection.WalletStatisticProjection;
import com.eukon05.financetracker.wallet.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.eukon05.financetracker.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalletServiceTests {

    private final WalletRepository repository = Mockito.mock(WalletRepository.class);
    private final WalletStatisticRepository statisticRepository = Mockito.mock(WalletStatisticRepository.class);
    private final WalletModelMapperImpl mapper = new WalletModelMapperImpl();
    private final CurrencyConverter converter = new CurrencyConverterImpl(new ObjectMapper());

    private final WalletService service = new WalletService(mapper, repository, statisticRepository, converter);

    private static final CreateWalletDTO createDTO = new CreateWalletDTO("new wallet", "PLN");
    private static final EditWalletDTO editDTO = new EditWalletDTO("some name", "PLN");
    private static final WalletStatisticProjection projection = new WalletStatisticProjection() {
        @Override
        public long getCategoryID() {
            return 1;
        }

        @Override
        public BigDecimal getSum() {
            return BigDecimal.valueOf(10000);
        }
    };

    @Test
    void should_create_wallet() {
        service.createWallet(userID, createDTO);
        Mockito.verify(repository).save(Mockito.any(Wallet.class));
    }

    @Test
    void should_validate_wallet_already_exists() {
        Mockito.when(repository.existsByUserIdAndName(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        assertThrows(WalletNameTakenException.class, () -> service.createWallet(userID, createDTO));
    }

    @Test
    void should_delete_wallet() {
        Mockito.when(repository.getWalletByUserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testWallet));

        service.deleteWallet(Mockito.anyString(), Mockito.anyLong());
        Mockito.verify(repository).delete(Mockito.any(Wallet.class));
    }

    @Test
    void should_edit_wallet() {
        Mockito.when(repository.getWalletByUserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testWallet));
        service.editWallet(userID, walletID, editDTO);
        assertEquals(editDTO.name(), testWallet.getName());
        assertEquals(editDTO.currency(), testWallet.getCurrency());
    }

    @Test
    void should_not_edit_wallet() {
        Mockito.when(repository.getWalletByUserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testWallet));
        Mockito.when(repository.existsByUserIdAndName(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        assertThrows(WalletNameTakenException.class, () -> service.editWallet(userID, walletID, editDTO));
    }

    @Test
    void should_get_user_wallets() {
        List<Wallet> list = List.of(testWallet);
        Mockito.when(repository.getWalletsByUserId(Mockito.anyString())).thenReturn(list);
        assertEquals(list.stream().map(mapper::mapWalletToWalletDTO).toList(), service.getUserWalletDTOs(Mockito.anyString()));
    }

    @Test
    void should_get_wallet_statistics() {
        Mockito.when(repository.getWalletByUserIdAndId(Mockito.anyString(), Mockito.anyLong())).thenReturn(Optional.of(testWallet));
        Mockito.when(statisticRepository.getWalletStatistics(Mockito.any(Wallet.class))).thenReturn(List.of(projection));
        assertEquals(List.of(projection), service.getWalletStatisticsById(userID, walletID, null));
    }

}