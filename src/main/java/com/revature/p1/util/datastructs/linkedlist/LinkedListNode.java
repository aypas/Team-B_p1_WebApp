package com.revature.p1.util.datastructs.linkedlist;

import com.revature.p1.util.iterator.DoubleIterator;


/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 3:27 PM
 * Description: A node for the custom linked list class allowing forward and backwards traversal.
 */
public class LinkedListNode<T> implements DoubleIterator {

    private T data;
    private LinkedListNode<T> nextNode;
    private LinkedListNode<T> prevNode;
    private int index;

    public LinkedListNode(int index, T data) {
        this.index = index;
        this.data = data;
    }

    public LinkedListNode(int index, T data, LinkedListNode<T> nextNode, LinkedListNode<T> prevNode) {
        this.index = index;
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    /**
     *
     * Description: Gets the node's index
     *
     * @return int
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * Description: Sets the nodes index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * Description: Gets the data from the node.
     *
     * @return T
     */
    public T getData() {
        return data;
    }

    /**
     *
     * Description: Sets the nodes data.
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * Description: Sets the nodes next node reference.
     *
     * @param nextNode
     */
    public void setNextNode(LinkedListNode<T> nextNode) {
        this.nextNode = nextNode;
    }


    /**
     *
     * Description: Sets the nodes previous node reference.
     *
     * @param prevNode
     */
    public void setPrevNode(LinkedListNode<T> prevNode) {
        this.prevNode = prevNode;
    }

    /**
     *
     * Description: Checks to see if node has a next node reference.
     *
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        boolean returnValue = false;

        if (this.nextNode != null) {
            returnValue = true;
        }

        return returnValue;
    }


    /**
     *
     * Description: Goes to the next node reference.
     *
     * @return LinkedListNode<T>
     */
    @Override
    public LinkedListNode<T> next() {
        return this.nextNode;
    }

    /**
     *
     * Description: Checks if the node has a previous node reference.
     *
     * @return boolean
     */
    @Override
    public boolean hasPrev() {
        boolean returnValue = false;

        if (this.prevNode != null) {
            returnValue = true;
        }

        return returnValue;
    }

    /**
     *
     * Description: Sets the reference to the current nodes previous node reference.
     *
     * @return LinkedListNode<T>
     */
    @Override
    public LinkedListNode<T> prev() {
        return prevNode;
    }

}
