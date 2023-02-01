package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction_category.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction_category.exception.DefaultTransactionCategoryModificationException;
import com.eukon05.financetracker.transaction_category.exception.TransactionCategoryAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static com.eukon05.financetracker.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionCategoryServiceTests {

    private final TransactionCategoryRepository repository = Mockito.mock(TransactionCategoryRepository.class);
    private final TransactionCategoryModelMapper mapper = new TransactionCategoryModelMapperImpl();
    private final TransactionCategoryService service = new TransactionCategoryServiceImpl(repository, mapper);
    private static final CreateTransactionCategoryDTO createIncomeDTO = new CreateTransactionCategoryDTO("INCOME", TransactionCategoryType.INCOME);
    private static final EditTransactionCategoryDTO editDTO = new EditTransactionCategoryDTO("EDITED");

    @Test
    void should_create_category() {
        service.createTransactionCategory(createIncomeDTO);
        Mockito.verify(repository).save(Mockito.any(TransactionCategory.class));
    }

    @Test
    void should_not_create_duplicate_category() {
        Mockito.when(repository.existsByNameAndType(createIncomeDTO.name(), createIncomeDTO.type()))
                .thenReturn(true);
        assertThrows(TransactionCategoryAlreadyExistsException.class, () -> service.createTransactionCategory(createIncomeDTO));
    }

    @Test
    void should_not_create_another_default_category() {
        CreateTransactionCategoryDTO dto = new CreateTransactionCategoryDTO("invalid category", TransactionCategoryType.DEFAULT);
        assertThrows(DefaultTransactionCategoryModificationException.class, () -> service.createTransactionCategory(dto));
    }

    @Test
    void should_not_edit_default_category() {
        assertThrows(DefaultTransactionCategoryModificationException.class, () -> service.editTransactionCategory(testDefaultCategory, editDTO));
    }

    @Test
    void should_edit_category() {
        service.editTransactionCategory(testExpenseCategory, editDTO);
        assertEquals(editDTO.name(), testExpenseCategory.getName());
    }

    @Test
    void should_not_allow_duplicate_name_edit() {
        Mockito.when(repository.existsByNameAndType(Mockito.anyString(), Mockito.any(TransactionCategoryType.class))).thenReturn(true);
        assertThrows(TransactionCategoryAlreadyExistsException.class, () -> service.editTransactionCategory(testExpenseCategory, editDTO));
    }

    @Test
    void should_get_category() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(testExpenseCategory));
        assertEquals(testExpenseCategory, service.getTransactionCategory(Mockito.anyLong()));
    }

    @Test
    void should_delete_category() {
        testTransaction.setValue(BigDecimal.valueOf(-100));
        testTransaction.setCategory(testExpenseCategory);
        testExpenseCategory.getTransactions().add(testTransaction);
        Mockito.when(repository.findByType(TransactionCategoryType.DEFAULT)).thenReturn(Optional.of(testDefaultCategory));
        service.deleteTransactionCategory(testExpenseCategory);
        Mockito.verify(repository).delete(testExpenseCategory);
        assertEquals(testDefaultCategory, testTransaction.getCategory());
    }

    @Test
    void should_not_delete_default_category() {
        assertThrows(DefaultTransactionCategoryModificationException.class, () -> service.deleteTransactionCategory(testDefaultCategory));
    }

}
