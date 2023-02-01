package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.transaction.TransactionFacade;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.wallet.dto.CreateWalletDTO;
import com.eukon05.financetracker.wallet.dto.EditWalletDTO;
import com.eukon05.financetracker.wallet.dto.WalletDTO;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticDTO;
import com.eukon05.financetracker.wallet_statistic.WalletStatisticFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
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
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", ref = "validation"),
                    @ApiResponse(responseCode = "401", ref = "unauthorized"),
                    @ApiResponse(responseCode = "409", ref = "conflict")
            }
    )
    void createWallet(@RequestBody @Valid CreateWalletDTO dto, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.createWallet(jwt.getSubject(), dto);
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
    void deleteWallet(@PathVariable long walletID, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.deleteWallet(jwt.getSubject(), walletID);
    }

    @PatchMapping("/{walletID}")
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
    void editWallet(@PathVariable long walletID, @RequestBody @Valid EditWalletDTO dto, @AuthenticationPrincipal Jwt jwt) {
        walletFacade.editWallet(jwt.getSubject(), walletID, dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get information about all wallets")
    @ApiResponse(responseCode = "401", ref = "unauthorized")
    List<WalletDTO> getYourWallets(@AuthenticationPrincipal Jwt jwt) {
        return walletFacade.getUserWalletDTOs(jwt.getSubject());
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
    WalletDTO getWallet(@AuthenticationPrincipal Jwt jwt, @PathVariable long walletID) {
        return walletFacade.getWalletDTO(jwt.getSubject(), walletID);
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
    @Parameters(
            value = {
                    @Parameter(name = "fromDate", schema = @Schema(implementation = Instant.class)),
                    @Parameter(name = "toDate", schema = @Schema(implementation = Instant.class)),
                    @Parameter(name = "category", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "value", schema = @Schema(implementation = BigDecimal.class)),
                    @Parameter(name = "params", hidden = true)
            }
    )
    Page<TransactionDTO> getTransactions(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable long walletID,
            @ParameterObject Pageable pageable,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return transactionFacade.getPagedTransactionDTOsForWallet(jwt.getSubject(), walletID, params, pageable);
    }

    @GetMapping(value = "/{walletID}/statistics")
    @Parameters(
            value = {
                    @Parameter(name = "fromDate", schema = @Schema(implementation = Instant.class)),
                    @Parameter(name = "toDate", schema = @Schema(implementation = Instant.class)),
                    @Parameter(name = "category", schema = @Schema(implementation = Long.class)),
                    @Parameter(name = "params", hidden = true)
            }
    )
    List<WalletStatisticDTO> getWalletStatistics(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable long walletID,
            @RequestParam(required = false) Map<String, String> params
    ) {
        return statisticFacade.getWalletStatistics(jwt.getSubject(), walletID, params);
    }
}
