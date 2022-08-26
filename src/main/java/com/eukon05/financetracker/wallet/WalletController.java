package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
class WalletController {

    private final WalletFacade walletFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createWallet(@RequestBody @Valid CreateWalletDTO dto, Principal principal) {
        walletFacade.createWallet(principal.getName(), dto.name());
    }

    @DeleteMapping
    @RequestMapping("/{walletID}")
    void deleteWallet(@PathVariable long walletID, Principal principal) {
        walletFacade.deleteWallet(principal.getName(), walletID);
    }

}
