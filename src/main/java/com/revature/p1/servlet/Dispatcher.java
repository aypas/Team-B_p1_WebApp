package com.revature.p1.servlet;

import com.revature.p1.controller.BankUserController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    private BankUserController controller = new BankUserController();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("in dispatcher dataDispatch" + req.getParameter("first_name"));

        switch(req.getRequestURI()){
            case "/bankapp/user":
                System.out.println("in dispatcher switch");
                controller.register(req, resp);
                break;
            default:
                resp.setStatus(400);
                resp.getWriter().println(req.getRequestURI());
        }
    }
}
