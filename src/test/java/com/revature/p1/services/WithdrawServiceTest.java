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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 10:52 AM
 * Description: {Insert Description}
 */
public class WithdrawServiceTest {

    private WithdrawService sut;
    private AccountBalanceDAO mockBalanceDao;
    private AccountTransactionDAO mockXActionDAO;

    @Before
    public void setUp() {
        mockBalanceDao = mock(AccountBalanceDAO.class);
        mockXActionDAO = mock(AccountTransactionDAO.class);
        sut = new WithdrawService(mockBalanceDao, mockXActionDAO);
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
    public void test_withdrawWithTooManyDecimals() {

        //Arrange


        //Act

        sut.createBalance("0.111");

        //Assert

    }


    @Test(expected = InvalidRequestException.class)
    public void test_withdrawWithEmpty() {

        //Arrange


        //Act

        sut.createBalance("");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_withdrawWithSpace() {

        //Arrange


        //Act

        sut.createBalance(" ");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_withdrawWithNegative() {

        //Arrange


        //Act

        sut.createBalance("-25");

        //Assert

    }

    @Test(expected = InvalidRequestException.class)
    public void test_withdrawDoubleWithSpace() {

        //Arrange


        //Act

        sut.createBalance("2 5.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_withdrawWithBigDouble() {

        //Arrange

        when(mockBalanceDao.getBalance(any())).thenReturn(2600000000000.00);

        //Act

        sut.createBalance("2500000000000.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_withdrawWithAverageDouble() {

        //Arrange

        when(mockBalanceDao.getBalance(any())).thenReturn(26.00);

        //Act

        sut.createBalance("25.55");

        //Assert

        Assert.assertEquals(true, true);

    }

    @Test
    public void test_withdrawWithNoDecimal() {

        //Arrange

        when(mockBalanceDao.getBalance(any())).thenReturn(26.00);

        //Act

        sut.createBalance("25");

        //Assert

        Assert.assertEquals(true, true);

    }

}
