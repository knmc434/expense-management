package com.cg.expense.management.controller;

import com.cg.expense.management.domain.ExpenseDto;
import com.cg.expense.management.repository.entity.Expense;
import com.cg.expense.management.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping
    public ExpenseDto createExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        return expenseService.saveExpense(expenseDto);
    }

    @GetMapping("/{userName}")
    public List<ExpenseDto> getExpense(@PathVariable String userName) {
        return expenseService.getExpenses(userName);
    }

    @PutMapping
    public ExpenseDto updateExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        return expenseService.updateExpense(expenseDto);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }
}
