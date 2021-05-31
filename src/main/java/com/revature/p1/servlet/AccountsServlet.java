package com.revature.p1.servlet;

import com.revature.p1.controller.AccountsController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class routes client requests to the appropriate controller.
 */
public class AccountsServlet extends HttpServlet {

    private AccountsController accountsController;

    public AccountsServlet(AccountsController accountsController) {
            this.accountsController = accountsController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch(req.getRequestURI()){
            case "/bankapp/accounts/types":
                accountsController.getAllAcctTypes(req, resp);
                break;
            case "/bankapp/accounts/balance":
                accountsController.getBalance(req, resp);
            case"/bankapp/accounts/transactions":
                accountsController.getTransactions(req, resp);
                break;

            default:
                resp.setStatus(400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch(req.getRequestURI()){
            case "/bankapp/accounts/newaccount":
                accountsController.saveNewAcct(req, resp);
                break;
            case "/bankapp/accounts/newbalance":
                ///taken care of in new account? Make its own independent ruoute?
//                accountsController.saveNewBalance(req, resp);
                break;
            case "/bankapp/accounts/deposit":
            case "/bankapp/accounts/withdraw":
                accountsController.createDepositWithdraw(req,resp);
                break;
            case "/bankapp/accounts/transaction":
                accountsController.createTransaction(req, resp);
                break;
            default:
                resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("in delete of bankuser servlet");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.setStatus(501);
        writer.write("Deleting account not currently supported");
    }
}
