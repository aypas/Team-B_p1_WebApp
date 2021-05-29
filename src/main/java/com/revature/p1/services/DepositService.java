package com.revature.p1.services;

import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.util.singleton.CurrentAccount;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 1:39 PM
 * Description: Assures the users deposit input is valid before persisting to the database.
 */
public class DepositService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService xActionService;

    public DepositService(AccountBalanceDAO balanceDAO, AccountTransactionDAO xActionDAO) {
        this.balanceDAO = balanceDAO;
        this.xActionService = new AccountTransactionService(xActionDAO);
    }

    public DepositService(AccountBalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }


    /**
     *
     * Description: If entry is valid this will send the data to the database.
     *
     * @param usrInput
     * @return boolean
     * @throws InvalidRequestException
     */
    public boolean createBalance(BankUser bankUser, String usrInput) throws InvalidRequestException {
        System.out.println("in create balance "+ bankUser.getuName());
        //perhaps we should un-nest the getbalance method
            //its a little challenging to scale, perhaps?
            //**coding decision**
        /**
         * Since each route is validated
         *  just pass current user as arg here - I think that may make tying in ORM simpler?
         */

        if (!isDepositValid(usrInput)) {
            throw new InvalidRequestException("Invalid Deposit Amount Entered");
        }

        double newBalance = balanceDAO.getBalance(new AccountBalance(CurrentAccount.getInstance().getCurrentAccount().getaID(), 0)).getBalance() + Double.parseDouble(usrInput);

        // Sends extra information to transaction table in the database.
        xActionService.sendBalanceAsTransaction(usrInput, "Deposit");
       //neewd to send account_id
        return balanceDAO.saveBalance(new AccountBalance(CurrentAccount.getInstance().getCurrentAccount().getaID(), newBalance));

    }

    /**
     *
     * Description: Ensures user input is valid
     *
     * @param usrInput
     * @return boolean
     */
    public boolean isDepositValid(String usrInput) {

        String regex = "[0-9]*(\\.[0-9]{0,2})?";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(usrInput);

        if (usrInput == null || usrInput.trim().isEmpty() || usrInput.contains("-") || usrInput.contains(" ") || !m.matches()) return false;

        return true;
    }
}
