package com.revature.p1.util.datastructs.linkedlist;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/12/2021
 * Time: 9:38 AM
 * Description: {Insert Description}
 */
public class LinkedListNodeTest {

    private LinkedListNode<String> sut;

    @Before
    public void setUpTest() { sut = new LinkedListNode<String>(0 , "Node 0"); }

    @After
    public void tearDownTest() { sut = null; }

    @Test
    public void test_getData() {

        //Arrange

        String expected = "Node 0";

        //Act

        String actual = sut.getData();

        //Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_hasNext() {

        //Arrange

        LinkedListNode<String> sutHelper = new LinkedListNode<String>(1, "Node 1");
        sut.setNextNode(sutHelper);

        //Act

        boolean actual = sut.hasNext();

        //Assert

        Assert.assertEquals(true, actual);

    }

    @Test
    public void test_hasNoNext() {

        //Arrange

        //Act

        boolean actual = sut.hasNext();

        //Assert

        Assert.assertEquals(false, actual);

    }

    @Test
    public void test_getNext() {

        //Arrange

        LinkedListNode<String> sutHelper = new LinkedListNode<String>(1, "Node 1");
        sut.setNextNode(sutHelper);

        String expected = "Node 1";

        //Act

        sut = sut.next();

        String actual = sut.getData();

        //Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_hasPrevious() {

        //Arrange

        LinkedListNode<String> sutHelper = new LinkedListNode<String>(1, "Node 1");
        sut.setPrevNode(sutHelper);

        //Act

        boolean actual = sut.hasPrev();

        //Assert

        Assert.assertEquals(true, actual);

    }

    @Test
    public void test_hasNoPrevious() {

        //Arrange

        //Act

        boolean actual = sut.hasPrev();

        //Assert

        Assert.assertEquals(false, actual);

    }

    @Test
    public void test_getPrev() {

        //Arrange

        LinkedListNode<String> sutHelper = new LinkedListNode<String>(1, "Node 1");
        sut.setPrevNode(sutHelper);

        String expected = "Node 1";

        //Act

        sut = sut.prev();

        String actual = sut.getData();

        //Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_getIndex() {

        //Arrange

        int expected = 0;

        //Act

        int actual = sut.getIndex();

        //Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_setIndex() {

        //Arrange

        int expected = 1;

        //Act

        sut.setIndex(1);

        int actual = sut.getIndex();

        //Assert

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_setData() {

        //Arrange

        String expected = "chuh-anged";

        //Act

        sut.setData("chuh-anged");

        String actual = sut.getData();

        //Assert

        Assert.assertEquals(expected, actual);

    }

}
