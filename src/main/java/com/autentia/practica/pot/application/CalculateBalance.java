package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FakeExpensesDao;
import com.autentia.practica.pot.dao.FriendDao;
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
    private final FriendDao friendDao;
    private final ExpenseService expenseService;

    public CalculateBalance (ExpenseDao expenseDao, FriendDao friendDao, ExpenseService expenseService){
        this.expenseDao = expenseDao;
        this.expenseService = expenseService;
        this.friendDao = friendDao;
    }

    public HashMap<Friend, BigDecimal> calculateBalance() {
        List<Expense> expenseList = expenseDao.getAllExpenses();
        if(!expenseList.isEmpty())
            return expenseService.calculate(expenseList);
        else
            return expenseService.calculateIfEmptyExpenses(friendDao.getFriends());
    }
}
