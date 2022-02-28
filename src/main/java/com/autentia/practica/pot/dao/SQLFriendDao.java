package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class SQLFriendDao implements FriendDao{

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public void insertFriend(Friend friend){

    }

    @Override
    public List<Friend> getFriends() {
        final String sql = "select * from friends";
        return jdbcTemplate.query(sql,(resultset,i)-> {
            UUID id = UUID.fromString(resultset.getString("id"));
            String name = resultset.getString("name");
            String surname = resultset.getString("surname");
            return new Friend(name,surname);
        });
    }
}
