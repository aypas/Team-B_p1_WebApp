package com.revature.p0.util.datastructs.linkedlist;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 9:20 AM
 * Description: {Insert Description}
 */
public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void setUpTest() {
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue() {
        // Arrange (prepare the test)
        int expectedSize = 1;

        // Act (do the test)
        sut.add("data");

        // Assert (ensure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = Exception.class)
    public void test_addWithNullValue() {
        // Arrange
        // sometimes blank if there's nothing in particular to do

        // Act
        sut.add(null);

        // Assert
        // sometimes blank, especially if you expect an exception to be thrown
    }

    @Test
    public void test_pollWithEmptyList() {
        // Arrange
        // nothing to do here...

        // Act
        String actualResult = sut.poll();

        // Assert
        Assert.assertNull(actualResult);
    }

    @Test
    public void test_pollWithPopulatedList() {
        // Arrange
        sut.add("test data 1");
        sut.add("test data 2");
        String expectedResult = "test data 1";
        int expectedSize = 1;

        // Act
        String actualResult = sut.poll();

        // Assert
        int actualSize = sut.size();
        Assert.assertEquals(expectedResult, actualResult);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void test_sizeWithEmptyList() {
        //Arrange
        int expectedResult = 0;


        // Act
        int actualResult = sut.size();

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_sizeWithPopulatedList() {
        //Arrange
        sut.add("test");
        int expectedResult = 1;


        // Act
        int actualResult = sut.size();

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = Exception.class)
    public void test_getWithEmptyList() {
        //Arrange

        // Act
        sut.get(1);

        // Assert

    }

    @Test
    public void test_getWithPopulatedList() {
        //Arrange
        sut.add("test");
        String expected = "test";

        // Act
        String actual = sut.get(0);

        // Assert
        Assert.assertEquals(expected, actual);

    }

}
