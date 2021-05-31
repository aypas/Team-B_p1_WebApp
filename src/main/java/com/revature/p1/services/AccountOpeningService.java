package com.revature.p1.services;

import com.revature.p1.daos.AccountDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.Account;

/**
 * *  This class validates data for account creation before sending it to appropriate DAO's
 */
public class AccountOpeningService {

    private AccountDAO acctDAO;

    public AccountOpeningService(AccountDAO acctDAO) {
        this.acctDAO = acctDAO;
    }

    /**
     *
     * Description: If entry is valid this will send the data to the database
     *
     * @param acct
     * @return Account
     * @throws InvalidRequestException
     */
    public Account createAccount(Account acct) throws InvalidRequestException {

        if (!isAccountValid(acct)) {
            throw new InvalidRequestException("Invalid Account Data Entered");
        }

        return acctDAO.saveNewAcct(acct);
    }

    /**
     *
     * Description: Ensures user input is valid
     *
     * @param acct
     * @return boolean
     */
    public boolean isAccountValid(Account acct) {

        if (acct == null) return false;
        if (acct.getaName() == null || acct.getaName().trim().isEmpty() || acct.getaName().length() > 50) return false;

        return true;

    }

}
