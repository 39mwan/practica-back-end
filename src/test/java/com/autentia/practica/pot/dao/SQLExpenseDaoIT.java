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
import java.util.UUID;
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


//    @Before
//    public void setUp() {
//        friendDao.insertFriend(new Friend("Pepe", "merino"));
//
//        luisID = friendDao.getFriends().stream()
//                .filter(friend -> friend.getName().equals("Pepe"))
//                .collect(Collectors.toList());
//
//        taxiExpense = new Expense(luisID.get(0).getId(), BigDecimal.valueOf(32.10), "Mi taxi", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
//        expectedList = List.of(taxiExpense);
////        luisID.forEach(friend -> System.out.println(" ********** " + luisID.get(0).getName() + " "
////                                                    + luisID.get(0).getId() + " " + luisID.get(0).getSurname()+ "**********" ));
//
//        //System.out.println("******* AMOUNT de " + taxiExpense.getIdFriend() +" " +  taxiExpense.getAmount());
//    }


    @Test
    @Order(1)
    void addExpense() {

        friendDao.insertFriend(new Friend("Pepe", "merino"));

        luisID = friendDao.getFriends().stream()
                .filter(friend -> friend.getName().equals("Pepe"))
                .collect(Collectors.toList());

        //Datos del caso de prueba
        UUID idLuis = luisID.get(0).getId();
        BigDecimal amount = new BigDecimal("32.10");
        String descripcion = "Mi taxi";
        LocalDateTime fecha = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        taxiExpense = new Expense(idLuis, amount, descripcion, fecha);
        expectedList = List.of(taxiExpense);

        expenseDao.addExpense(taxiExpense);

        String sql = "SELECT * FROM expenses WHERE idFriend = ? AND amount=? AND description = ? AND date = ?";
        Expense receivedExpense = jdbcTemplate.queryForObject(sql, (resultSet, rowNumber) -> {
            Expense expense = new Expense();
            expense.setIdFriend(UUID.fromString(resultSet.getString("idFriend")));
            expense.setAmount(resultSet.getBigDecimal("amount"));
            expense.setDescription(resultSet.getString("description"));
            expense.setDate(resultSet.getTimestamp("date").toLocalDateTime());
            return expense;
        }, idLuis.toString(), amount, descripcion, fecha);

        assertEquals(taxiExpense, receivedExpense);
    }

    @Test
    @Order(2)
    void getAllExpenses() {
        //Creacion de resultado esperado : expectedList
        luisID = friendDao.getFriends().stream()
                .filter(friend -> friend.getName().equals("Pepe"))
                .collect(Collectors.toList());

        taxiExpense = new Expense(luisID.get(0).getId(), new BigDecimal("32.10"), "Mi taxi", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        expectedList = List.of(taxiExpense);

//        System.out.println("\nLISTA ESPERADA ****************");
//        expectedList.forEach(expense -> System.out.println(expense.getIdFriend() + " ** "
//                + expense.getAmount() + " ** " +
//                expense.getDate() + " ** " +
//                expense.getDescription() + "\n"));
//        System.out.println("LISTA REAL DE BD ???????????????????????????????");
//        expenseDao.getAllExpenses().forEach(expense -> System.out.println(expense.getIdFriend() + " ** "
//                + expense.getAmount() + " ** " +
//                expense.getDate() + " ** " +
//                expense.getDescription() + "\n"));

        assertEquals(expectedList, expenseDao.getAllExpenses());
    }
}