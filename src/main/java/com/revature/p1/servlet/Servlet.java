package com.revature.p1.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    private Dispatcher dispatcher =  new Dispatcher();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("in post servlet");
        dispatcher.dataDispatch(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        dispatcher.dataDisapatch(req, resp);
//    }

}
