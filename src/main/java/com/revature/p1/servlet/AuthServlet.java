package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.p1.controller.BankUserController;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.dtos.Credentials;
import com.revature.p1.exceptions.AuthenticationException;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class handles client requests specific to user and route authentication.
 */

public class AuthServlet extends HttpServlet {

    private BankUserController bankUserController;

    public AuthServlet(BankUserController bankUserController) {
       this.bankUserController = bankUserController;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            bankUserController.authenticate(req, resp);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
