package com.autentia.practica.pot.api;

import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/expenses")
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public void addExpense(@RequestBody Expense expense){
        //expenseService.addExpense(expense);
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
