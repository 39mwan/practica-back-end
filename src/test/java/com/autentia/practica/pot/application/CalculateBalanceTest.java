package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FakeExpensesDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateBalanceTest {
    private ExpenseDao expenseDaoMock;
    private ExpenseService balanceService;

    @BeforeEach
    public void setUp() {
        expenseDaoMock = mock(ExpenseDao.class);
        balanceService = new ExpenseService();
    }


    @Test
    public void shouldReturnBalance() {
        //Creation of "expenses" = a MOCK DB of ExpenseDao
        List<Expense> expenses = new ArrayList<>();
        Friend luis = new Friend("luis", "merino");
        Friend sonia = new Friend("sonia", "zhang");

        expenses.add(new Expense(luis, BigDecimal.valueOf(20), "taxi", LocalDateTime.now()));
        expenses.add(new Expense(sonia, BigDecimal.valueOf(10), "comida", LocalDateTime.now()));

        //simulation of ExpenseDao functionality: expenseDaoMock returns MOCK DB of ExpenseDao
        when(expenseDaoMock.getAllExpenses()).thenReturn(expenses);

        CalculateBalance calculateBalance = new CalculateBalance(expenseDaoMock, balanceService);


        HashMap<Friend, BigDecimal> calculatedBalanceExpected = new HashMap<>();
        calculatedBalanceExpected.put(luis, BigDecimal.valueOf(5));
        calculatedBalanceExpected.put(sonia, BigDecimal.valueOf(-5));

        assertEquals(calculatedBalanceExpected, calculateBalance.calculateBalance());
        System.out.println("expected balance: " + calculatedBalanceExpected + "\nactual balance:  " + calculateBalance.calculateBalance());
    }
}
