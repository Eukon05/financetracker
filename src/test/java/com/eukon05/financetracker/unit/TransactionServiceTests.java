package com.eukon05.financetracker.unit;

import com.eukon05.financetracker.transaction.TransactionRepository;
import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.exceptions.TransactionTypeMismatchException;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapper;
import com.eukon05.financetracker.transaction.mapper.TransactionModelMapperImpl;
import com.eukon05.financetracker.transaction.service.transaction.TransactionService;
import com.eukon05.financetracker.transaction.service.transactionCategory.TransactionCategoryService;
import com.eukon05.financetracker.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static com.eukon05.financetracker.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTests {

    private final TransactionRepository repository = Mockito.mock(TransactionRepository.class);
    private final TransactionCategoryService categoryService = Mockito.mock(TransactionCategoryService.class);
    private final WalletService walletService = Mockito.mock(WalletService.class);
    private final TransactionModelMapper mapper = new TransactionModelMapperImpl();
    private final TransactionService service = new TransactionService(walletService, categoryService, mapper, repository);
    private static final String userID = "SOMEUSERID";
    private static final CreateTransactionDTO createDTO = new CreateTransactionDTO(1, "somename", BigDecimal.valueOf(120), 0);

    @Test
    void should_create_transaction() {
        Mockito.when(walletService.getWalletById(Mockito.anyString(), Mockito.anyLong())).thenReturn(testWallet);
        Mockito.when(categoryService.getTransactionCategory(Mockito.anyLong())).thenReturn(testDefaultCategory);
        service.createTransaction(userID, createDTO);
        assertTrue(testWallet.getTransactions().size() > 1);
        testWallet.getTransactions().clear();
        testWallet.getTransactions().add(testTransaction);
    }

    @Test
    void should_delete_transaction() {
        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        service.deleteTransaction(userID, testTransaction.getId());
        Mockito.verify(repository).delete(testTransaction);
    }

    @Test
    void should_edit_transaction() {
        Mockito.when(categoryService.getTransactionCategory(Mockito.anyLong())).thenReturn(testExpenseCategory);
        EditTransactionDTO dto = new EditTransactionDTO("editedname", BigDecimal.valueOf(-100), 1);

        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        service.editTransaction(userID, testTransaction.getId(), dto);
        assertEquals(dto.name(), testTransaction.getName());
        assertEquals(dto.value(), testTransaction.getValue());
        assertEquals(testExpenseCategory, testTransaction.getCategory());
    }

    @Test
    void should_not_edit_transaction() {
        Mockito.when(categoryService.getTransactionCategory(Mockito.anyLong())).thenReturn(testExpenseCategory);
        EditTransactionDTO dto = new EditTransactionDTO(null, BigDecimal.valueOf(100), 1);

        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        assertThrows(TransactionTypeMismatchException.class, () -> service.editTransaction(userID, testTransaction.getId(), dto));
    }

    @Test
    void should_get_transaction() {
        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        assertEquals(mapper.mapTransactionToTransactionDTO(testTransaction), service.getTransactionDTOById(userID, testTransaction.getId()));
    }

}
