package com.example.demo.dao;

import com.example.demo.model.Friend;

import java.util.ArrayList;
import java.util.List;

public class FakeFriendDao implements FriendDao {
private static List<Friend> DB = new ArrayList<>();

    @Override
    public void addFriend(Friend friend) {
        DB.add(friend);
    }
}
