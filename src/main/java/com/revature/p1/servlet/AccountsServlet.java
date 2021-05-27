package com.revature.p1.servlet;

import com.revature.p1.controller.AccountsController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountsServlet extends HttpServlet {


    private AccountsController accountsController;

    public AccountsServlet(AccountsController accountsController) {
            this.accountsController = accountsController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("in acc servlet get " + req.getRequestURI());
        //DONE
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

        switch(req.getRequestURI()){
            case "/bankapp/accounts/types":
                System.out.println("hit account types switch");
                accountsController.getAllAcctTypes(req, resp);
                break;
//            case ""

            default:
                resp.setStatus(400);
        }

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

        switch(req.getRequestURI()){
            case "/bankapp/accounts/newaccount":
                System.out.println("hit account new switch");
                accountsController.saveNewAcct(req, resp);
                break;
//            case ""

            default:
                resp.setStatus(400);

        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        //AccountBalanceDao
            //saveBalance
        // /accounts/balance
    }
}
