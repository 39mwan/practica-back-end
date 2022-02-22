package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddExpenseUseCase;
import com.autentia.practica.pot.application.GetAllExpensesUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/expenses")
@RestController
public class ExpenseController {
    private final AddExpenseUseCase addExpenseUseCase;
    private final GetAllExpensesUseCase getAllExpensesUseCase;

    @Autowired
    public ExpenseController(AddExpenseUseCase addExpenseUseCase, GetAllExpensesUseCase getAllExpensesUseCase) {
        this.addExpenseUseCase = addExpenseUseCase;
        this.getAllExpensesUseCase = getAllExpensesUseCase;
    }

    @PostMapping
    public void addExpense(@RequestBody Expense expense) {
        addExpenseUseCase.addExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return getAllExpensesUseCase.getAllExpensesUseCase();
    }


  /*  @GetMapping
    public HashMap<Friend, BigDecimal> getBalance(){
        return this.expenseService.calculate();
    }
*/
}
