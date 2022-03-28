package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("GetAllFriendsUseCase")
public class GetAllFriendsUseCase {
    private final FriendDao friendDao;

    public GetAllFriendsUseCase (FriendDao friendDao){
        this.friendDao = friendDao;
    }

    public List<Friend> getAllFriendsUseCase (){
        return friendDao.getFriends();
    }

}
