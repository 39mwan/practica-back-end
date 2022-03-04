package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository("SQLFriendDao")
public class SQLFriendDao implements FriendDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SQLFriendDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertFriend(Friend friend) {
        final String sql = "INSERT INTO friends VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, friend.getId().toString(), friend.getName(), friend.getSurname());
    }

    @Override
    public List<Friend> getFriends() {
        final String sql = "SELECT * FROM friends ORDER BY name";
        return jdbcTemplate.query(sql, (resultSet, rowNumber) -> {
            Friend friend = new Friend();
            friend.setId(UUID.fromString(resultSet.getString("id")));
            friend.setName(resultSet.getString("name"));
            friend.setSurname(resultSet.getString("surname"));
            return friend;
        });
    }
}
