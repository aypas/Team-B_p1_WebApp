package com.revature.p1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.UserDAO;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BankUserController {
//    private UserDAO userDao;

    //Refactor with Dependency Injection? pass bankuser class the same way?
    private BankUserService bankUserService = new BankUserService(new UserDAO());

    public void register(HttpServletRequest req, HttpServletResponse resp){
        String fName = req.getParameter("First_Name");
        String lName = req.getParameter("Last_Name");
        String uName = req.getParameter("Username");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");

        BankUser newUser = new BankUser(fName, lName, uName, email, password);
        bankUserService.register(newUser);
        resp.setStatus(202);
    }

    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, Object> jsonMap = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);

        BankUser bankuser = bankUserService.authenticate(jsonMap.get("username").toString(), jsonMap.get("password").toString());
        if(bankuser == null){
            resp.getWriter().println("User not found.");
        } else{
            resp.getWriter().println("Logged in as:\n"+bankuser.toString());
        }
        resp.setStatus(202);
    }
}