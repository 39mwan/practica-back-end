package com.example.demo.model;

import java.util.Date;

public class Expense {
    private Friend friend;
    private float amount;
    private String description;
    private Date date;

    public Expense(Friend friend, float amount, String description, Date date) {
        this.friend = friend;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
