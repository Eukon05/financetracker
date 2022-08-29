package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.usecase.TransactionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallets/{walletID}/transactions")
@RequiredArgsConstructor
class TransactionController {

    private final TransactionFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createTransaction(@PathVariable long walletID, @RequestBody @Valid CreateTransactionDTO dto, Principal principal) {
        facade.createTransaction(principal.getName(), walletID, dto);
    }

}
