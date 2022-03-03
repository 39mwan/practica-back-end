package com.autentia.practica.pot.api;

import com.autentia.practica.pot.application.AddExpenseUseCase;
import com.autentia.practica.pot.application.CalculateBalanceUseCase;
import com.autentia.practica.pot.application.GetAllExpensesUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AddExpenseUseCase addExpenseUseCase;

    @MockBean
    private GetAllExpensesUseCase getAllExpensesUseCase;

    @MockBean
    private CalculateBalanceUseCase calculateBalanceUseCase;

    private Expense expense;
    private List<Expense> expenselist;

    @BeforeEach
    public void setUp(){
        Friend sonia = new Friend("Sonia", "Zhang");
        expense = new Expense(sonia.getId(), BigDecimal.valueOf(20),"Taxi", LocalDateTime.now());
        expenselist = new ArrayList<>();
        expenselist.add(expense);
    }

    @Test
    public void shouldAddExpense() throws Exception{

        mvc.perform(post("/api/v1/expenses")
                .content(objectMapper.writeValueAsString(expense))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllExpenses() throws Exception {

        when(getAllExpensesUseCase.getAllExpensesUseCase()).thenReturn(expenselist);
        String response = mvc.perform(get("/api/v1/expenses").contentType(MediaType.APPLICATION_JSON))
                                .andDo(print())
                                .andReturn()
                                .getResponse().getContentAsString();

        String expectedResponse = objectMapper.writeValueAsString(expenselist);
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void shouldGetBalance() throws Exception {
        HashMap<Friend, BigDecimal> balance = new HashMap<>();
        Friend luis = new Friend("luis", "merino");
        Friend sonia = new Friend("sonia", "zhang");
        balance.put(luis, BigDecimal.valueOf(5)); // a luis le deben 5
        balance.put(sonia, BigDecimal.valueOf(-5)); // sonia debe 5

        when(calculateBalanceUseCase.calculateBalance()).thenReturn(balance);
        String response = mvc.perform(get("/api/v1/expenses/balance").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse().getContentAsString();

        String expectedResponse = objectMapper.writeValueAsString(balance);
        Assertions.assertEquals(expectedResponse, response);
    }

}