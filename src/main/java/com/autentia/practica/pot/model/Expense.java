package com.autentia.practica.pot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


public class Expense {
    private Friend friend;
    private BigDecimal amount; //bigDecimal
    private String description;
    private LocalDateTime date; //localDateTime


    public Expense(@JsonProperty("friend") Friend friend,@JsonProperty("amount") BigDecimal amount,
                   @JsonProperty("description") String description, @JsonProperty("date") LocalDateTime date) {
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
