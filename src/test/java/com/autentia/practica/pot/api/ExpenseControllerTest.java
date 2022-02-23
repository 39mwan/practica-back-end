package com.autentia.practica.pot.api;

import com.autentia.practica.pot.DemoApplication;
import com.autentia.practica.pot.application.AddExpenseUseCase;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.security.RunAs;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddExpenseUseCase addExpenseUseCase;

    @Test
    public void shouldAddExpense() throws Exception{

        Expense expense = new Expense(new Friend("Sonia", "Zhang"), BigDecimal.valueOf(20),"Taxi", LocalDateTime.now());

        addExpenseUseCase.addExpense(expense);
        verify(addExpenseUseCase).addExpense(expense);

        mvc.perform(post("/api/v1/expenses"))
                .andDo(print())
                .andExpect(status().isOk());
    }

 /*   @Test
    public void shouldPassIfStringMatches() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/api/v1/expenses", JSONObject.class).;
    }*/



}