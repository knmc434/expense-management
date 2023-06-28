package com.cg.expense.management.service;

import com.cg.expense.management.domain.ExpenseDto;
import com.cg.expense.management.repository.ExpenseRepository;
import com.cg.expense.management.repository.entity.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public List<ExpenseDto> getExpenses(String userName) {
        List<Expense> expenses = expenseRepository.findAllByUserName(userName);
        return expenses.stream().map(this::expenseDto).collect(Collectors.toList());
    }

    public ExpenseDto saveExpense(ExpenseDto expenseDto) {
        Expense expense = expense(expenseDto);
        return expenseDto(expenseRepository.save(expense));
    }

    public void deleteExpense(long expenseId) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isPresent()) {
            expenseRepository.delete(expenseOptional.get());
        }
    }

    private ExpenseDto expenseDto(Expense expense) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(expense, ExpenseDto.class);
    }

    private Expense expense(ExpenseDto expenseDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(expenseDto, Expense.class);
    }

    public ExpenseDto updateExpense(ExpenseDto expenseDto) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseDto.getExpenseId());
        if (!expenseOptional.isPresent()) {
            throw new RuntimeException("Expense Not present");
        }
        return expenseDto(expenseRepository.save(expense(expenseDto)));
    }
}
