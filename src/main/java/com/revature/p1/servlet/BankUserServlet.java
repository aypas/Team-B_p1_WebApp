package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.controller.AccountsController;
import com.revature.p1.controller.BankUserController;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.dtos.Credentials;
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

    private BankUserService bankUserService;
    private BankUserController bankUserController;

    public BankUserServlet(BankUserController bankUserController) {
        this.bankUserController = bankUserController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        BankUser requestingUser = (session == null) ? null : (BankUser) session.getAttribute("this-user");

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
        ObjectMapper mapper = new ObjectMapper();

        // user passes in username and password when making delete request
        Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
        HttpSession session = req.getSession(false);
        BankUser requestingUser = (session == null) ? null : (BankUser) session.getAttribute("this-user");
        boolean success = false;

        if (requestingUser == null) {
            resp.setStatus(401);
            writer.write("Error: cannot delete when not logged in.");
            // this probably shouldn't be handled in the servlet like this but it works for now
            // ensures that the user's credentials matched the currently logged in user just for an extra layer of caution when deleting
        } else if (creds.getUsername() != requestingUser.getuName() || creds.getPassword() != requestingUser.getPassword()) {
            resp.setStatus(403);
            writer.write("Credentials do not match current user. Cannot delete user.");
        } else {
            success = bankUserController.delete(requestingUser);
            if (success) {
                session.invalidate();
                resp.setStatus(200);
                writer.write("User successfully deleted.");
            } else {
                resp.setStatus(500);
                writer.write("Failed to delete user.");
            }
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
}
