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
        // /accounts/types

//        AccountTransactionDAO
            //getAllAcctTransactions
        // /accounts/transaction/all?id=(some id)

        //AccountDAO
            // getAcct
        // /accounts/all

        //AccountBalanceDao
            // getBalance
        // /accounts/balance (need param?)


        //switch statment using req.getRequestURI()

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        //AccountTransactionDAO
        // /accounts/transaction

        // AccountDao
            //saveNewAcct
        // /accounts/newaccount

        //AccountBalanceDao
            //saveNewBalance
        // /accounts/newbalance

        //switch statment using req.getRequestURI()

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        //AccountBalanceDao
            //saveBalance
        // /accounts/balance
    }
}
