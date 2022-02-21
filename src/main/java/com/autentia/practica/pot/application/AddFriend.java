package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Friend;

public class AddFriend {

   private final FriendDao friendDao;

    public AddFriend(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public boolean addFriendUseCase(Friend friend){
        return friendDao.insertFriend(friend);
    }

}
