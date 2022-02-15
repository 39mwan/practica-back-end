package com.example.demo.service;

import com.example.demo.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpensesManagement {
    private static ExpensesManagement single_instance = null;

    private List<Expense> expenses;

    //Singleton
    private ExpensesManagement() {
        this.expenses = new ArrayList<>();
    }

    public static ExpensesManagement getInstance() {
        if (single_instance == null) single_instance = new ExpensesManagement();

        return single_instance;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
