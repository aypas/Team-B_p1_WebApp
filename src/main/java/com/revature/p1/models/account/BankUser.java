package com.revature.p1.models.account;

import com.revature.util.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:06 AM
 * Description: {Insert Description}
 */

@Entity
@Table(table_name = "user_table")
public class BankUser {

    @Primary(name = "id")
    private int uID;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @Column(name = "username")
    @Username
    private String uName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @Password
    private String password;

    public BankUser(){
        super();
    }

    public BankUser(String fName, String lName, String uName, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.uName = uName;
        this.email = email;
        this.password = password;
    }

    public BankUser(int uID, String fName, String lName, String uName, String email, String password) {
        this.uID = uID;
        this.fName = fName;
        this.lName = lName;
        this.uName = uName;
        this.email = email;
        this.password = password;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BankUser{");
        sb.append("fName='").append(fName).append('\'');
        sb.append(", lName='").append(lName).append('\'');
        sb.append(", uName='").append(uName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
