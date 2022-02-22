package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddFriendUseCase;
import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/friend")
@RestController
public class FriendController {
    private final AddFriendUseCase addFriendUseCase;

    @Autowired
    public FriendController(AddFriendUseCase addFriendUseCase) {
        this.addFriendUseCase = addFriendUseCase;
    }

    @PostMapping
    public void addFriend(@RequestBody Friend friend){
        addFriendUseCase.addFriendUseCase(friend);
    }
}
