package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLFriendDaoTest {

    @Autowired
    FriendDao friendDao;

    Friend luis = new Friend("Luis", "Merino");
    Friend expectedFriend = new Friend("Luis", "Merino");

    List<Friend> friendList = List.of(expectedFriend);

    @Test
    void insertFriend() {
       //assertDoesNotThrow(Exception.class, friendDao.insertFriend(luis)); //hacer que devuelva excepcion si no se inserta
    }

    @Test
    void getFriends() {
        assertEquals(friendList, friendDao.getFriends());
    }
}