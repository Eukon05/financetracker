package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.exception.WalletNameTakenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.eukon05.financetracker.unit.TestUtils.testWallet;
import static com.eukon05.financetracker.unit.TestUtils.userID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WalletServiceTests {
    private final WalletRepository repository = Mockito.mock(WalletRepository.class);
    private final WalletModelMapper mapper = new WalletModelMapperImpl();
    private final CurrencyConverter converter = new CurrencyConverterImpl(new ObjectMapper());
    private final WalletService service = new WalletServiceImpl(mapper, repository, converter);
    private static final CreateWalletDTO createDTO = new CreateWalletDTO("new wallet", "PLN");
    private static final EditWalletDTO editDTO = new EditWalletDTO("some name", "PLN");

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
        service.deleteWallet(testWallet);
        Mockito.verify(repository).delete(Mockito.any(Wallet.class));
    }

    @Test
    void should_edit_wallet() {
        service.editWallet(testWallet, editDTO);
        assertEquals(editDTO.name(), testWallet.getName());
        assertEquals(editDTO.currency(), testWallet.getCurrency());
    }

    @Test
    void should_not_edit_wallet() {
        Mockito.when(repository.existsByUserIdAndName(testWallet.getUserId(), editDTO.name())).thenReturn(true);
        assertThrows(WalletNameTakenException.class, () -> service.editWallet(testWallet, editDTO));
    }

    @Test
    void should_get_user_wallets() {
        List<Wallet> list = List.of(testWallet);
        Mockito.when(repository.getWalletsByUserId(Mockito.anyString())).thenReturn(list);
        assertEquals(list.stream().map(mapper::mapWalletToWalletDTO).toList(), service.getUserWalletDTOs(Mockito.anyString()));
    }
}
