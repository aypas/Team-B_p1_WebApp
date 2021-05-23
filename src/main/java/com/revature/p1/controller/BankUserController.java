package com.revature.p1.controller;

import com.revature.p1.daos.UserDAO;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankUserController {
//    private UserDAO userDao;

    //Refactor with Dependency Injection?
    private BankUserService bankUserService = new BankUserService(new UserDAO());

    public void register(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("in controller register method");
        String fName = req.getParameter("First_Name");
        String lName = req.getParameter("Last_Name");
        String uName = req.getParameter("Username");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");

        BankUser newUser = new BankUser(fName, lName, uName, email, password);
        bankUserService.register(newUser);
        resp.setStatus(202);

    }
}
