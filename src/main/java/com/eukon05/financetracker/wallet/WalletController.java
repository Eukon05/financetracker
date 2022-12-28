package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.usecase.transaction.TransactionFacade;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet.usecase.WalletFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Handles operations related to wallets")
class WalletController {

    private final WalletFacade walletFacade;
    private final TransactionFacade transactionFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new wallet")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", ref = "validation"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "409", ref = "conflict")
            }
    )
    void createWallet(@RequestBody @Valid CreateWalletDTO dto, Principal principal) {
        walletFacade.createWallet(principal.getName(), dto.name(), dto.currency());
    }

    @DeleteMapping("/{walletID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete wallet with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized")
            }
    )
    void deleteWallet(@PathVariable long walletID, Principal principal) {
        walletFacade.deleteWallet(principal.getName(), walletID);
    }

    @PutMapping("/{walletID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit wallet with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "400", ref = "validation"),
                    @ApiResponse(responseCode = "409", ref = "conflict")
            }
    )
    void editWallet(@PathVariable long walletID, @RequestBody @Valid EditWalletDTO dto, Principal principal) {
        walletFacade.editWallet(principal.getName(), walletID, dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about all wallets")
    @ApiResponse(responseCode = "401", ref = "unauthorized")
    List<WalletDTO> getYourWallets(Principal principal) {
        return walletFacade.getUserWalletDTOs(principal.getName());
    }

    @GetMapping(value = "/{walletID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a single wallet with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized")
            }
    )
    WalletDTO getWallet(Principal principal, @PathVariable long walletID) {
        return walletFacade.getWalletDTOById(principal.getName(), walletID);
    }

    @GetMapping(value = "/{walletID}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all transactions associated with a wallet with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", ref = "notfound"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized")
            }
    )
    Page<TransactionDTO> getTransactions(Principal principal, @PathVariable long walletID, @ParameterObject Pageable pageable) {
        return transactionFacade.getPagedTransactionDTOsForWallet(principal.getName(), walletID, pageable);
    }

}
