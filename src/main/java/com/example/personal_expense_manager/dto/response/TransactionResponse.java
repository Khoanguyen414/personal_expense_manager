package com.example.personal_expense_manager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class TransactionResponse {
    int id;
    BigDecimal amount;
    String description;
    LocalDate date;
    TransactionType type;
    int categoryId;
    String categoryName;   
}
