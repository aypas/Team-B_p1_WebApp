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
 * This class is tied to the startup and shutdown of tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
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
        DepositService depositService = new DepositService(balanceDAO);
        WithdrawService withdrawService = new WithdrawService(balanceDAO);
        AccountDAO accountDAO = new AccountDAO();
        AccountOpeningService accountOpeningService = new AccountOpeningService(accountDAO);
        AccountTransactionDAO transactionDAO = new AccountTransactionDAO();
        AccountTransactionService accountTransactionService = new AccountTransactionService(transactionDAO);
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
        AccountsController accountsController = new AccountsController(depositService, withdrawService,accountOpeningService, accountTransactionService, accountTypeDAO, mapper);

        //has one method - getAllaccount types -> isn't tied to a service

        AuthServlet authServlet = new AuthServlet(bankUserController);
        BankUserServlet bankUserServlet= new BankUserServlet(bankUserController);
        AccountsServlet accountsServlet = new AccountsServlet(accountsController);


        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("BankUserServlet", bankUserServlet).addMapping("/users/*");
        context.addServlet("AccountsServlet", accountsServlet).addMapping("/accounts/*");
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
