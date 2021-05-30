package com.revature.p1.models.account;

import com.revature.util.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 11:09 AM
 * Description: {Insert Description}
 */

@Entity
@Table(table_name = "account_transaction")
public class AccountTransaction {

    @Foreign
    @Column(name = "account_id")
    private int acctID;

    @Primary(name = "id")
    private int transactionID;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_amt")
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountTransaction{");
        sb.append("acctID=").append(acctID);
        sb.append(", description='").append(description).append('\'');
        sb.append(", transactionAmt=").append(transactionAmt);
        sb.append('}');
        return sb.toString();
    }
}
