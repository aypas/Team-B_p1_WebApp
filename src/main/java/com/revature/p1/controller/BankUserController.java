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

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }
    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try{
            BankUser newUser = mapper.readValue(req.getInputStream(), BankUser.class);
            bankUserService.register(newUser);

        }catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    public void authenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try{
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
               BankUser authUser = bankUserService.authenticate(creds.getUsername(), creds.getPassword());
            //prints username and password of authuser - remove??
            writer.write(mapper.writeValueAsString(authUser));

            req.getSession().setAttribute("this-user", authUser);

        } catch(MismatchedInputException e){
            e.printStackTrace();
            resp.setStatus(400);
        }catch (AuthenticationException e){
            e.printStackTrace();
            resp.setStatus(401);
        } catch(Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }

    }
}