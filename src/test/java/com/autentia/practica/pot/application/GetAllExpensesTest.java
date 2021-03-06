package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllExpensesTest {
    private ExpenseDao expenseDaoMock;
    private List<Expense> expenseList;
    private Friend luis;
    private Friend sonia;
    private GetAllExpensesUseCase getAllExpensesUseCase;

    @BeforeEach
    public void test() {
        expenseDaoMock = mock(ExpenseDao.class);
        getAllExpensesUseCase = new GetAllExpensesUseCase(expenseDaoMock);
    }

    @Test
    public void shouldReturnAllExpenses() {
        luis = new Friend("luis", "merino");
        sonia = new Friend("sonia", "zhang");
        expenseList = new ArrayList<>();
        expenseList.add(new Expense(luis.getId(), BigDecimal.valueOf(20), "taxi", LocalDate.now()));
        expenseList.add(new Expense(sonia.getId(), BigDecimal.valueOf(10), "comida", LocalDate.now()));
        when(expenseDaoMock.getAllExpenses()).thenReturn(expenseList);
        assertEquals(expenseList, getAllExpensesUseCase.getAllExpensesUseCase());

    }

}