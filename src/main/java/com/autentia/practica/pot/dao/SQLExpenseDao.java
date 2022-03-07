package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository("SQLExpenseDao")
public class SQLExpenseDao implements ExpenseDao{

    @Autowired
    FriendDao friendDao;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLExpenseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Expense> getAllExpenses() {
        final String sql = "SELECT * FROM expenses ORDER BY idFriend";

        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            Expense expense = new Expense();
            expense.setIdFriend(UUID.fromString(resultSet.getString("idFriend")));
            expense.setAmount(resultSet.getBigDecimal("amount"));
            expense.setDescription(resultSet.getString("description"));
            expense.setDate(resultSet.getTimestamp("date").toLocalDateTime());
            return expense;
        });
    }

    @Override
    public void addExpense(Expense expense) {
        final String sql = "INSERT INTO expenses VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, expense.getIdFriend().toString(), expense.getAmount(), expense.getDescription(), expense.getDate());
    }
}
