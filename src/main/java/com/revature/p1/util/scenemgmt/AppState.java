package com.revature.p1.util.scenemgmt;

import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountDAO;
import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.daos.BankUserDAO;
import com.revature.p1.screens.*;
import com.revature.p1.services.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:21 AM
 * Description: Holds all of the needed objects for the application
 *              and monitors if the application is running
 */
public class AppState{
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState() {
        System.out.println("Initializing application...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final BankUserDAO userDAO = new BankUserDAO();
        final AccountDAO acctDAO = new AccountDAO();
        final AccountBalanceDAO balanceDAO = new AccountBalanceDAO();
        final AccountTransactionDAO xActionDAO = new AccountTransactionDAO();

        final UserInputService inputService = new UserInputService();
        final BankUserService userService = new BankUserService(userDAO);
        final AccountOpeningService acctOpenService = new AccountOpeningService(acctDAO);
        final DepositService depositService = new DepositService(balanceDAO, xActionDAO);
        final WithdrawService withdrawService = new WithdrawService(balanceDAO, xActionDAO);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader))
                .addScreen(new LoginScreen(consoleReader, userDAO))
                .addScreen(new RegisterScreen(consoleReader, userService))
                .addScreen(new AccountScreen(consoleReader, inputService))
                .addScreen(new OpenAccountScreen(consoleReader, acctOpenService))
                .addScreen(new DepositScreen(consoleReader, depositService))
                .addScreen(new WithdrawScreen(consoleReader, withdrawService))
                .addScreen(new TransactionScreen(consoleReader, xActionDAO));

        System.out.println("Application initialized!");
    }

    /**
     *
     * Description: Allows other objects to access the screen router without
     *              an injection.
     *
     * @return
     */
    public ScreenRouter getRouter() {
        return router;
    }

    /**
     *
     * Description: Checks if the app should exit or continue to run
     *
     * @return boolean
     */
    public boolean isAppRunning() {
        return appRunning;
    }


    /**
     *
     * Description: Set the application to either running or not running.
     *
     * @param appRunning
     */
    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

}
