package com.autentia.practica.pot.api;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RequestMapping("api/v1/expenses")
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public void insertExpense (@RequestBody Expense expense){
        //expenseService.insertExpense(expense);
    }

/*
    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseService.getExpenses();
    }
*/

  /*  @GetMapping
    public HashMap<Friend, BigDecimal> getBalance(){
        return this.expenseService.calculate();
    }
*/
}
