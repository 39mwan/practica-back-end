package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Friend;

public class AddFriendUseCase {

   private final FriendDao friendDao;

    public AddFriendUseCase(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public void addFriendUseCase(Friend friend){
        friendDao.insertFriend(friend);
    }

}
