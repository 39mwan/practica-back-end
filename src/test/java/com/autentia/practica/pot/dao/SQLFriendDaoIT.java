package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //Inicia el contexto para inyectar las dependencias
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SQLFriendDaoIT {   //Nomenclatura -IT para tests de integracion

    @Autowired
    FriendDao friendDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Friend sonia = new Friend("Sonia", "Zhang");
    List<Friend> friendList = List.of(sonia);

    @Test
    @Order(1)
    void insertFriend() {
        friendDao.insertFriend(sonia);

        /*   List<Friend> expectedList = jdbcTemplate.query("SELECT * from friends WHERE name ='Sonia' AND surname='Zhang'", (resultSet, rowNumber) -> {
            Friend friend = new Friend();
            friend.setId(UUID.fromString(resultSet.getString("id")));
            friend.setName(resultSet.getString("name"));
            friend.setSurname(resultSet.getString("surname"));
            return friend;
        });*/

        String sql = "SELECT * from friends WHERE name ='Sonia' AND surname='Zhang'";
        Friend actualFriend = jdbcTemplate.queryForObject(sql,(resultSet, rowNumber) -> {
            Friend friend = new Friend();
            friend.setId(UUID.fromString(resultSet.getString("id")));
            friend.setName(resultSet.getString("name"));
            friend.setSurname(resultSet.getString("surname"));
            return friend;});

        assertEquals(sonia, actualFriend);
        // System.out.println(actualFriend.getName() + " " + actualFriend.getSurname());
    }

    @Test
    @Order(2)
    void getFriends() {
        assertEquals(friendList, friendDao.getFriends());
    }
}
