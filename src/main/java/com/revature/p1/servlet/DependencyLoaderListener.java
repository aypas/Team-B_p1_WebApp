package com.revature.p1.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.controller.BankUserController;
import com.revature.p1.controller.AccountsController;
import com.revature.p1.daos.*;
import com.revature.p1.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * This class is handles DAO dependency injection to the appropriate service's
 * and injects them to the appropriate contorller.
 */
public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ObjectMapper mapper = new ObjectMapper();

        //BankUserController Injections
        BankUserDAO bankUserDAO = new BankUserDAO();
        BankUserService bankUserService = new BankUserService(bankUserDAO);
        BankUserController bankUserController = new BankUserController(bankUserService, mapper);

        //Accounts Controller Injections
        AccountBalanceDAO balanceDAO = new AccountBalanceDAO();
        _WithdrawService withdrawService = new _WithdrawService(balanceDAO);
        AccountDAO accountDAO = new AccountDAO();
        AccountOpeningService accountOpeningService = new AccountOpeningService(accountDAO);
        AccountTransactionDAO transactionDAO = new AccountTransactionDAO();
        AccountTransactionService accountTransactionService = new AccountTransactionService(transactionDAO);
        DepositWithdrawService depositWithdrawService = new DepositWithdrawService(balanceDAO, accountTransactionService);
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
        AccountsController accountsController = new AccountsController(depositWithdrawService, withdrawService,accountOpeningService, accountTransactionService, accountTypeDAO, balanceDAO, mapper);

        AuthServlet authServlet = new AuthServlet(bankUserController);
        BankUserServlet bankUserServlet= new BankUserServlet(bankUserController);
        AccountsServlet accountsServlet = new AccountsServlet(accountsController);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("BankUserServlet", bankUserServlet).addMapping("/users/*");
        context.addServlet("AccountsServlet", accountsServlet).addMapping("/accounts/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
