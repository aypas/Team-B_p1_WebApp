package com.revature.p1.services;

import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.exceptions.ResourcePersistenceException;
import com.revature.p1.models.account.BankUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
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
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(anyString())).thenReturn(true);

        // Act
        sut.register(new BankUser(0, "fN", "lN", "uN", "taken@email.com", "pw"));

        // Assert
        verify(mockUserDao, times(1)).isUsernameAvailable(anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(anyString());
        verify(mockUserDao, times(1)).save(any());
    }

    @Test
    public void test_registerWithValidUserAndTakenUsername() {
        // Arrange
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);

        // Act
        try {
            sut.register(new BankUser(0, "fN", "lN", "tN", "valid@email.com", "pw"));
        } catch (Exception e) {
            assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(0)).isEmailAvailable(anyString());
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
}

