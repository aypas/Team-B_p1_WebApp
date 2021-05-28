package com.revature.p1.services;

import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.exceptions.AuthenticationException;
import com.revature.p1.exceptions.DataSourceException;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.exceptions.ResourcePersistenceException;
import com.revature.p1.models.account.BankUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 8:51 AM
 * Description: {Insert Description}
 */
public class BankUserServiceTest {

    private BankUserService sut;
    private BankUserDAO mockUserDao;

    @Before
    public void setUp() {
        mockUserDao = mock(BankUserDAO.class);
        sut = new BankUserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
        mockUserDao = null;
    }

    @Test
    public void test_registerWithValidUserAndAvailableUsernameAndPassword() {

        // Arrange
        BankUser user = new BankUser(0, "fN", "lN", "uN", "taken@email.com", "pw");
        when(mockUserDao.isUsernameAvailable(user)).thenReturn(true);
        when(mockUserDao.isEmailAvailable(user)).thenReturn(true);

        // Act
        sut.register(user);

        // Assert
        verify(mockUserDao, times(1)).isUsernameAvailable(user);
        verify(mockUserDao, times(1)).isEmailAvailable(user);
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        BankUser user = new BankUser(0, "fN", "lN", "uN", "taken@email.com", "pw");
        when(mockUserDao.isUsernameAvailable(user)).thenReturn(false);

        // Act
        try {
            sut.register(user);
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(user);
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test
    public void test_registerWithValidUserAndTakenEmail() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new BankUser(0, "fN", "lN", "uN", "taken@email.com", "pw"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(anyString());
            verify(mockUserDao, times(0)).save(any());
        }


    }

    @Test(expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        // Arrange
        BankUser invalidUser = new BankUser(0, "", "", "", "", "");

        // Act
        sut.register(invalidUser);

        // Assert
        verify(mockUserDao.isUsernameAvailable(anyString()), times(1));
        verify(mockUserDao.isEmailAvailable(anyString()), times(0));


    }


    @Test
    public void test_authenticateWithExistingUser() {
        // Arrange
        when(mockUserDao.findUserByUsernameAndPassword("johnsmith", "revature")).thenReturn(new BankUser(1, "John", "Smith", "johnsmith", "john@gmail.com", "revature"));
        int expectedId = 1;
        String expectedFirst = "John";
        String expectedLast = "Smith";
        String expectedUser = "johnsmith";
        String expectedEmail = "john@gmail.com";
        String expectedPass = "revature";

        // Act
        BankUser authenticatedUser = sut.authenticate("johnsmith", "revature");

        // Assert
        assertEquals(expectedFirst, authenticatedUser.getfName());
        assertEquals(expectedLast, authenticatedUser.getlName());
        assertEquals(expectedUser, authenticatedUser.getuName());
        assertEquals(expectedId, authenticatedUser.getuID());
        assertEquals(expectedEmail, authenticatedUser.getEmail());
        assertEquals(expectedPass, authenticatedUser.getPassword());
        verify(mockUserDao, times(1)).findUserByUsernameAndPassword(anyString(), anyString());
    }

    @Test (expected = AuthenticationException.class)
    public void test_authenticateWithNonExistingUser() {
        // Arrange
        when(mockUserDao.findUserByUsernameAndPassword("", "")).thenReturn(null);

        // Act
        BankUser authenticatedUser = sut.authenticate("", "");

        // Assert
        verify(mockUserDao.findUserByUsernameAndPassword(anyString(), anyString()), times(1));
    }

    @Test (expected = AuthenticationException.class)
    public void test_authenticateWithSQLException() {
        // Arrange
        when(mockUserDao.findUserByUsernameAndPassword("", "")).thenThrow(new DataSourceException());

        // Act
        BankUser authenticatedUser = sut.authenticate("", "");

        // Assert
        verify(mockUserDao.findUserByUsernameAndPassword(anyString(), anyString()), times(1));

    }

    @Test
    public void test_isUserValidWithValidUser() {
        // Arrange
        boolean expected = true;
        BankUser test = new BankUser(1, "Test", "Testington", "testusername", "testemail@gmail.com", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithNullUser() {
        // Arrange
        boolean expected = false;
        BankUser test = null;

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithInvalidUsername() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "Test", "Testington", "", "testemail@gmail.com", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithInvalidPassword() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "Test", "Testington", "testusername", "testemail@gmail.com", null);

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithTooLongEmail() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "Test", "Testington", "testusername", "1234567891234567891234567891234567891234567891", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithInvalidFirstName() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "", "Testington", "testusername", "testemail@gmail.com", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithInvalidLastName() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "Test", "", "testusername", "testemail@gmail.com", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }

    @Test
    public void test_isUserValidWithInvalidEmailFormat() {
        // Arrange
        boolean expected = false;
        BankUser test = new BankUser(1, "Test", "Testington", "testusername", "testemail@gmail.comcomcom", "testpassword");

        // Act
        boolean result = sut.isUserValid(test);

        // Assert
        assertEquals(expected, result);

    }
}

