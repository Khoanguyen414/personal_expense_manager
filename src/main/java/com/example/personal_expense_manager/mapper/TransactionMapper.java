package com.example.personal_expense_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.personal_expense_manager.dto.request.TransactionRequest;
import com.example.personal_expense_manager.dto.response.TransactionResponse;
import com.example.personal_expense_manager.entity.Transaction;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "id", ignore = true)
    Transaction toTransaction(TransactionRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    TransactionResponse toTransactionResponse(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateTransaction(TransactionRequest request, @MappingTarget Transaction transaction);
}
