package com.revature.p1.screens;

import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.services._WithdrawService;

import java.io.BufferedReader;


import static com.revature.p1.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 4:45 PM
 * Description: Screen for making withdraws
 */
public class WithdrawScreen extends Screen{

    private BufferedReader consoleReader;
    private _WithdrawService withdrawService;

    public WithdrawScreen(BufferedReader consoleReader, _WithdrawService withdrawService) {
        super("DepositScreen", "/withdraw");
        this.consoleReader = consoleReader;
        this.withdrawService = withdrawService;
    }

    /**
     *
     * Description: Displays the withdraw screen for the end user.
     *
     */
    public void render() {

        try {
            System.out.println();
            System.out.println("How much would you like to withdraw?");
            System.out.println("+--------------------------------+");
            System.out.print("> $");
            String userSelection = consoleReader.readLine();

            try {
                if (withdrawService.createBalance(userSelection)) {
                    System.out.println("Withdraw Success");
                    app().getRouter().navigate("/accounts");
                }
            } catch (InvalidRequestException ire) {
                System.out.println("Invalid amount, try again.");
                app().getRouter().navigate("/withdraw");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
