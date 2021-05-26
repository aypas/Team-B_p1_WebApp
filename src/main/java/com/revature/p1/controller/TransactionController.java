package com.revature.p1.controller;

import com.revature.p1.services.AccountOpeningService;
import com.revature.p1.services.AccountTransactionService;
import com.revature.p1.services.DepositService;
import com.revature.p1.services.WithdrawService;

import javax.servlet.http.HttpServlet;

public class TransactionController {

    private DepositService depositService;
    private WithdrawService withdrawService;
    private AccountOpeningService accountOpeningService;
    private AccountTransactionService accountTransactionService;

    public TransactionController(DepositService depositService, WithdrawService withdrawService, AccountOpeningService accountOpeningService, AccountTransactionService accountTransactionService) {
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.accountOpeningService = accountOpeningService;
        this.accountTransactionService = accountTransactionService;
    }


}
