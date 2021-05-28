package com.revature.p1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.dtos.Credentials;
import com.revature.p1.exceptions.AuthenticationException;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class BankUserController {

    private BankUserService bankUserService;
    private ObjectMapper mapper;


    public BankUserController(BankUserService bankUserService, ObjectMapper mapper) {

        this.bankUserService = bankUserService;
        this.mapper = mapper;

    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            BankUser newUser = mapper.readValue(req.getInputStream(), BankUser.class);
            bankUserService.register(newUser);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            BankUser authUser = bankUserService.authenticate(creds.getUsername(), creds.getPassword());
            writer.write(mapper.writeValueAsString(authUser));

            req.getSession().setAttribute("this-user", authUser);

        } catch (MismatchedInputException e) {
            e.printStackTrace();
            resp.setStatus(400);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            resp.setStatus(401);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}