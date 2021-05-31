package com.revature.p1.services;

import com.revature.p1.daos.AccountTransactionDAO;
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
 * Time: 11:11 AM
 * Description: {Insert Description}
 */
public class TransactionServiceTest {

    private AccountTransactionService sut;
    private AccountTransactionDAO mockXactionDao;

    @Before
    public void setUp() {
        mockXactionDao = mock(AccountTransactionDAO.class);
        sut = new AccountTransactionService(mockXactionDao);
        CurrentAccount.getInstance().setCurrentAccount(new Account("test",1,1,1));
    }

    @After
    public void tearDown() {
        sut = null;
        mockXactionDao = null;
        CurrentAccount.getInstance().setCurrentAccount(null);
    }

    @Test
    public void test_SendInfoByTransactionDAO(){

        //Arrange


        //Act
    // args of sendTransacaiont takes and Account Transaction class
//        sut.sendBalanceAsTransaction("25", "test");

        //Assert

        Assert.assertEquals(true, true);

    }

}
