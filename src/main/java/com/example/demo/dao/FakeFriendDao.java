package com.example.demo.dao;

import com.example.demo.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeFriendRepo")
public class FakeFriendDao implements FriendDao {
private static List<Friend> DB = new ArrayList<>();

    @Override
    public void insertFriend(Friend friend) {
        DB.add(friend);
    }
}
