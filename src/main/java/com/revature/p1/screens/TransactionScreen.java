package com.revature.p1.screens;

import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.util.singleton.CurrentAccount;

import java.io.BufferedReader;

import static com.revature.p1.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 4:57 PM
 * Description: Transaction screen
 */
public class TransactionScreen extends Screen {

    private BufferedReader consoleReader;
    private AccountTransactionDAO transactionDAO;

    public TransactionScreen(BufferedReader consoleReader, AccountTransactionDAO transactionDAO) {
        super("DepositScreen", "/transactions");
        this.consoleReader = consoleReader;
        this.transactionDAO = transactionDAO;
    }

    /**
     *
     * Description: Displays the transaction screen for the end user.
     *
     */
    public void render() {

        try {
            System.out.println(CurrentAccount.getInstance().getCurrentAccount().getaName());
            AccountTransaction[] transactions = transactionDAO.getAllAcctTransactions(CurrentAccount.getInstance().getCurrentAccount());

            System.out.println("Transactions");
            System.out.println("+--------------------------------+");


            for (AccountTransaction xAction : transactions) {
                if (Double.toString(xAction.getTransactionAmt()).contains("-")) {
                    String negHandler = Double.toString(xAction.getTransactionAmt());
                    negHandler = negHandler.substring(1);
                    System.out.printf("-%s%-19s | %-13s\n", "$", negHandler, xAction.getDescription());

                } else {
                    System.out.printf("%s%-20.2f | %-14s\n","$" , xAction.getTransactionAmt(), xAction.getDescription());
                }
            }

            System.out.println();
            System.out.println("Enter 0 to go back to accounts screen");

            String userSelection = "1";

            while (!userSelection.equals("0")) {
                System.out.print(">");
                userSelection = consoleReader.readLine();
            }

            app().getRouter().navigate("/accounts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
