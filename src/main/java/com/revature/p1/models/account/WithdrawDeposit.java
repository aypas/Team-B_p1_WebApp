package com.revature.p1.models.account;

public class WithdrawDeposit {
    private int aID;
    private double amount;

    public WithdrawDeposit(int aID, double amount) {
        this.aID = aID;
        this.amount = amount;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WithdrawDeposit{");
        sb.append("aID=").append(aID);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
