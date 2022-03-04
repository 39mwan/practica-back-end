package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //Friend friend = friendDao.getFriends().indexOf(luis);


    Expense taxiExpense;
    List<Expense> expenseList;
    List<Friend> luisID;

    @BeforeEach
    void setUp() {
        friendDao.insertFriend(new Friend("Pepe", "merino"));
        luisID = friendDao.getFriends().stream()
                .filter(friend -> friend.getName().equals("Pepe"))
                .collect(Collectors.toList());
        luisID.forEach(friend -> System.out.println(" ********** " + luisID.get(0).getName() + " "
                                                    + luisID.get(0).getId() + " " + luisID.get(0).getSurname()+ "**********" ));

        taxiExpense = new Expense(luisID.get(0).getId(), BigDecimal.valueOf(32.1), "Mi taxi", LocalDateTime.now());
        expenseList = List.of(taxiExpense);
    }

    @Test
    @Order(2)
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
    @Order(1)
    void addExpense() {
        expenseDao.addExpense(taxiExpense);
        assertTrue(expenseDao.getAllExpenses().contains(taxiExpense));
    }

}