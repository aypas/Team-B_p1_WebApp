package com.revature.p1.util.singleton;

import com.revature.p1.models.account.BankUser;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 6:04 PM
 * Description: Singleton containing the current logged in user.
 */
public class LoggedInUser {

    private static LoggedInUser userSingleton = new LoggedInUser();
    private BankUser loggedInUser = null;

    private LoggedInUser() {

    }

    /**
     *
     * Description: Returns the singleton container holding the logged in user.
     *
     * @return LoggedInUser
     */
    public static LoggedInUser getInstance() {
        return userSingleton;
    }

    /**
     *
     * Description: Gets the logged in user from this singleton.
     *
     * @return BankUser
     */
    public BankUser getLoggedInUser() { return loggedInUser; }


    /**
     *
     * Description: Sets the currently logged in user within a singleton.
     *
     * @param currUser
     */
    public void setLoggedInUser(BankUser currUser) {
        loggedInUser = currUser;
    }

}
