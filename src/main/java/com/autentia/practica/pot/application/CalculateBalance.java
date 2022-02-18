package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FakeExpensesDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class CalculateBalance {

    private static final ExpenseDao expenseDao = new FakeExpensesDao();
    private static final ExpenseService expenseService = new ExpenseService();

    public static HashMap<Friend, BigDecimal> calculateBalance() {
        List<Expense> expenseList = expenseDao.getAllExpenses();
        return expenseService.calculate(expenseList);
    }
}
