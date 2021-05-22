package com.revature.p0.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:22 AM
 * Description: {Insert Description}
 */
public class AccountBalance {
    private int acctID;
    private double balance;

    public AccountBalance(int acctID, double balance) {
        this.acctID = acctID;
        this.balance = balance;
    }

    public int getAcctID() {
        return acctID;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
