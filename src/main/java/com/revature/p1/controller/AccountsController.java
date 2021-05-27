package com.revature.p1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.AccountTypeDAO;
import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountType;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.services.AccountOpeningService;
import com.revature.p1.services.AccountTransactionService;
import com.revature.p1.services.DepositService;
import com.revature.p1.services.WithdrawService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AccountsController {

    private DepositService depositService;
    private WithdrawService withdrawService;
    private AccountOpeningService accountOpeningService;
    private AccountTransactionService accountTransactionService;
    private AccountTypeDAO accountTypeDAO;
    private ObjectMapper mapper;

    public AccountsController(DepositService depositService, WithdrawService withdrawService, AccountOpeningService accountOpeningService, AccountTransactionService accountTransactionService, AccountTypeDAO accountTypeDAO, ObjectMapper mapper) {
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.accountOpeningService = accountOpeningService;
        this.accountTransactionService = accountTransactionService;
        this.accountTypeDAO = accountTypeDAO;
        this.mapper = mapper;
    }

    //Abstract away Object Mapper?

    public void getAllAcctTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try{
            AccountType[] acctTypes = accountTypeDAO.getAllAcctTypes();
           Arrays.stream(acctTypes).forEach(accountType -> System.out.println("account type " + accountType.getType()));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveNewAcct(HttpServletRequest req, HttpServletResponse resp){

        resp.setContentType("application/json");

//        Account newAcct = new Account();

        if(req.getSession().getAttribute("this-user") == null){
            //Should this throw exception instead?
            resp.setStatus(401);
            return;
        }

        try{

            Account newAcct = mapper.readValue(req.getInputStream(), Account.class);
            accountOpeningService.createAccount(newAcct);

        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(404);
        }
      ;
        resp.setContentType("application/json");

//        try{
//            Account newAcctInfo = new Account()
//            Account newAcct = accountOpeningService.createAccount()
//        }
    }


}
