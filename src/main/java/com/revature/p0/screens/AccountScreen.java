package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.services.UserInputService;
import com.revature.p0.util.singleton.CurrentAccount;
import com.revature.p0.util.singleton.LoggedInUser;

import java.io.BufferedReader;

import static com.revature.p0.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:10 AM
 * Description: Shows a screen that allows users to manipulate or display data regarding their accounts.
 */
public class AccountScreen extends Screen {

    private UserInputService inputService;
    private AccountDAO acctDao = new AccountDAO();
    private BufferedReader consoleReader;
    private BankUser user;

    public AccountScreen(BufferedReader consoleReader, UserInputService inputService) {
        super("AccountScreen", "/accounts");
        this.consoleReader = consoleReader;
        this.inputService = inputService;
    }

    @Override
    public void render() {

        Account[] userAccounts;

        try {

            userAccounts = acctDao.getAcct(LoggedInUser.getInstance().getLoggedInUser());

            getSelectionScreen(userAccounts);

            String userSelection = consoleReader.readLine();

            try{

                inputService.validateInput(userAccounts.length, userSelection);

                if (userSelection.equalsIgnoreCase("e")) {

                    System.out.println("Have a beautiful time!");
                    app().setAppRunning(false);
                    return;

                }

                if (userSelection.equals("0")) {

                    System.out.println("Routing to account opening screen...");
                    app().getRouter().navigate("/openAcct");

                } else {

                    System.out.println();
                    CurrentAccount.getInstance().setCurrentAccount(userAccounts[Integer.parseInt(userSelection) - 1]);
                    showAccountInfo(CurrentAccount.getInstance().getCurrentAccount());

                    userSelection = consoleReader.readLine();

                    inputService.validateInput(3, userSelection);

                    switch (userSelection) {

                        case "e":
                            System.out.println("Have a beautiful time!");
                            Thread.sleep(1000);
                            app().setAppRunning(false);
                            break;

                        case "0":
                            app().getRouter().navigate("/accounts");
                            break;

                        case "1":
                            System.out.println("Routing to deposit screen...");
                            app().getRouter().navigate("/deposit");
                            break;

                        case "2":
                            System.out.println("Routing to withdraw screen...");
                            app().getRouter().navigate("/withdraw");
                            break;

                        case "3":
                            System.out.println("Routing to transaction screen...");
                            app().getRouter().navigate("/transactions");
                            break;
                    }

                }

            } catch (InvalidRequestException ire) {
                System.out.println("Invalid selection provided, please try again.");
                app().getRouter().navigate("/accounts");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getSelectionScreen(Account[] userAccounts) {

        int counter = 1;

        System.out.println("Select the account you want to view.");
        System.out.println("+----------------------------------+");

        for (Account acct : userAccounts) {
            System.out.println(counter + " - " + acct.getaName());

            counter++;
        }

        System.out.println("0 - Open a new account.");
        System.out.println("e - Exit");

        System.out.print("> ");
    }

    private void showAccountInfo(Account acct) {

        AccountBalanceDAO balanceDAO = new AccountBalanceDAO();

        System.out.printf("Account Name: %s\nBalance: $%.2f", acct.getaName(), balanceDAO.getBalance(acct));
        System.out.println("");

        System.out.println("1 - Deposit");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Transaction History");
        System.out.println("0 - Return to Account Selection");
        System.out.println("e - Exit");

        System.out.print("> ");

    }
}
