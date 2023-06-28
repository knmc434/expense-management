package com.cg.expense.management.repository;

import com.cg.expense.management.domain.ExpenseDto;
import com.cg.expense.management.repository.entity.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findAllByUserName(String userName);

}
