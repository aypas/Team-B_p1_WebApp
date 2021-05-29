package com.revature.p1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountTypeDAO;
import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountBalance;
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
    private AccountBalanceDAO balanceDAO;
    private ObjectMapper mapper;

    public AccountsController(DepositService depositService, WithdrawService withdrawService, AccountOpeningService accountOpeningService, AccountTransactionService accountTransactionService, AccountTypeDAO accountTypeDAO, AccountBalanceDAO balanceDAO, ObjectMapper mapper) {
        this.depositService = depositService;
        this.withdrawService = withdrawService;
        this.accountOpeningService = accountOpeningService;
        this.accountTransactionService = accountTransactionService;
        this.accountTypeDAO = accountTypeDAO;
        this.balanceDAO = balanceDAO;
        this.mapper = mapper;
    }

    public void getAllAcctTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            AccountType[] acctTypes = accountTypeDAO.getAllAcctTypes();
//            Arrays.stream(acctTypes).forEach(accountType -> System.out.println("account type " + accountType.getType()));
            writer.write(mapper.writeValueAsString(acctTypes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveNewAcct(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        if (req.getSession().getAttribute("this-user") == null) {
            //Should this throw exception instead?
            resp.setStatus(401);
            return;
        }

        try {

            Account newAcct = mapper.readValue(req.getInputStream(), Account.class);
            Account acct = accountOpeningService.createAccount(newAcct);

            BankUser currentUser = (BankUser) req.getSession().getAttribute("this-user");
            System.out.println(currentUser);
            System.out.println(acct.getuID() + currentUser.getuID());
            if(acct.getuID() != currentUser.getuID()){
                resp.setStatus(401);
                return;
            }

            writer.write(mapper.writeValueAsString(newAcct));


        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(404);
        }
    }

    public void getBalance(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Doesnt work with current config => needs constructor in Account model that taks aID,
        //but isn't allowed since there is already and int as only arg constructor => uID

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        System.out.println(req.getSession().getAttribute("this-user").toString());

        if (req.getSession().getAttribute("this-user") == null) {
            //Should this throw exception instead?
            resp.setStatus(401);
            return;
        }
        System.out.println("after truy block in get balance contoller");

        try{
             AccountBalance acctID = mapper.readValue(req.getInputStream(), AccountBalance.class);;
            AccountBalance respBalance = balanceDAO.getBalance(acctID.getAcctID());

            writer.write(mapper.writeValueAsString(respBalance));

        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    public void createDeposit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BankUser bankUser = (BankUser) req.getSession().getAttribute("this-user");
         String depositAmt = req.getParameter("depositAmt");
         String accountId = req.getParameter("accountId");

        depositService.createBalance(bankUser, accountId, depositAmt);
    }
}
