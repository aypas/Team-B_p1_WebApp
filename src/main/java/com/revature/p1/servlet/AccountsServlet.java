package com.revature.p1.servlet;

import com.revature.p1.controller.AccountsController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountsServlet extends HttpServlet {

    private AccountsController transactionController;

    public AccountsServlet(AccountsController transactionController) {
            this.transactionController = transactionController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        //AccountsTypeDao => no service tied to this one
            //getAllAcctTypes
        // /transactions/types

//        AccountTransactionDAO
        // /transactions/all?id=(some id)

        //switch statment using req.getRequestURI()
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        //AccountTransactionDAO
        // /transactions/

        // AccountDao
            //saveNewAcct
        // /transactions/account/register
    }
}
