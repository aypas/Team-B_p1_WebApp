package com.revature.p1.services;

import com.revature.p1.daos.AccountDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 9:17 AM
 * Description: {Insert Description}
 */
public class AccountOpeningServiceTest {

    private com.revature.p1.services.AccountOpeningService sut;
    private AccountDAO mockAcctDAO;

    @Before
    public void setUp() {
        mockAcctDAO = mock(AccountDAO.class);
        sut = new com.revature.p1.services.AccountOpeningService(mockAcctDAO);
    }

    @After
    public void tearDown() {
        sut = null;
        mockAcctDAO = null;
    }

    @Test(expected = InvalidRequestException.class)
    public void test_createNullAcct() {

        // Arrange

        Account acct = null;

        // Act

        sut.createAccount(acct);

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithEmptyAcctName() {

        // Arrange

        Account acct = new Account("", 1, 0, 1);

        // Act

        sut.createAccount(acct);

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithLongAcctName() {

        // Arrange

        Account acct = new Account("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1, 0, 1);

        // Act

        sut.createAccount(acct);

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithNullAcctName() {

        // Arrange

        Account acct = new Account(null, 1, 0, 1);

        // Act

        sut.createAccount(acct);

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_createWithSpaceForAcctName() {

        // Arrange

        Account acct = new Account(" ", 1, 0, 1);

        // Act

        sut.createAccount(acct);

        //Assert

    }

    @Test
    public void test_createWithCorrectAcctName() {

        // Arrange

        Account acct = new Account("good", 1, 0, 1);

        Account expected = sut.createAccount(acct);


        // Act

        Account actual = sut.createAccount(acct);

        //Assert

        Assert.assertEquals(expected, actual);

    }

}