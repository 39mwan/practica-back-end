package com.autentia.practica.pot.api;

import com.autentia.practica.pot.DemoApplication;
import com.autentia.practica.pot.application.AddExpenseUseCase;
import com.autentia.practica.pot.application.CalculateBalanceUseCase;
import com.autentia.practica.pot.application.GetAllExpensesUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.security.RunAs;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
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
    private HashMap<Friend,BigDecimal> balance;

    private Friend luis;
    private Friend sonia;

    @BeforeEach
    public void setUp(){
        expense = new Expense(new Friend("Sonia", "Zhang"), BigDecimal.valueOf(20),"Taxi", LocalDateTime.now());
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
        balance = new HashMap<>();
        luis = new Friend("luis", "merino");
        sonia = new Friend("sonia", "zhang");
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