package com.revature.p1.services;


import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.Account;
import com.revature.p1.util.singleton.CurrentAccount;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 10:31 AM
 * Description: {Insert Description}
 */
public class DepositServiceTest {

    private DepositService sut;
    private AccountBalanceDAO mockBalanceDao;
    private AccountTransactionDAO mockXActionDAO;

    @Before
    public void setUp() {
        mockBalanceDao = mock(AccountBalanceDAO.class);
        mockXActionDAO = mock(AccountTransactionDAO.class);
        sut = new DepositService(mockBalanceDao, mockXActionDAO);
        CurrentAccount.getInstance().setCurrentAccount(new Account("test",1,1,1));
    }

    @After
    public void tearDown() {
        sut = null;
        mockBalanceDao = null;
        mockXActionDAO = null;
        CurrentAccount.getInstance().setCurrentAccount(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_depositWithTooManyDecimals() {

        //Arrange


        //Act

        sut.createBalance("0.111");

        //Assert

    }


    @Test(expected = InvalidRequestException.class)
    public void test_depositWithEmpty() {

        //Arrange


        //Act

        sut.createBalance("");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_depositWithSpace() {

        //Arrange


        //Act

        sut.createBalance(" ");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_depositWithNegative() {

        //Arrange


        //Act

        sut.createBalance("-25");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_depositDoubleWithSpace() {

        //Arrange


        //Act

        sut.createBalance("2 5.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_depositWithBigDouble() {

        //Arrange


        //Act

        sut.createBalance("2500000000000.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_depositWithAverageDouble() {

        //Arrange


        //Act

        sut.createBalance("25.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_depositWithNoDecimal() {

        //Arrange


        //Act

        sut.createBalance("25");

        //Assert

        Assert.assertEquals(true, true);

    }

}
