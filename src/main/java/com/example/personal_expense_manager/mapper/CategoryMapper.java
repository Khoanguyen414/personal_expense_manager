package com.example.personal_expense_manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.personal_expense_manager.dto.request.CategoryRequest;
import com.example.personal_expense_manager.dto.response.CategoryResponse;
import com.example.personal_expense_manager.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
