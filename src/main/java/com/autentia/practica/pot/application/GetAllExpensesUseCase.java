package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.model.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("GetAllExpensesUseCase")
public class GetAllExpensesUseCase {
    ExpenseDao expenseDao;

    public GetAllExpensesUseCase (ExpenseDao expenseDao){
        this.expenseDao = expenseDao;
    }

    public List<Expense> getAllExpensesUseCase (){
        return expenseDao.getAllExpenses();
    }
}
