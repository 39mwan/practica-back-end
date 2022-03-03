package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class AddExpenseUseCaseTest {
    private ExpenseDao expenseDaoMock;
    private AddExpenseUseCase addExpenseUseCase;
    Expense expense;
    Friend sonia;

    @BeforeEach
    public void setUp(){
        expenseDaoMock = mock(ExpenseDao.class);
        addExpenseUseCase = new AddExpenseUseCase(expenseDaoMock);
        sonia = new Friend("Sonia", "Zhang");
        expense = new Expense(sonia.getId(), BigDecimal.valueOf(20),"taxi", LocalDateTime.now());
    }

    @Test
    public void shouldAddExpense(){
        addExpenseUseCase.addExpense(expense);
        verify(expenseDaoMock).addExpense(expense);
    }
}