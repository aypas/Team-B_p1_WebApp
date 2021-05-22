package com.revature.p0.screens;

import static com.revature.p0.Driver.app;

import java.io.BufferedReader;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/5/2021
 * Time: 9:09 AM
 * Description: Welcome screen
 */
public class WelcomeScreen extends Screen {

    private BufferedReader consoleReader;

    public WelcomeScreen(BufferedReader consoleReader) {
        super("WelcomeScreen", "/welcome");
        this.consoleReader = consoleReader;
    }

    /**
     *
     * Description: Displays the welcome screen for the end user.
     *
     */
    @Override
    public void render() {

        System.out.println("Welcome to the bank!");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit application");

        try {
            System.out.print("> ");
            String userSelection = consoleReader.readLine();

            switch (userSelection) {
                case "1":
                    System.out.println("Navigating to login screen");
                    app().getRouter().navigate("/login");
                    break;
                case "2":
                    System.out.println("Navigating to register screen");
                    app().getRouter().navigate("/register");
                    break;
                case "3":
                    System.out.println("Exiting application!");
                    // we need to figure out how to tell the app the shutdown
//                    System.exit(0); // very bad practice; force closes the JVM
                    app().setAppRunning(false);
                    break;
                default:
                    System.out.println("Invalid selection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
