package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeFriendRepo")
public class FakeFriendDao implements FriendDao {
    private final List<Friend> DB = new ArrayList<>();

    @Override
    public void insertFriend(Friend friend) {
        DB.add(friend);
    }

    public List<Friend> getFriends() {
        return DB;
    }
}
