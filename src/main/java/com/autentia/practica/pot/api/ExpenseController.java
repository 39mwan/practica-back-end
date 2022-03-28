package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddExpenseUseCase;
import com.autentia.practica.pot.application.CalculateBalanceUseCase;
import com.autentia.practica.pot.application.GetAllExpensesUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/expenses")
@RestController
public class ExpenseController {
    private final AddExpenseUseCase addExpenseUseCase;
    private final GetAllExpensesUseCase getAllExpensesUseCase;
    private final CalculateBalanceUseCase calculateBalanceUseCase;

    @Autowired
    public ExpenseController(AddExpenseUseCase addExpenseUseCase, GetAllExpensesUseCase getAllExpensesUseCase,
                             CalculateBalanceUseCase calculateBalanceUseCase) {
        this.addExpenseUseCase = addExpenseUseCase;
        this.getAllExpensesUseCase = getAllExpensesUseCase;
        this.calculateBalanceUseCase = calculateBalanceUseCase;
    }

    @PostMapping
    public void addExpense(@RequestBody Expense expense) {
        addExpenseUseCase.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return getAllExpensesUseCase.getAllExpensesUseCase();
    }

    @RequestMapping("/balance")
    @GetMapping
    public HashMap<Friend, BigDecimal> getBalance(){
        return calculateBalanceUseCase.calculateBalance();
    }
}
