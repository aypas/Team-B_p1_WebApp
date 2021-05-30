package com.revature.p1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountTypeDAO;
import com.revature.p1.models.account.*;
import com.revature.p1.services.AccountOpeningService;
import com.revature.p1.services.AccountTransactionService;
import com.revature.p1.services.DepositWithdrawService;
import com.revature.p1.services._WithdrawService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class AccountsController {

    private DepositWithdrawService depositWithdrawService;
    private _WithdrawService withdrawService;
    private AccountOpeningService accountOpeningService;
    private AccountTransactionService accountTransactionService;
    private AccountTypeDAO accountTypeDAO;
    private AccountBalanceDAO balanceDAO;
    private ObjectMapper mapper;

    public AccountsController(DepositWithdrawService depositWithdrawService, _WithdrawService withdrawService, AccountOpeningService accountOpeningService, AccountTransactionService accountTransactionService, AccountTypeDAO accountTypeDAO, AccountBalanceDAO balanceDAO, ObjectMapper mapper) {
        this.depositWithdrawService = depositWithdrawService;
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
            List<AccountType> acctTypes = accountTypeDAO.getAllAcctTypes();
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
            if (acct.getuID() != currentUser.getuID()) {
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

        if (req.getSession().getAttribute("this-user") == null) {
            //Should this throw exception instead?
            System.out.println("req sessioin if");
            resp.setStatus(401);
            return;
        }
        System.out.println("after truy block in get balance contoller");

        try {
            AccountBalance acctID = mapper.readValue(req.getInputStream(), AccountBalance.class);
            ;
            AccountBalance respBalance = balanceDAO.getBalance(acctID);
            if (respBalance.getAcctID() == 0) {
                writer.write("Invalid request data.");
                resp.setStatus(400);
                return;
            } else {
                writer.write(mapper.writeValueAsString(respBalance));
                resp.setStatus(200);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    public void createDepositWithdraw(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        if (req.getSession().getAttribute("this-user") == null) {
            writer.write("You are no autorized");
            resp.setStatus(401);
            return;
        }

        BankUser bankUser = (BankUser) req.getSession().getAttribute("this-user");
        WithdrawDeposit withdrawDeposit = mapper.readValue(req.getInputStream(), WithdrawDeposit.class);

        String[] reqArr = req.getRequestURI().split("/");
        String transType = reqArr[reqArr.length - 1];

        double amount = withdrawDeposit.getAmount();

        AccountBalance accountBalance = depositWithdrawService.createBalance(bankUser, withdrawDeposit.getaID(), amount, transType);
        if (accountBalance.getAcctID() == 0) {
            resp.setStatus(400);
            writer.write("Invalid transaction amount entered.");
            return;
        }
        writer.write(mapper.writeValueAsString(accountBalance));
    }


    public void createTransaction(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        boolean result;
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        if (req.getSession().getAttribute("this-user") == null) {
            resp.setStatus(401);
            return;
        }

        try{
            AccountTransaction newAccountTrans = mapper.readValue(req.getInputStream(), AccountTransaction.class);

            result = accountTransactionService.sendBalanceAsTransaction(newAccountTrans);
            if (result) {
                writer.write("Transaction save: succes!");
                resp.setStatus(200);
            } else {
                writer.write("Transaction save: failed.");
                resp.setStatus(400);
            }
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTransactions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        if (req.getSession().getAttribute("this-user") == null) {
            resp.setStatus(401);
            return;
        }

        try{
            Account account = mapper.readValue(req.getInputStream(), Account.class);

            List<AccountTransaction> allTransactions = accountTransactionService.getTransactions(account);
            System.out.println(allTransactions.size());

            allTransactions.stream().forEach((transaction) -> {
                try {
                    writer.write(mapper.writeValueAsString(transaction));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

            }catch (Exception e){
            e.printStackTrace();
        }
    }

}
