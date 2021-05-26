package com.revature.p1.servlet;

import com.revature.p1.controller.AccountController;
import com.revature.p1.controller.BankUserController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

//    private BankUserController bankUserController = new BankUserController();
//    private AccountController accountController = new AccountController();
//
//    public void dataDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        switch(req.getRequestURI()){
//            case "/bankapp/user":
//                bankUserController.register(req, resp);
//                break;
////            case"/bankapp/auth":
////                bankUserController.authenticate(req, resp);
////                break;
//            case "/bankapp/account/create":
//                System.out.println("account/create in dispatch switch");
//                accountController.createAccount(req, resp);
//
//            default:
//                resp.setStatus(400);
////                resp.getWriter().println(req.getRequestURI());
//        }
//    }
}
