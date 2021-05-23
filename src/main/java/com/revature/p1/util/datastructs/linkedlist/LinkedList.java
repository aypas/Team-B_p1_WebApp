package com.revature.p1.util.datastructs.linkedlist;

import com.revature.p1.util.datastructs.queue.Queue;
import com.revature.p1.util.datastructs.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 3:24 PM
 * Description: Custom linked list implementation that holds LinkedListNodes<T>
 */
public class LinkedList<T> implements List<T>, Queue<T> {

    private int size;
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;

    /**
     *
     * Description: Creates a LinkedListNode with supplied data
     *
     * @param data
     * @throws IllegalArgumentException
     */
    public void add(T data) throws IllegalArgumentException {

        if (data == null) {
            throw new IllegalArgumentException("This linked list does not accept null values");
        }

        LinkedListNode<T> newNode = new LinkedListNode<T>(size, data);
        if (head == null) {
            tail = head = newNode; // sets both head and tail as the same new node
        } else {
            tail.setNextNode(newNode);
            newNode.setPrevNode(tail);
            tail = newNode;
        }

        size++;

    }

    /**
     *
     * Description: Returns the size of the linked list.
     *
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     *
     * Description: Gets a LinkedListNode by index.
     *
     * @param index
     * @return LinkedListNode<T>
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        LinkedListNode<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.getData();
            }
            runner = runner.next();
        }

        return null;
    }

    /**
     *
     * Description: Removes and returns LinkedListNode<T>
     *
     * @return
     */
    @Override
    public T poll() {

        if (head == null) {
            return null;
        }

        T soughtData = head.getData();
        head = head.next();

        if (head != null) {
            head.setPrevNode(null);
        } else {
            tail = null;
        }

        size--;

        return soughtData;

    }

}
