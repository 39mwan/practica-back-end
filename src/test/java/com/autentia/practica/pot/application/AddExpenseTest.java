package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class AddExpenseTest {
    private ExpenseDao expenseDaoMock;
    private AddExpense addExpense;
    Expense expense;
    Friend sonia;

    @BeforeEach
    public void setUp(){
        expenseDaoMock = mock(ExpenseDao.class);
        addExpense = new AddExpense(expenseDaoMock);
        sonia = new Friend("Sonia", "Zhang");
        expense = new Expense(sonia, BigDecimal.valueOf(20),"taxi", LocalDateTime.now());
    }

    @Test
    public void shouldAddExpense(){
        addExpense.addExpense(expense);
        verify(expenseDaoMock).addExpense(expense);
    }
}