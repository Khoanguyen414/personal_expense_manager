package com.example.personal_expense_manager.dto.response;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceStatResponse {
    String timePeriod;
    BigDecimal totalIncome;
    BigDecimal totalExpense;
    BigDecimal balance;
}
