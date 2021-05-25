package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.dtos.Credentials;
import com.revature.p1.exceptions.AuthenticationException;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.BankUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private final BankUserService bankUserService = new BankUserService(new BankUserDAO());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try{
            Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
            System.out.printf("Attempting to authenticate user, %s, with provided credentials", creds.getUsername());

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
