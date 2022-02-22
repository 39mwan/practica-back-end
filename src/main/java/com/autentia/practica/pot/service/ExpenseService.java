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


    public HashMap<Friend, BigDecimal> calculate(List<Expense> expenses, List<Friend> friendList) {

        HashMap<Friend, BigDecimal> balance = new HashMap<>();
        BigDecimal friendAmount;
        BigDecimal totalExpenses = new BigDecimal(0);
        int totalFriends = friendList.size();

        if(!expenses.isEmpty()) {
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

            for (Friend clave : balance.keySet()) {
                BigDecimal pagosTotales = balance.get(clave);
                BigDecimal totalBalance = pagosTotales.subtract(totalExpenses.divide(BigDecimal.valueOf(totalFriends))); // balanceTotal = pagosTotales - (gastosTotales / totalFriends)

                balance.put(clave, totalBalance);

            }
        } else {
            for (Friend friend : friendList) {
                balance.put(friend, BigDecimal.valueOf(0));
            }
        }

        return balance;
    }

}
