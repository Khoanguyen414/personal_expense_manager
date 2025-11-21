package com.example.personal_expense_manager.dto.response;

import java.math.BigDecimal;

import com.example.personal_expense_manager.enums.TransactionType;

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
public class ExpenseStatResponse {
    BigDecimal amount;
    TransactionType type;
    Long categoryId;
    String categoryName; 
}
