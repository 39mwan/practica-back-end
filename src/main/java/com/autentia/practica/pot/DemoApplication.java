package com.autentia.practica.pot;

import com.autentia.practica.pot.application.AddFriendUseCase;
import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Friend;
import com.mysql.cj.protocol.FullReadInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

}
