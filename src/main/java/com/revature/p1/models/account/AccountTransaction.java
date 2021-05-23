package com.revature.p1.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 11:09 AM
 * Description: {Insert Description}
 */
public class AccountTransaction {
    private int acctID;
    private int transactionID;
    private String description;
    private double transactionAmt;

    public AccountTransaction() {
    }

    public AccountTransaction(int acctID, String description, double transactionAmt) {
        this.acctID = acctID;
        this.description = description;
        this.transactionAmt = transactionAmt;
    }

    public AccountTransaction(int acctID, int transactionID, String description, double transactionAmt) {
        this.acctID = acctID;
        this.transactionID = transactionID;
        this.description = description;
        this.transactionAmt = transactionAmt;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getAcctID() {
        return acctID;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public double getTransactionAmt() {
        return transactionAmt;
    }

    public void setTransactionAmt(double transactionAmt) {
        this.transactionAmt = transactionAmt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
