package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.TransactionDTO;
import com.eukon05.financetracker.transaction.usecase.TransactionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
class TransactionController {

    private final TransactionFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createTransaction(@RequestBody @Valid CreateTransactionDTO dto, Principal principal) {
        facade.createTransaction(principal.getName(), dto);
    }

    @DeleteMapping("/{transactionID}")
    void deleteTransaction(@PathVariable long transactionID, Principal principal) {
        facade.deleteTransaction(principal.getName(), transactionID);
    }

    @GetMapping("/{transactionID}")
    TransactionDTO getTransaction(@PathVariable long transactionID, Principal principal) {
        return facade.getTransactionDTOById(principal.getName(), transactionID);
    }

}
