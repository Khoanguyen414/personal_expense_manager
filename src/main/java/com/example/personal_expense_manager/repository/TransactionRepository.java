package com.example.personal_expense_manager.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.personal_expense_manager.dto.response.ExpenseStatResponse;
import com.example.personal_expense_manager.dto.response.TimeStatResponse;
import com.example.personal_expense_manager.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query("SELECT new com.example.personal_expense_manager.dto.response.ExpenseStatResponse(" +
        "SUM(t.amount), t.type, c.id, c.name) " +
        "FROM Transaction t JOIN t.category c " +
        "WHERE t.date BETWEEN :startDate AND :endDate " +
        "GROUP BY c.id, c.name, t.type"
    )
    List<ExpenseStatResponse> getExpenseStatistic(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.personal_expense_manager.dto.response.TimeStatResponse(" +
        "CAST(t.date AS string), " +
        "t.type, " +
        "SUM(t.amount)) " +
        "FROM Transaction t " +
        "WHERE t.date BETWEEN :startDate AND :endDate " +
        "GROUP BY t.date, t.type " +
        "ORDER BY t.date ASC"
    ) 
    List<TimeStatResponse> getDailyStatistic(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT new com.example.personal_expense_manager.dto.response.TimeStatResponse(" +
        "SUBSTRING(CAST(t.date AS string), 1, 7), " + 
        "t.type, " +
        "SUM(t.amount)) " +
        "FROM Transaction t " +
        "WHERE t.date BETWEEN :startDate AND :endDate " +
        "GROUP BY SUBSTRING(CAST(t.date AS string), 1, 7), t.type " +
        "ORDER BY SUBSTRING(CAST(t.date AS string), 1, 7) ASC"
    )
    List<TimeStatResponse> getMonthlyStatistic(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT new com.example.personal_expense_manager.dto.response.TimeStatResponse(" +
        "SUBSTRING(CAST(t.date AS string), 1, 4), " + 
        "t.type, " +
        "SUM(t.amount)) " +
        "FROM Transaction t " +
        "WHERE t.date BETWEEN :startDate AND :endDate " +
        "GROUP BY SUBSTRING(CAST(t.date AS string), 1, 4), t.type " +
        "ORDER BY SUBSTRING(CAST(t.date AS string), 1, 4) ASC"
    )
    List<TimeStatResponse> getYearlyStatistic(
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate
    );
}


