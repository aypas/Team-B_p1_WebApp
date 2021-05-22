package com.revature.p0.services;

import com.revature.p0.daos.AccountBalanceDAO;
import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.util.singleton.CurrentAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 3:02 PM
 * Description: Assures the users withdraw input is valid before persisting to the database.
 */
public class WithdrawService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService xActionService;

    public WithdrawService(AccountBalanceDAO balanceDAO, AccountTransactionDAO xActionDAO) {
        this.balanceDAO = balanceDAO;
        this.xActionService = new AccountTransactionService(xActionDAO);
    }

    /**
     *
     * Description: If entry is valid this will send the data to the database.
     *
     * @param usrInput
     * @return boolean
     * @throws InvalidRequestException
     */
    public boolean createBalance(String usrInput) throws InvalidRequestException {

        if (!isWithdrawValid(usrInput)) {
            throw new InvalidRequestException("Invalid Withdraw Amount Entered");
        }

        double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) - Double.parseDouble(usrInput);

        usrInput = "-" + usrInput;

        // Sends extra information to transaction table in the database.
        xActionService.sendBalanceAsTransaction(usrInput, "Withdraw");

        return balanceDAO.saveBalance(CurrentAccount.getInstance().getCurrentAccount(), newBalance);

    }

    /**
     *
     * Description: Ensures user input is valid
     *
     * @param usrInput
     * @return boolean
     */
    public boolean isWithdrawValid(String usrInput) {

        String regex = "[0-9]*(\\.[0-9]{0,2})?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(usrInput);

        if (usrInput == null || usrInput.trim().isEmpty() || usrInput.contains("-") || usrInput.contains(" ") || !m.matches()) return false;

        double newBalance = balanceDAO.getBalance(CurrentAccount.getInstance().getCurrentAccount()) - Double.parseDouble(usrInput);
        if (newBalance < 0) return false;

        return true;
    }



}
