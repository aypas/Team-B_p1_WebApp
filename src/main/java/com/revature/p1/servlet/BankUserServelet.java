package com.revature.p1.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BankUserServelet extends HttpServlet {

    private Dispatcher dispatcher =  new Dispatcher();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("in servlet");
        dispatcher.dataDisapatch(req, resp);
    }

}
