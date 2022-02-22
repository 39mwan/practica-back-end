package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//CalculateBalance Use case
//Functional Core, Imperative shell
//encapsulates business logic in core and a shell (this class) orchestrates DAO and Core
@Component("CalculateBalanceUseCase")
public class CalculateBalanceUseCase {
    private final ExpenseDao expenseDao;
    private final FriendDao friendDao;
    private final ExpenseService expenseService;

    public CalculateBalanceUseCase(@Qualifier("fakeExpensesDao") ExpenseDao expenseDao,
                                   @Qualifier("fakeFriendsDao") FriendDao friendDao,
                                   ExpenseService expenseService){
        this.expenseDao = expenseDao;
        this.expenseService = expenseService;
        this.friendDao = friendDao;
    }

    public HashMap<Friend, BigDecimal> calculateBalance() {
        List<Expense> expenseList = expenseDao.getAllExpenses();
        List<Friend> friendList = friendDao.getFriends();
        return expenseService.calculate(expenseList, friendList);
    }
}
