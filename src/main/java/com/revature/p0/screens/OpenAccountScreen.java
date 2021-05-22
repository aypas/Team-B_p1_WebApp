package com.revature.p0.screens;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTypeDAO;
import com.revature.p0.models.account.Account;
import com.revature.p0.models.account.AccountType;
import com.revature.p0.services.AccountOpeningService;
import com.revature.p0.util.singleton.LoggedInUser;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.revature.p0.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 3:54 PM
 * Description: Displays the screen allowing users to open a new account
 */
public class OpenAccountScreen extends Screen{

    private AccountTypeDAO acctTypeDAO = new AccountTypeDAO();
    private Account newAcct = new Account();
    private BufferedReader consoleReader;
    private AccountOpeningService openService;


    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);

    public OpenAccountScreen(BufferedReader consoleReader, AccountOpeningService openService) {
        super("OpenAccountScreen", "/openAcct");
        this.consoleReader = consoleReader;
        this.openService = openService;
    }

    /**
     *
     * Description: Renders the bank account creation screen to the end user.
     *              Includes in place validation since it's the only time this type of
     *              validation is used.
     *
     */
    public void render() {

        try {

            AccountType[] acctTypes = acctTypeDAO.getAllAcctTypes();

            showAcctTypes(acctTypes);

            String userSelection = consoleReader.readLine();

            Matcher m = p.matcher(userSelection);

            while (Integer.parseInt(userSelection) < 0 || Integer.parseInt(userSelection) > 2 || !m.matches()) {

                System.out.println();
                System.out.println("Invalid selection.");
                System.out.println();
                showAcctTypes(acctTypes);

                userSelection = consoleReader.readLine();

                m = p.matcher(userSelection);

            }

            if (userSelection.equals("0")) {
                app().getRouter().navigate("/accounts");
            }

            newAcct.setuID(LoggedInUser.getInstance().getLoggedInUser().getuID());
            newAcct.settID(acctTypes[Integer.parseInt(userSelection) - 1].getId());

            System.out.print("Account Name: ");
            userSelection = consoleReader.readLine();

            while (userSelection.trim().length() < 1) {
                System.out.println();
                System.out.println("Please enter a proper name");
                System.out.println();
                System.out.print("Account Name: ");

                userSelection = consoleReader.readLine();
            }

            newAcct.setaName(userSelection);

            newAcct = openService.createAccount(newAcct);


            System.out.println("Account created successful!");
            app().getRouter().navigate("/accounts");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Description: Displays all the types of accounts available for creation.
     *
     * @param acctTypes
     */
    private void showAcctTypes(AccountType[] acctTypes) {

        int counter = 1;

        System.out.println("What type of account would you like to open?");
        System.out.println("+------------------------------------------+");

        for (AccountType acctType : acctTypes) {
            System.out.printf("%d - Type: %s | Interest: %.2f | Monthly Fee $%.2f\n", counter, acctType.getType(), acctType.getInterest(), acctType.getMonthlyFees());
            counter++;
        }

        System.out.println("0 - Account Menu");

        System.out.print("> ");
    }
}
