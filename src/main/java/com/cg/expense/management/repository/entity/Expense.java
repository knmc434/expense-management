package com.cg.expense.management.repository.entity;

import com.cg.expense.management.domain.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long expenseId;

    @NotEmpty(message="Expense name cannot be empty")
    private String expenseName;

    @Min(1)
    private double amount;

    private Category category;


    @Past(message="Date cannot be in the future")
    private Date createdDate;

    @Size(min=3, max=200, message = "comment size should be between {min} and {max}")
    private String comments;

    private String userName;
}
