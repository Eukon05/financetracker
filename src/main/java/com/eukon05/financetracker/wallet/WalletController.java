package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateEditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.mapper.WalletModelMapper;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
class WalletController {

    private final WalletFacade walletFacade;
    private final WalletModelMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createWallet(@RequestBody @Valid CreateEditWalletDTO dto, Principal principal) {
        walletFacade.createWallet(principal.getName(), dto.name());
    }

    @DeleteMapping("/{walletID}")
    void deleteWallet(@PathVariable long walletID, Principal principal) {
        walletFacade.deleteWallet(principal.getName(), walletID);
    }

    @PutMapping("/{walletID}")
    void editWallet(@PathVariable long walletID, @RequestBody @Valid CreateEditWalletDTO dto, Principal principal) {
        walletFacade.editWallet(principal.getName(), walletID, dto.name());
    }

    @GetMapping
    List<WalletDTO> getYourWallets(Principal principal) {
        return walletFacade.getUserWallets(principal.getName());
    }

    @GetMapping("/{walletID}")
    WalletDTO getWallet(Principal principal, @PathVariable long walletID) {
        //I should move the mapping code to a service/use case class, but it's fine for now
        return mapper.mapWalletToWalletDTO(walletFacade.getWalletById(principal.getName(), walletID));
    }

}
