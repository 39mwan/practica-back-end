package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddFriendUseCase;
import com.autentia.practica.pot.model.Friend;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FriendController.class)
class FriendControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AddFriendUseCase addFriendUseCase;

    private Friend friend;

    @BeforeEach
    public void setUp(){
        friend = new Friend("Sonia", "Zhang");
    }

    @Test
    void addFriend() throws  Exception{
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/friend")
                .content(objectMapper.writeValueAsString(friend))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }
}