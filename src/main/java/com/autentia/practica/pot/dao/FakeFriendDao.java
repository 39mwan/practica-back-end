package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fakeFriendRepo")
public class FakeFriendDao implements FriendDao {
    private final List<Friend> DB = new ArrayList<>();

    @Override
    public boolean insertFriend(Friend friend) {
        boolean success = true;
        if (!DB.contains(friend))
            DB.add(friend);
        else
            success = false;
        return success;
    }

    @Override
    public List<Friend> getFriends() {
        return DB;
    }
}
