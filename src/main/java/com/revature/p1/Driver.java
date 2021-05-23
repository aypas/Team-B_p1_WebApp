package com.revature.p1;

import com.revature.p1.util.scenemgmt.AppState;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 4/29/2021
 * Time: 9:06 AM
 * Description: Runs the application
 */
///adding text so to get branch up to main
public class Driver {
    private static AppState app = new AppState();

    public static void main(String[] args) {
        while (app.isAppRunning()) {
            app.getRouter().navigate("/welcome");
        }
    }

    /**
     *
     * Description: Gives access to the applications state throughout the program
     *
     * @return AppState
     */
    public static AppState app() {
        return app;
    }
}
