package com.revature.p1.dtos;


import com.revature.util.annotation.*;

@Entity
@Table(table_name = "user_table")
public class Credentials {

    @Column(name = "username")
    @Username
    private String username;

    @Column(name = "password")
    @Password
    private String password;

    public Credentials(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Credentials{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
