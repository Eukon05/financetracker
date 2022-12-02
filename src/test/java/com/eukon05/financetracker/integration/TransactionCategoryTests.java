package com.eukon05.financetracker.integration;

import com.eukon05.financetracker.transaction.TransactionCategory;
import com.eukon05.financetracker.transaction.TransactionCategoryRepository;
import com.eukon05.financetracker.transaction.TransactionCategoryType;
import com.eukon05.financetracker.transaction.dto.CreateTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.EditTransactionCategoryDTO;
import com.eukon05.financetracker.transaction.dto.TransactionCategoryDTO;
import com.eukon05.financetracker.user.RoleType;
import com.eukon05.financetracker.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static com.eukon05.financetracker.integration.IntegrationTestsUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionCategoryTests extends AbstractIntegrationTest {

    @MockBean
    private TransactionCategoryRepository transactionCategoryRepository;

    private static final CreateTransactionCategoryDTO incomeCreateDTO = new CreateTransactionCategoryDTO("Test expense", TransactionCategoryType.EXPENSE);
    private static final EditTransactionCategoryDTO editDto = new EditTransactionCategoryDTO("new name");
    private static final User adminUser = new User();

    static {
        adminUser.setId(2);
        adminUser.setPassword(testUser.getPassword());
        adminUser.setEnabled(true);
        adminUser.setEmail("admin@admin.com");
        adminUser.setUsername("admin");
        adminUser.getRoles().add(RoleType.ADMIN);
    }

    @Test
    void should_not_create_category() throws Exception {
        mockMvc.perform(post("/categories")
                        .header(AUTHORIZATION, utils.getDefaultToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incomeCreateDTO)))
                .andExpect(status().isForbidden());
    }

    @Test
    void should_create_category() throws Exception {
        makeCreateCategoryRequest(incomeCreateDTO).andExpect(status().isCreated());
        Mockito.verify(transactionCategoryRepository).save(Mockito.any(TransactionCategory.class));
    }

    @Test
    void should_not_create_duplicate_category() throws Exception {
        Mockito.when(transactionCategoryRepository.existsByNameAndType(incomeCreateDTO.name(), incomeCreateDTO.type()))
                .thenReturn(true);

        makeCreateCategoryRequest(incomeCreateDTO).andExpect(status().isConflict());
    }

    @Test
    void should_not_create_another_default_category() throws Exception {
        CreateTransactionCategoryDTO dto = new CreateTransactionCategoryDTO("invalid category", TransactionCategoryType.DEFAULT);
        makeCreateCategoryRequest(dto).andExpect(status().isBadRequest());
    }

    @Test
    void should_not_edit_default_category() throws Exception {
        Mockito.when(transactionCategoryRepository.findById(0L)).thenReturn(Optional.of(defaultCategory));
        makeEditCategoryRequest(0).andExpect(status().isBadRequest());
    }

    @Test
    void should_edit_category() throws Exception {
        Mockito.when(transactionCategoryRepository.findById(1L)).thenReturn(Optional.of(testIncomeCategory));
        Mockito.when(transactionCategoryRepository.existsByNameAndType(Mockito.anyString(), Mockito.any(TransactionCategoryType.class))).thenReturn(false);

        makeEditCategoryRequest(1).andExpect(status().isOk());
        assertEquals(editDto.name(), testIncomeCategory.getName());
    }

    @Test
    void should_not_edit_category_with_duplicate_name() throws Exception {
        Mockito.when(transactionCategoryRepository.existsByNameAndType(editDto.name(), testIncomeCategory.getType()))
                .thenReturn(true);
        Mockito.when(transactionCategoryRepository.findById(1L)).thenReturn(Optional.of(testIncomeCategory));

        makeEditCategoryRequest(1).andExpect(status().isConflict());
    }

    @Test
    void should_get_category() throws Exception {
        Mockito.when(transactionCategoryRepository.findById(1L)).thenReturn(Optional.of(testIncomeCategory));

        TransactionCategoryDTO dto = objectMapper.readValue(mockMvc.perform(get("/categories/1")
                        .header(AUTHORIZATION, utils.getToken(adminUser)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), TransactionCategoryDTO.class);

        assertEquals(testIncomeCategory.getName(), dto.name());
    }

    @Test
    void should_not_delete_default_category() throws Exception {
        Mockito.when(transactionCategoryRepository.findById(0L)).thenReturn(Optional.of(defaultCategory));
        makeDeleteCategoryRequest(0).andExpect(status().isBadRequest());
    }

    @Test
    void should_delete_category() throws Exception {
        Mockito.when(transactionCategoryRepository.findById(0L)).thenReturn(Optional.of(defaultCategory));
        Mockito.when(transactionCategoryRepository.findById(1L)).thenReturn(Optional.of(testIncomeCategory));
        makeDeleteCategoryRequest(1).andExpect(status().isOk());
        Mockito.verify(transactionCategoryRepository).delete(Mockito.any(TransactionCategory.class));
    }


    private ResultActions makeCreateCategoryRequest(CreateTransactionCategoryDTO dto) throws Exception {
        return mockMvc.perform(post("/categories")
                .header(AUTHORIZATION, utils.getToken(adminUser))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
    }

    private ResultActions makeEditCategoryRequest(long id) throws Exception {
        return mockMvc.perform(put(String.format("/categories/%d", id))
                .header(AUTHORIZATION, utils.getToken(adminUser))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(editDto)));
    }

    private ResultActions makeDeleteCategoryRequest(long id) throws Exception {
        return mockMvc.perform(delete(String.format("/categories/%d", id))
                .header(AUTHORIZATION, utils.getToken(adminUser)));
    }

}
