package com.revature.p1.services;


import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.util.singleton.CurrentAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 10:31 AM
 * Description: {Insert Description}
 */
public class DepositWithdrawServiceTest {

    private DepositWithdrawService sut;
    private AccountTransactionService mockAccountTransactionService;
    private AccountBalanceDAO mockBalanceDao;

    @Before
    public void setUp() {
        mockBalanceDao = mock(AccountBalanceDAO.class);
        mockAccountTransactionService = mock(AccountTransactionService.class);
        sut = new DepositWithdrawService(mockBalanceDao, mockAccountTransactionService);
    }

    @After
    public void tearDown() {
        sut = null;
        mockBalanceDao = null;
        mockAccountTransactionService = null;
    }

    @Test (expected = InvalidRequestException.class)
    public void test_createBalanceWithInvalidDeposit() {

        // Arrange
        BankUser testUser = new BankUser();
        int testId = 1;
        double testAmount = 0;
        String transType = "Deposit";

        // Act
        sut.createBalance(testUser, testId, testAmount, transType);

        // Assert
        verify(mockBalanceDao, times(0)).saveBalance(any());
        verify(mockBalanceDao, times(0)).getBalance(any());
        verify(mockAccountTransactionService, times(0)).sendBalanceAsTransaction(any());
    }

    @Test
    public void test_createBalanceWithDeposit() {

        // Arrange
        BankUser testUser = new BankUser();
        int testId = 1;
        double testAmount = 2;
        AccountBalance currentBalance = new AccountBalance(1, 1);
        String transType = "deposit";
        when(mockBalanceDao.saveBalance(any())).thenReturn(true);
        when(mockBalanceDao.getBalance(any())).thenReturn(currentBalance);
        when(mockAccountTransactionService.sendBalanceAsTransaction(any())).thenReturn(true);
        AccountBalance expected = new AccountBalance(1, 3);

        // Act
        AccountBalance actual = sut.createBalance(testUser, testId, testAmount, transType);

        // Assert
        assertEquals(expected.getAcctID(), actual.getAcctID());
        assertEquals(expected.getBalance(), actual.getBalance(), 0.0);
        verify(mockBalanceDao, times(1)).saveBalance(any());
        verify(mockBalanceDao, times(1)).getBalance(any());
        verify(mockAccountTransactionService, times(1)).sendBalanceAsTransaction(any());
    }

    @Test
    public void test_createBalanceWithWithdrawal() {

        // Arrange
        BankUser testUser = new BankUser();
        int testId = 1;
        double testAmount = 1;
        AccountBalance currentBalance = new AccountBalance(1, 2);
        String transType = "withdraw";
        when(mockBalanceDao.saveBalance(any())).thenReturn(true);
        when(mockBalanceDao.getBalance(any())).thenReturn(currentBalance);
        when(mockAccountTransactionService.sendBalanceAsTransaction(any())).thenReturn(true);
        AccountBalance expected = new AccountBalance(1, 1);

        // Act
        AccountBalance actual = sut.createBalance(testUser, testId, testAmount, transType);

        // Assert
        assertEquals(expected.getAcctID(), actual.getAcctID());
        assertEquals(expected.getBalance(), actual.getBalance(), 0.0);
        verify(mockBalanceDao, times(1)).saveBalance(any());
        verify(mockBalanceDao, times(1)).getBalance(any());
        verify(mockAccountTransactionService, times(1)).sendBalanceAsTransaction(any());
    }

    @Test (expected = InvalidRequestException.class)
    public void test_createBalanceWithInvalidTransaction() {

        // Arrange

        BankUser testUser = new BankUser();
        int testId = 1;
        double testAmount = 2;
        AccountBalance currentBalance = new AccountBalance(1, 1);
        String transType = "deposit";
        when(mockBalanceDao.saveBalance(any())).thenReturn(false);
        when(mockBalanceDao.getBalance(any())).thenReturn(currentBalance);
        when(mockAccountTransactionService.sendBalanceAsTransaction(any())).thenReturn(true);

        // Act
        sut.createBalance(testUser, testId, testAmount, transType);

        // Assert
        verify(mockBalanceDao, times(1)).saveBalance(any());
        verify(mockBalanceDao, times(1)).getBalance(any());
        verify(mockAccountTransactionService, times(0)).sendBalanceAsTransaction(any());
    }

    @Test
    public void test_isDepositValidWithValidDeposit() {

        // Arrange
        boolean expected = true;
        double amount = 2;
        String transType = "withdraw";

        // Act
        boolean result = sut.isDepositValid(amount, transType);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void test_isDepositValidWithInalidDeposit() {

        // Arrange
        boolean expected = false;
        double amount = 0;
        String transType = "withdraw";

        // Act
        boolean result = sut.isDepositValid(amount, transType);

        // Assert
        assertEquals(expected, result);
    }


}