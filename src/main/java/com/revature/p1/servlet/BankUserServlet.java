package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.controller.AccountsController;
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in delete of bankuser servlet");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        BankUser requestingUser = (session == null) ? null : (BankUser) session.getAttribute("this-user");
        boolean success = false;

        if (requestingUser != null)  {
            success = bankUserController.delete(requestingUser);
            if (success) {
                resp.setStatus(200);
                writer.write("User successfully deleted.");
            } else {
                resp.setStatus(500);
                writer.write("Faild to delete user.");
            }
        } else {
            resp.setStatus(401);
            writer.write("Error: cannot delete when not logged in.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in put of bankuser servlet");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();

        BankUser updatedUser = mapper.readValue(req.getInputStream(), BankUser.class);

        HttpSession session = req.getSession(false);
        BankUser currentUser = (session == null) ? null : (BankUser) session.getAttribute("this-user");

        boolean success = false;

        if (currentUser != null && currentUser.getuID() == updatedUser.getuID()) {
            success = bankUserController.updateUser(updatedUser);
            if (success) {
                resp.setStatus(200);
                writer.write("User successfully updated.");
            } else {
                resp.setStatus(500);
                writer.write("Failed to update user.");
            }
        } else if (currentUser == null) {
            resp.setStatus(401);
            writer.write("Not logged in to an existing account.");
        } else {
            resp.setStatus(403);
            writer.write("Provided user ID does not match current user's.");
        }

        resp.setStatus(501);
        writer.write("Put not supported at this time.");

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        dispatcher.dataDisapatch(req, resp);
//    }

}
