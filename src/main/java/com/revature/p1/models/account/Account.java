package com.revature.p1.models.account;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:17 AM
 * Description: {Insert Description}
 */
public class Account {
    private int aID;
    private String aName;
    private int uID;
    private int jUID;
    private int tID;

    public Account() {
        super();
    }

    public Account(int aID, String aName, int uID, int jUID, int tID) {
        this.aID = aID;
        this.aName = aName;
        this.uID = uID;
        this.jUID = jUID;
        this.tID = tID;
    }

    public Account(String aName, int uID, int jUID, int tID) {
        this.aName = aName;
        this.uID = uID;
        this.jUID = jUID;
        this.tID = tID;
    }

    public Account(int uID) {
        this.uID = uID;
    }

    public Account(int uId, int tId, String aName) {
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getjUID() {
        return jUID;
    }

    public void setjUID(int jUID) {
        this.jUID = jUID;
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }
}
