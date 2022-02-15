package com.example.demo.service;

import com.example.demo.dao.FriendDao;
import com.example.demo.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private final FriendDao friendDao;

    //dependency injection
    @Autowired
    public FriendService(@Qualifier("fakeFriendRepo") FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public void addFriend(Friend friend){
        friendDao.insertFriend(friend);
    }
}
