package com.cg.expense.management.domain;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ExpenseDto {

    private long expenseId;

    @NotEmpty(message="Expense name cannot be empty")
    private String expenseName;

    @Min(1)
    private double amount;

    private Category category;

    @Past(message="Date cannot be in the future")
    private LocalDate createdDate;

    @Size(min=3, max=200, message = "comment size should be between {min} and {max}")
    private String comments;
}
