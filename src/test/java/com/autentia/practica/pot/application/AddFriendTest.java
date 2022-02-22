package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.FakeFriendDao;
import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddFriendTest {

    private FriendDao friendDaoMock;

    @BeforeEach
    public void setUp(){
        friendDaoMock = mock(FakeFriendDao.class);
    }

    @Test
    public void shouldAddAValidFriend(){
        Friend newFriend = new Friend("Luis", "Merino");
        AddFriend addFriend = new AddFriend(friendDaoMock);

        addFriend.addFriendUseCase(newFriend);
        verify(friendDaoMock).insertFriend(newFriend);

    }

}




















