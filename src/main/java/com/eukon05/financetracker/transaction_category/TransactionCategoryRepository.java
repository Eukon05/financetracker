package com.eukon05.financetracker.transaction_category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {

    List<TransactionCategory> findAllByType(TransactionCategoryType type);

    Optional<TransactionCategory> findByType(TransactionCategoryType type);

    boolean existsByNameAndType(String name, TransactionCategoryType type);

}
