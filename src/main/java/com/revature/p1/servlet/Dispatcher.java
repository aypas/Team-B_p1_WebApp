package com.revature.p1.servlet;

import com.revature.p1.controller.BankUserController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    private BankUserController controller = new BankUserController();

    public void dataDisapatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch(req.getRequestURI()){
            case "/user":
                controller.register(req, resp);
                break;
            default:
                resp.setStatus(400);
                resp.getWriter().println(req.getRequestURI());
        }
    }
}
