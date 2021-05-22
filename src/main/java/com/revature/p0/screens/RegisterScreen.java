package com.revature.p0.screens;

import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.account.BankUser;
import com.revature.p0.services.BankUserService;

import java.io.BufferedReader;

import static com.revature.p0.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:09 AM
 * Description: User registration screen.
 */
public class RegisterScreen extends Screen {

    private BufferedReader consoleReader;
    private BankUserService userService;

    public RegisterScreen(BufferedReader consoleReader, BankUserService userService) {
        super("RegisterScreen", "/register");
        this.consoleReader = consoleReader;
        this.userService = userService;
    }

    /**
     *
     * Description: Displays the user registration screen to the end user.
     *
     */
    public void render() {

        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        int age;

        // ok but a little verbose
//        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//        BufferedReader consoleReader = new BufferedReader(inputStreamReader);

        try {
            // risky code that might through an exception

            System.out.println("Register for a new account!");
            System.out.println("+-------------------------+");

            System.out.print("First name: ");
            firstName = consoleReader.readLine(); // throws Exception here

            System.out.print("Last name: ");
            lastName = consoleReader.readLine();

            System.out.print("Email: ");
            email = consoleReader.readLine();

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            BankUser newUser = new BankUser(firstName, lastName, username, email, password);
            userService.register(newUser);

        } catch (InvalidRequestException ire) {
            System.out.println("Invalid entry, please try again.");
            app().getRouter().navigate("/welcome");

        } catch (ResourcePersistenceException rpe) {
            System.out.println("Username or email already taken.");
            app().getRouter().navigate("/welcome");

        } catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}
