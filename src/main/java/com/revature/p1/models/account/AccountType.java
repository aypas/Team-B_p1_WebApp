package com.revature.p1.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/4/2021
 * Time: 9:59 AM
 * Description: {Insert Description}
 */
public class AccountType {
    private int id;
    private double monthlyFees;
    private double interest;
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
