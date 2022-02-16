package com.autentia.practica.pot.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Expense {
    private Friend friend;
    private BigDecimal amount; //bigDecimal
    private String description;
    private LocalDateTime date; //localDateTime


    public Expense(Friend friend, BigDecimal amount, String description, LocalDateTime date) {
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



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
