package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //Inicia el contexto para inyectar las dependencias
class SQLExpenseDaoIT {

    @Autowired
    @Qualifier("SQLExpenseDao")
    ExpenseDao expenseDao;

    @Autowired
    @Qualifier("SQLFriendDao")
    FriendDao friendDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Friend luis = new Friend("Luis", "Merino");
    //Friend friend = friendDao.getFriends().indexOf(luis);


    Expense taxiExpense = new Expense(luis.getId(), BigDecimal.valueOf(32.1), "Mi taxi", LocalDateTime.now());
    List<Expense> expenseList = List.of(taxiExpense);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllExpenses() {
        final String sql = "SELECT * FROM expenses ORDER BY idFriend";
        List<Expense> actualExpenses = jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            Expense expense = new Expense();
            expense.setIdFriend(UUID.fromString(resultSet.getString("idFriend")));
            expense.setAmount(resultSet.getBigDecimal("amount"));
            expense.setDescription(resultSet.getString("description"));
//            expense.setDate(resultSet.("date"));
            return expense;
        });

        assertEquals(expenseList, actualExpenses);
    }

    @Test
    void addExpense() {
        expenseDao.addExpense(taxiExpense);
        assertTrue(expenseDao.getAllExpenses().contains(taxiExpense));
    }

}