package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SQLFriendDao implements FriendDao{
    JdbcTemplate jdbcTemplate = new JdbcTemplate()

    @Override
    public void insertFriend(Friend friend){

    }

    @Override
    public List<Friend> getFriends() {
        return null;
    }
}
