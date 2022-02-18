package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FakeExpensesDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

//CalculateBalance Use case
//Functional Core, Imperative shell
//encapsulates business logic in core and a shell (this class) orchestrates DAO and Core
public class CalculateBalance {

    private final ExpenseDao expenseDao;
    private final ExpenseService expenseService;

    public CalculateBalance (ExpenseDao expenseDao, ExpenseService expenseService){
        this.expenseDao = expenseDao;
        this.expenseService = expenseService;
    }

    public HashMap<Friend, BigDecimal> calculateBalance() {
        List<Expense> expenseList = expenseDao.getAllExpenses();
        return expenseService.calculate(expenseList);
    }
}
