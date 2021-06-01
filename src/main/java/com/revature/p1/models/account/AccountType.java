package com.revature.p1.models.account;

import com.revature.util.annotation.Column;
import com.revature.util.annotation.Entity;
import com.revature.util.annotation.Primary;
import com.revature.util.annotation.Table;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 9:59 AM
 * Description: {Insert Description}
 */

@Entity
@Table(table_name = "account_type")
public class AccountType {

    @Primary(name = "id")
    private int id;

    @Column(name = "monthly_fee")
    private double monthlyFees;

    @Column(name = "interest")
    private double interest;

    @Column(name = "acct_type")
    private String type;

    public AccountType(){};

    public AccountType(int id, String type, double monthlyFees, double interest) {
        this.id = id;
        this.type = type;
        this.monthlyFees = monthlyFees;
        this.interest = interest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
