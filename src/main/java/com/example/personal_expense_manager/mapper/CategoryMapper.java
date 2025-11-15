package com.example.personal_expense_manager.mapper;

import org.mapstruct.Mapper;

import com.example.personal_expense_manager.dto.request.CategoryRequest;
import com.example.personal_expense_manager.dto.response.CategoryResponse;
import com.example.personal_expense_manager.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
}
