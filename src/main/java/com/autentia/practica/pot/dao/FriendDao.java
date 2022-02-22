package com.autentia.practica.pot.dao;

import com.autentia.practica.pot.model.Friend;

import java.util.List;

public interface FriendDao {
    void insertFriend (Friend friend);
    List<Friend> getFriends();
}
