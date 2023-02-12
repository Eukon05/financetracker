package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.openapi.param.*;
import com.eukon05.financetracker.openapi.response.ConflictResponse;
import com.eukon05.financetracker.openapi.response.NotFoundResponse;
import com.eukon05.financetracker.openapi.response.ValidationErrorResponse;
import com.eukon05.financetracker.transaction.TransactionFacade;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticDTO;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet", description = "Handles operations related to wallets")
class WalletController {
    private final WalletFacade walletFacade;
    private final TransactionFacade transactionFacade;
    private final WalletStatisticFacade statisticFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new wallet")
    @ValidationErrorResponse
    @ConflictResponse
    void createWallet(@RequestBody @Valid CreateWalletDTO dto, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.createWallet(jwt.getSubject(), dto);
    }

    @DeleteMapping("/{walletID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete wallet with a given ID")
    @NotFoundResponse
    void deleteWallet(@PathVariable long walletID, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.deleteWallet(jwt.getSubject(), walletID);
    }

    @PatchMapping("/{walletID}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit wallet with a given ID")
    @NotFoundResponse
    @ConflictResponse
    @ValidationErrorResponse
    void editWallet(@PathVariable long walletID, @RequestBody @Valid EditWalletDTO dto, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.editWallet(jwt.getSubject(), walletID, dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about all wallets")
    List<WalletDTO> getYourWallets(@AuthenticationPrincipal Jwt jwt) {
        return walletFacade.getUserWalletDTOs(jwt.getSubject());
    }

    @GetMapping(value = "/{walletID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about a single wallet with a given ID")
    @NotFoundResponse
    WalletDTO getWallet(@AuthenticationPrincipal Jwt jwt, @PathVariable long walletID) {
        return walletFacade.getWalletDTO(jwt.getSubject(), walletID);
    }

    @GetMapping(value = "/{walletID}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all transactions associated with a wallet with a given ID")
    @NotFoundResponse
    @FromDateParam
    @ToDateParam
    @CategoryParam
    @ValueParam
    @HideParamsMap
    Page<TransactionDTO> getTransactions(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable long walletID,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return transactionFacade.getPagedTransactionDTOsForWallet(jwt.getSubject(), walletID, params, pageable);
    }

    @GetMapping(value = "/{walletID}/statistics")
    @FromDateParam
    @ToDateParam
    @CategoryParam
    @HideParamsMap
    List<WalletStatisticDTO> getWalletStatistics(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable long walletID,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return statisticFacade.getWalletStatistics(jwt.getSubject(), walletID, params);
    }
}
