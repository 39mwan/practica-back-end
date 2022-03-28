package com.autentia.practica.pot.service;

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

class ExpenseServiceTest {

    private final HashMap<Friend, BigDecimal> balance = new HashMap<>();
    List<Expense> expenseList;
    List<Friend> friendsList;
    Friend luis;
    Friend sonia;

    @BeforeEach
    public void setUp() {
        expenseList = new ArrayList<>();
        friendsList = new ArrayList<>();
        luis = new Friend( "luis", "merino");
        sonia = new Friend( "sonia", "zhang");
        friendsList.add(luis);
        friendsList.add(sonia);
    }

    @Test
    public void shouldCalculateBalanceForTwo() {
        expenseList.add(new Expense(luis.getId(), BigDecimal.valueOf(20), "taxi", LocalDate.now()));
        expenseList.add(new Expense(sonia.getId(), BigDecimal.valueOf(10), "comida", LocalDate.now()));

        balance.put(luis, BigDecimal.valueOf(5)); // a luis le deben 5
        balance.put(sonia, BigDecimal.valueOf(-5)); // sonia debe 5

        HashMap<Friend, BigDecimal> calculatedBalance = new ExpenseService().calculate(expenseList, friendsList);
        System.out.println(calculatedBalance.values());
        assertTrue(balance.equals(calculatedBalance));
    }

    @Test
    public void shouldReturnBalanceForRepeatedFriends(){

        expenseList.add(new Expense(luis.getId(), BigDecimal.valueOf(20), "taxi", LocalDate.now()));
        expenseList.add(new Expense(sonia.getId(), BigDecimal.valueOf(10), "comida", LocalDate.now()));
        expenseList.add(new Expense(sonia.getId(), BigDecimal.valueOf(20), "comida", LocalDate.now()));

        HashMap<Friend, BigDecimal> calculatedBalanceExpected = new HashMap<>();
        calculatedBalanceExpected.put(luis, BigDecimal.valueOf(-5));
        calculatedBalanceExpected.put(sonia, BigDecimal.valueOf(5));

        HashMap<Friend, BigDecimal> calculatedBalance = new ExpenseService().calculate(expenseList, friendsList);
        System.out.println(calculatedBalance.values());
        assertEquals(calculatedBalanceExpected, calculatedBalance);
    }

    @Test
    public void shouldReturnBalanceForEmptyExpenses(){

        HashMap<Friend, BigDecimal> calculatedBalanceExpected = new HashMap<>();
        calculatedBalanceExpected.put(luis, BigDecimal.valueOf(0));
        calculatedBalanceExpected.put(sonia, BigDecimal.valueOf(0));
        HashMap<Friend, BigDecimal> calculatedBalance = new ExpenseService().calculate(expenseList, friendsList);

        assertEquals(calculatedBalanceExpected, calculatedBalance);
    }

}