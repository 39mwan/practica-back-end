package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        final String sql = "SELECT * FROM expenses ORDER BY friend_id";

        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            Expense expense = new Expense();
            expense.setIdFriend(resultSet.getInt("friend_id"));
            expense.setAmount(resultSet.getBigDecimal("amount"));
            expense.setDescription(resultSet.getString("description"));
            expense.setDate(resultSet.getTimestamp("date").toLocalDateTime());
            return expense;
        });
    }

    @Override
    public void addExpense(Expense expense) {
        final String sql = "INSERT INTO expenses VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, expense.getIdFriend(), expense.getAmount(), expense.getDescription(), expense.getDate());
    }
}
