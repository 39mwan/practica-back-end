package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;

import java.util.List;

public interface  ExpenseDao {
    List<Expense> getAllExpenses();
    void addExpense(Expense expense);
}


