package com.autentia.practica.pot.service;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ExpenseServiceTest {

    HashMap<Friend, BigDecimal> balance;

    @BeforeEach
    public void setUp() {
        balance = new HashMap<>();
    }

    @Test
    public void shouldCalculateBalanceForTwo() {
        //Creacion de caso de prueba
        List<Expense> expenses = new ArrayList<>();
        Friend luis = new Friend( "luis", "merino");
        Friend sonia = new Friend( "sonia", "zhang");

        expenses.add(new Expense(luis, BigDecimal.valueOf(20), "taxi", LocalDateTime.now()));
        expenses.add(new Expense(sonia, BigDecimal.valueOf(10), "comida", LocalDateTime.now()));


        balance.put(luis, BigDecimal.valueOf(5)); // a luis le deben 5
        balance.put(sonia, BigDecimal.valueOf(-5)); // sonia debe 5

        HashMap<Friend, BigDecimal> calculatedBalance = new ExpenseService().calculate(expenses);
        System.out.println(calculatedBalance.values());
        assertTrue(balance.equals(calculatedBalance));
    }

}