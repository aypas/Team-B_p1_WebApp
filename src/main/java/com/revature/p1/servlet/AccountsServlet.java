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

        //Done
        //AccountDAO
            // getAcct
        // /accounts/all

        //DONE
        //AccountBalanceDao
            // getBalance
        // /accounts/balance (need param?)


        //switch statment using req.getRequestURI()

        switch(req.getRequestURI()){
            case "/bankapp/accounts/types":
                System.out.println("hit account types switch");
                accountsController.getAllAcctTypes(req, resp);
                break;
            case "/bankapp/accounts/balance":
                accountsController.getBalance(req, resp);

            default:
                resp.setStatus(400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //

        //DONE
        //AccountTransactionDAO
        //depositandwithdraw
        // /accounts/deposit

        //DONE
        // AccountDao
            //saveNewAcct
        // /accounts/newaccount


        //AccountBalanceDao
            //saveNewBalance
        // /accounts/newbalance

        //switch statment using req.getRequestURI()

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
                System.out.println("hit trans switch");
                break;
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
