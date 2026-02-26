package com.example.personal_expense_manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.personal_expense_manager.dto.request.TransactionRequest;
import com.example.personal_expense_manager.dto.response.ExpenseStatResponse;
import com.example.personal_expense_manager.dto.response.TimeStatResponse;
import com.example.personal_expense_manager.dto.response.TransactionResponse;
import com.example.personal_expense_manager.service.TransactionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController {
    TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }
    
    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    
    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest request) {        
        return transactionService.updateTransaction(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/statistics")
    public List<ExpenseStatResponse> getExpenseStatistic(
        @RequestParam(required = false) LocalDate startDate, 
        @RequestParam(required = false) LocalDate endDate

    ) {
        return transactionService.getExpenseStatistic(startDate, endDate);
    }
    
    @GetMapping("/statistics/daily")
    public List<TimeStatResponse> getDailyStatistic(
            @RequestParam(required = false) LocalDate startDate, 
            @RequestParam(required = false) LocalDate endDate) {
        
        return transactionService.getDailyStatistic(startDate, endDate);
    }

    @GetMapping("/statistics/monthly")
    public List<TimeStatResponse> getMonthlyStatistic(
            @RequestParam(required = false) LocalDate startDate, 
            @RequestParam(required = false) LocalDate endDate) {
        return transactionService.getMonthlyStatistic(startDate, endDate);
    }

    @GetMapping("/statistics/yearly")
    public List<TimeStatResponse> getYearlyStatistic(
            @RequestParam(required = false) LocalDate startDate, 
            @RequestParam(required = false) LocalDate endDate) {
        return transactionService.getYearlyStatistic(startDate, endDate);
    }
}
