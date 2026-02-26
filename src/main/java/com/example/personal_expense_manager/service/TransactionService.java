package com.example.personal_expense_manager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.personal_expense_manager.dto.request.TransactionRequest;
import com.example.personal_expense_manager.dto.response.ExpenseStatResponse;
import com.example.personal_expense_manager.dto.response.TimeStatResponse;
import com.example.personal_expense_manager.dto.response.TransactionResponse;
import com.example.personal_expense_manager.entity.Category;
import com.example.personal_expense_manager.entity.Transaction;
import com.example.personal_expense_manager.exception.ResourceNotFoundException;
import com.example.personal_expense_manager.mapper.TransactionMapper;
import com.example.personal_expense_manager.repository.CategoryRepository;
import com.example.personal_expense_manager.repository.TransactionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionService {
    TransactionRepository transactionRepository;
    CategoryRepository categoryRepository;
    TransactionMapper transactionMapper;

    public TransactionResponse createTransaction(TransactionRequest request) {
        Long categoryId = request.getCategoryId();
        Category category = categoryRepository.findById(categoryId)    
            .orElseThrow(() -> new ResourceNotFoundException("Categpry not found with id: " + categoryId));

        Transaction transaction = transactionMapper.toTransaction(request);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(savedTransaction);
    }

    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return transactionMapper.toTransactionResponse(transaction);
    }

    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
            .map(transactionMapper::toTransactionResponse)
            .toList();
    }

    public TransactionResponse updateTransaction(Long id, TransactionRequest request) {
        Transaction existingTransaction = transactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        
        existingTransaction.setAmount(request.getAmount());
        existingTransaction.setDescription(request.getDescription());
        existingTransaction.setDate(request.getDate());
        existingTransaction.setType(request.getType());

        Long newCategoryId = request.getCategoryId();
        if (newCategoryId != null && !newCategoryId.equals(existingTransaction.getCategory().getId())) {
            Category newCategory = categoryRepository.findById(newCategoryId)    
                .orElseThrow(() -> new ResourceNotFoundException("Categpry not found with id: " + newCategoryId));
            existingTransaction.setCategory(newCategory);
        }

        Transaction updatedTransaction = transactionRepository.save(existingTransaction);

        return transactionMapper.toTransactionResponse(updatedTransaction);
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(transaction);
    }

    public List<ExpenseStatResponse> getExpenseStatistic(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.now().withDayOfMonth(1);
        }

        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return transactionRepository.getExpenseStatistic(startDate, endDate);
    }

    public List<TimeStatResponse> getDailyStatistic(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            startDate = LocalDate.now().withDayOfMonth(1);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }
        return transactionRepository.getDailyStatistic(startDate, endDate);
    }

    public List<TimeStatResponse> getMonthlyStatistic(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) startDate = LocalDate.now().withDayOfYear(1); 
        if (endDate == null) endDate = LocalDate.now();
        return transactionRepository.getMonthlyStatistic(startDate, endDate);
    }

    public List<TimeStatResponse> getYearlyStatistic(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) startDate = LocalDate.now().minusYears(5).withDayOfYear(1); 
        if (endDate == null) endDate = LocalDate.now();
        return transactionRepository.getYearlyStatistic(startDate, endDate);
    }
}
