package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //Inicia el contexto para inyectar las dependencias
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SQLExpenseDaoIT {

    @Autowired
    @Qualifier("SQLExpenseDao")
    ExpenseDao expenseDao;

    @Autowired
    @Qualifier("SQLFriendDao")
    FriendDao friendDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Expense taxiExpense;
    List<Expense> expectedList;
    List<Friend> luisID;
    Friend luis = new Friend("Luis", "merino");


    @Test
    @Order(1)
    void addExpense() {
        friendDao.insertFriend(luis);

        BigDecimal amount = new BigDecimal("32.10");
        String descripcion = "Mi taxi";
        LocalDateTime fecha = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        int idLuis = getFriendId(luis);

        taxiExpense = createExpense(idLuis, amount, descripcion, fecha);

        expenseDao.addExpense(taxiExpense);

        String sql = "SELECT * FROM expenses WHERE friend_id = ? AND amount=? AND description = ? AND date = ?";
        Expense receivedExpense = jdbcTemplate.queryForObject(sql, (resultSet, rowNumber) -> {
            Expense expense = new Expense();
            expense.setIdFriend(resultSet.getInt("friend_id"));
            expense.setAmount(resultSet.getBigDecimal("amount"));
            expense.setDescription(resultSet.getString("description"));
            expense.setDate(resultSet.getTimestamp("date").toLocalDateTime());
            return expense;
        }, idLuis, amount, descripcion, fecha);

        assertEquals(taxiExpense, receivedExpense);
    }

    @Test
    @Order(2)
    void getAllExpenses() {
        //Creacion de resultado esperado : expectedList
        int luisID =  getFriendId(luis);

        taxiExpense = createExpense(luisID, new BigDecimal("32.10"), "Mi taxi", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        expectedList = List.of(taxiExpense);

        assertEquals(expectedList, expenseDao.getAllExpenses());
    }

    private Expense createExpense(int friendID, BigDecimal amount, String description, LocalDateTime date){
        return new Expense(friendID, amount, description, date);
    }


    private int getFriendId(Friend friend) {
        List<Friend> friendId = friendDao.getFriends().stream()
                .filter(eachFriend -> eachFriend.equals(friend))
                .collect(Collectors.toList());

       return friendId.get(0).getId();
    }
















}