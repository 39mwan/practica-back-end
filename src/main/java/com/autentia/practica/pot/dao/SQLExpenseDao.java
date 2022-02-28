package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SQLExpenseDao implements ExpenseDao{
    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public void addExpense(Expense expense) {

    }
}
