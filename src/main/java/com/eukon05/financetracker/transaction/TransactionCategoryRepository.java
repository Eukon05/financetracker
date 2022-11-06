package com.eukon05.financetracker.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {

    List<TransactionCategory> findAllByType(TransactionType type);

    boolean existsByNameAndType(String name, TransactionType type);

}
