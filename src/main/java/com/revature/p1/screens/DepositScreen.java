package com.revature.p1.screens;

import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.services.DepositService;


import java.io.BufferedReader;
;

import static com.revature.p1.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 10:20 AM
 * Description: Screen for making deposits into a bank account
 */
public class DepositScreen extends Screen{

    private BufferedReader consoleReader;
    private DepositService depositService;

    public DepositScreen(BufferedReader consoleReader, DepositService depositService) {
        super("DepositScreen", "/deposit");
        this.consoleReader = consoleReader;
        this.depositService = depositService;
    }

    /**
     *
     * Description: Displays the screen that allows users to deposit money into their accounts.
     *
     */

    public void render() {

        try {
            System.out.println();
            System.out.println("How much would you like to deposit?");
            System.out.println("+--------------------------------+");
            System.out.print("> $");
            String userSelection = consoleReader.readLine();

            try {
                if (depositService.createBalance(userSelection)) {
                    System.out.println("Deposit Success");
                    app().getRouter().navigate("/accounts");
                }
            } catch (InvalidRequestException ire) {
                System.out.println("Invalid amount, try again.");
                app().getRouter().navigate("/deposit");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
