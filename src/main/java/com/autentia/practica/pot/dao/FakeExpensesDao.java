package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository("fakeExpensesDao")
public class FakeExpensesDao implements ExpenseDao {
    private final List<Expense> expensesDB = new ArrayList<>();

    @Override
    public void insertExpense(Expense expense) {
        expensesDB.add(expense);
    }
    @Override
    public List<Expense> getAllExpenses() {
        return expensesDB;
    }

}
