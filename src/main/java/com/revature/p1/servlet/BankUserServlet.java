package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.controller.BankUserController;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

//@WebServlet("/user")
public class BankUserServlet extends HttpServlet {

//    private Dispatcher dispatcher = new Dispatcher();
    private BankUserService bankUserService;
    private BankUserController bankUserController;

    public BankUserServlet(BankUserController bankUserController) {
        this.bankUserController = bankUserController;
    }

        /*
    http verbs
        - actions taken on a resource
            get         READ
            post        CREATE
            put         UPDATE
            delete      DELETE
            patch       UPDATE
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in get of bankuser servlet");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        BankUser requestingUser = (session == null) ? null : (BankUser) session.getAttribute("this-user");

        System.out.println("get int bankuserSErvlet " + requestingUser.getuName());

        if(requestingUser == null){
            resp.setStatus(401);
            return;
        }else if(requestingUser.getuName() != null){
            resp.setStatus(200);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in post servlet");
        bankUserController.register(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        dispatcher.dataDisapatch(req, resp);
//    }

}
