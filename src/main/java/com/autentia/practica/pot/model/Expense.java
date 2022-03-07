package com.autentia.practica.pot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class Expense {

    private UUID idFriend;
    private BigDecimal amount; //bigDecimal
    private String description;
    private LocalDateTime date; //localDateTime

    public Expense() {
    }

    public Expense(@JsonProperty("friend") UUID idFriend, @JsonProperty("amount") BigDecimal amount,
                   @JsonProperty("description") String description, @JsonProperty("date") LocalDateTime date) {
        this.idFriend = idFriend;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public UUID getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(UUID idFriend) {
        this.idFriend = idFriend;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return idFriend.equals(expense.idFriend) && amount.equals(expense.amount) && description.equals(expense.description) && date.equals(expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFriend, amount, description, date);
    }
}
