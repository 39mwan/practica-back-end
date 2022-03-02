package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest   //Inicia el contexto para inyectar las dependencias
class SQLFriendDaoIT {   //Nomenclatura -IT para tests de integracion

    @Autowired
    FriendDao friendDao;

    Friend luis = new Friend("Luis", "Merino");
    Friend expectedFriend = new Friend("Luis", "Merino");

    List<Friend> friendList = List.of(expectedFriend);

    @Test
    void insertFriend() {
       Friend sonia = new Friend("Sonia", "Zhang");
       friendDao.insertFriend(sonia);
        List<Friend> friendList = friendDao.getFriends();
        assertTrue(friendList.contains(sonia));
    }

   @Test
    void getFriends() {
        assertEquals(friendList, friendDao.getFriends());
        //System.out.println(friendDao.getFriends());
    }
}
