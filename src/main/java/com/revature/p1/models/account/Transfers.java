package com.revature.p1.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 9:59 AM
 * Description: {Insert Description}
 */
public class Transfers {
    private int id;
    private int senderID;
    private int receiverID;
    private double amount;

    public Transfers(int id, int senderID, int receiverID, double amount) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
