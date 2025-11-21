package com.example.personal_expense_manager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.personal_expense_manager.dto.response.ExpenseStatResponse;
import com.example.personal_expense_manager.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query("SELECT new com.example.personal_expense_manager.dto.response.ExpenseStatResponse(" +
        "SUM(t.amount), " +
        "t.type, " +
        "c.id, " +
        "c.name) " +
        "FROM Transaction t JOIN t.category c " +
        "WHERE t.date BETWEEN :startDate AND :endDate " +
        "GROUP BY c.id, c.name, t.type" 
    )
    List<ExpenseStatResponse> getExpenseStatistic(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
