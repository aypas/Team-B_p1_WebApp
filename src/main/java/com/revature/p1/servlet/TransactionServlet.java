package com.revature.p1.servlet;

import com.revature.p1.controller.TransactionController;

import javax.servlet.http.HttpServlet;

public class TransactionServlet extends HttpServlet {

    private TransactionController transactionController

    public TransactionServlet(TransactionController transactionController) {
            this.transactionController = transactionController;
    }
}
