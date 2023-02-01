package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.dto.CreateTransactionDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionDTO;
import com.eukon05.financetracker.transaction.exception.TransactionTypeMismatchException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static com.eukon05.financetracker.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTests {

    private final TransactionRepository repository = Mockito.mock(TransactionRepository.class);
    private final TransactionModelMapper mapper = new TransactionModelMapperImpl();
    private final TransactionService service = new TransactionServiceImpl(mapper, repository);
    private static final String userID = "SOMEUSERID";
    private static final CreateTransactionDTO createDTO = new CreateTransactionDTO(1, "somename", BigDecimal.valueOf(120), 0);

    @Test
    void should_create_transaction() {
        service.createTransaction(testWallet, testDefaultCategory, createDTO);
        assertTrue(testWallet.getTransactions().size() > 1);
        testWallet.getTransactions().clear();
        testWallet.getTransactions().add(testTransaction);
    }

    @Test
    void should_delete_transaction() {
        service.deleteTransaction(testTransaction);
        Mockito.verify(repository).delete(testTransaction);
    }

    @Test
    void should_edit_transaction() {
        EditTransactionDTO dto = new EditTransactionDTO("editedname", BigDecimal.valueOf(-100), 1);

        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        service.editTransaction(testTransaction, testExpenseCategory, dto);
        assertEquals(dto.name(), testTransaction.getName());
        assertEquals(dto.value(), testTransaction.getValue());
        assertEquals(testExpenseCategory, testTransaction.getCategory());
    }

    @Test
    void should_not_edit_transaction() {
        EditTransactionDTO dto = new EditTransactionDTO(null, BigDecimal.valueOf(100), 1);

        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        assertThrows(TransactionTypeMismatchException.class, () -> service.editTransaction(testTransaction, testExpenseCategory, dto));
    }

    @Test
    void should_get_transaction() {
        Mockito.when(repository.getTransactionByWallet_UserIdAndId(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(testTransaction));

        assertEquals(mapper.mapTransactionToTransactionDTO(testTransaction), service.getTransactionDTO(userID, testTransaction.getId()));
    }

}
