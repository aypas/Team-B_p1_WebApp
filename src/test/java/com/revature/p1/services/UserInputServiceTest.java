package com.revature.p1.services;

import com.revature.p1.exceptions.InvalidRequestException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 11:21 AM
 * Description: {Insert Description}
 */
public class UserInputServiceTest {

    private UserInputService sut;

    @Before
    public void setUp() {
        sut = new UserInputService();
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void test_userInputWithProperNumber() {
        //Arrange


        //Act

        boolean result = sut.validateInput(1, "1");

        //Assert

        Assert.assertEquals(true, result);

    }

    @Test
    public void test_userInputWithProperExit() {
        //Arrange


        //Act

        boolean result = sut.validateInput(1, "e");

        //Assert

        Assert.assertEquals(true, result);

    }

    @Test(expected = InvalidRequestException.class)
    public void test_userInputWithBadNumber() {
        //Arrange


        //Act

        boolean result = sut.validateInput(1, "2");

        //Assert


    }

    @Test(expected = InvalidRequestException.class)
    public void test_userInputWithBadLetter() {
        //Arrange


        //Act

        boolean result = sut.validateInput(1, "q");

        //Assert



    }

}
