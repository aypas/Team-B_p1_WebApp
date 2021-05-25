package com.revature.p1.servlet;

import com.revature.p1.controller.BankUserController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    private BankUserController controller = new BankUserController();

    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch(req.getRequestURI()){
            case "/bankapp/user":
                controller.register(req, resp);
                break;
            case"/bankapp/auth":
                controller.authenticate(req, resp);

            default:
                resp.setStatus(400);
//                resp.getWriter().println(req.getRequestURI());
        }
    }
}
