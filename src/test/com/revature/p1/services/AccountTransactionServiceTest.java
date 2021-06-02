package com.revature.p1.services;

import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.util.singleton.CurrentAccount;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 11:11 AM
 * Description: {Insert Description}
 */
public class AccountTransactionServiceTest {

    private AccountTransactionService sut;
    private AccountTransactionDAO mockXactionDao;
    private Account currentAccount;

    @Before
    public void setUp() {
        mockXactionDao = mock(AccountTransactionDAO.class);
        sut = new AccountTransactionService(mockXactionDao);
        currentAccount = new Account("test",1,1,1);
    }

    @After
    public void tearDown() {
        sut = null;
        mockXactionDao = null;
        currentAccount = null;
    }

    @Test
    public void test_sendBalanceAsTransaction(){

        //Arrange
        AccountTransaction transaction = new AccountTransaction();
        when(mockXactionDao.saveTransaction(transaction)).thenReturn(true);
        boolean expected = true;

        //Act
        boolean actual = sut.sendBalanceAsTransaction(transaction);

        //Assert
        assertEquals(expected, actual);
        verify(mockXactionDao, times(1)).saveTransaction(transaction);

    }

    @Test
    public void test_getTransactions(){

        // Arrange
        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(new AccountTransaction());
        transactions.add(new AccountTransaction());
        Account newAccount = new Account();
        when(mockXactionDao.getAllAcctTransactions(newAccount)).thenReturn(transactions);

        // Act
        List<AccountTransaction> result = sut.getTransactions(newAccount);

        // Assert
        assertEquals(transactions, result);
        verify(mockXactionDao, times(1)).getAllAcctTransactions(newAccount);
    }

}
