package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddFriendUseCase;
import com.autentia.practica.pot.application.GetAllFriendsUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/friend")
@RestController
public class FriendController {
    private final AddFriendUseCase addFriendUseCase;
    private final GetAllFriendsUseCase getAllFriendsUseCase;

    @Autowired
    public FriendController(AddFriendUseCase addFriendUseCase, GetAllFriendsUseCase getAllFriendsUseCase) {
        this.addFriendUseCase = addFriendUseCase;
        this.getAllFriendsUseCase = getAllFriendsUseCase;
    }

    @PostMapping
    public void addFriend(@RequestBody Friend friend){
        addFriendUseCase.addFriendUseCase(friend);
    }

    @GetMapping
    public List<Friend> getAllFriends() {
        return getAllFriendsUseCase.getAllFriendsUseCase();
    }
}
