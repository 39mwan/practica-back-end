package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
        return null;
    }

    @Override
    public void addExpense(Expense expense) {
        final String sql = "INSERT INTO expenses VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, expense.getIdFriend(), expense.getAmount(), expense.getDescription(), expense.getDate());
    }
}
