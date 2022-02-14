package com.example.demo.model;

import java.util.UUID;

public class Friend {
    private UUID id;
    private String name;
    private String surname;
    private int balance;

    public Friend(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
