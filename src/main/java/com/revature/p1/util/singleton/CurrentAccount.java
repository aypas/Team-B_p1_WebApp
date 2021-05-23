package com.revature.p1.util.singleton;

import com.revature.p1.models.account.Account;


/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/10/2021
 * Time: 10:47 AM
 * Description: Singleton for holding the currently selected bank account.
 */
public class CurrentAccount {

    private static CurrentAccount acctSingleton = new CurrentAccount();
    private Account currentAccount = null;

    private CurrentAccount() {

    }

    /**
     *
     * Description: Returns the singleton container
     *
     * @return CurrentAccount
     */
    public static CurrentAccount getInstance() {
        return acctSingleton;
    }

    /**
     *
     * Description: Gets the current bank account from the singleton object
     *
     * @return Account
     */
    public Account getCurrentAccount() { return currentAccount; }


    /**
     *
     * Description: Sets the current bank account inside the singleton
     *
     * @param currAcct
     */
    public void setCurrentAccount(Account currAcct) {
        currentAccount = currAcct;
    }

}
