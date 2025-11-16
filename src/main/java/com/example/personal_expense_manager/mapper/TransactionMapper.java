package com.example.personal_expense_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.personal_expense_manager.dto.request.TransactionRequest;
import com.example.personal_expense_manager.dto.response.TransactionResponse;
import com.example.personal_expense_manager.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "id", ignore = true)
    Transaction toTransaction(TransactionRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    TransactionResponse toTransactionResponse(Transaction transaction);
}
