package com.autentia.practica.pot.service;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class ExpenseService {

    public HashMap<Friend, BigDecimal> calculateIfEmptyExpenses(List<Friend> friends) {
        HashMap<Friend, BigDecimal> balance = new HashMap<>();
        for (Friend friend : friends) {
            balance.put(friend, BigDecimal.valueOf(0));
        }
        return balance;
    }

    public HashMap<Friend, BigDecimal> calculate(List<Expense> expenses) {

        HashMap<Friend, BigDecimal> balance = new HashMap<>();
        BigDecimal friendAmount;
        BigDecimal totalExpenses = new BigDecimal(0);
        int totalFriends;

        for (Expense expense : expenses) {
            Friend friend = expense.getFriend();
            if (!balance.containsKey(friend))
                balance.put(friend, expense.getAmount());
            else {
                friendAmount = balance.get(friend);
                friendAmount = friendAmount.add(expense.getAmount());
                balance.put(friend, friendAmount);
            }
            totalExpenses = totalExpenses.add(expense.getAmount());
        }
        totalFriends = balance.size();

            for (Friend clave : balance.keySet()) {
                BigDecimal pagosTotales = balance.get(clave);
                BigDecimal totalBalance = pagosTotales.subtract(totalExpenses.divide(BigDecimal.valueOf(totalFriends))); // balanceTotal = pagosTotales - (gastosTotales / totalFriends)

                balance.put(clave, totalBalance);

        }

        return balance;
    }

}
