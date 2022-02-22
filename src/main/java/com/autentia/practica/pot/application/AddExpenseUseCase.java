package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;

public class AddExpenseUseCase {
    private final ExpenseDao expenseDao;

    public AddExpenseUseCase(ExpenseDao expenseDao){
        this.expenseDao = expenseDao;
    }

    public void addExpense (Expense expense){
        expenseDao.addExpense(expense);
    }
}

