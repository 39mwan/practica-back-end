package com.autentia.practica.pot.application;

import com.autentia.practica.pot.dao.ExpenseDao;
import com.autentia.practica.pot.dao.FriendDao;
import com.autentia.practica.pot.model.Expense;
import com.autentia.practica.pot.model.Friend;
import com.autentia.practica.pot.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculateBalanceUseCasTest {
    private ExpenseDao expenseDaoMock;
    private FriendDao friendDaoMock;

    private List<Expense> expenseList;
    private List<Friend> friendList;

    private HashMap<Friend, BigDecimal> expectedBalance;

    private Friend luis;
    private Friend sonia;
    private ExpenseService expenseServiceMock;


    @BeforeEach
    public void setUp() {
        expenseDaoMock = mock(ExpenseDao.class);
        expenseServiceMock = mock(ExpenseService.class);
        friendDaoMock = mock(FriendDao.class);


        friendList = new ArrayList<>();
        luis = new Friend("luis", "merino");
        sonia = new Friend("sonia", "zhang");
        friendList.add(luis);
        friendList.add(sonia);

        expenseList = new ArrayList<>();
        expenseList.add(new Expense(luis, BigDecimal.valueOf(20), "taxi", LocalDateTime.now()));
        expenseList.add(new Expense(sonia, BigDecimal.valueOf(10), "comida", LocalDateTime.now()));

        expectedBalance = new HashMap<>();
    }

    @Test
    public void shouldReturnCalculatedBalanceTest() {
        when(expenseDaoMock.getAllExpenses()).thenReturn(expenseList);
        when(friendDaoMock.getFriends()).thenReturn(friendList);

        expectedBalance.put(luis, BigDecimal.valueOf(5)); // a luis le deben 5
        expectedBalance.put(sonia, BigDecimal.valueOf(-5)); // sonia debe 5
        when(expenseServiceMock.calculate(expenseList, friendList)).thenReturn(expectedBalance);

        CalculateBalanceUseCase calculateBalanceUseCase = new CalculateBalanceUseCase(expenseDaoMock, friendDaoMock, expenseServiceMock);
        HashMap<Friend, BigDecimal> actualBalance = calculateBalanceUseCase.calculateBalance();
        assertEquals(expectedBalance, actualBalance);
    }

   }
