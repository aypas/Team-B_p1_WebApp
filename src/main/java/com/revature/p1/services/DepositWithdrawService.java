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
public class DepositWithdrawService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService xActionService;

    public DepositWithdrawService(AccountBalanceDAO balanceDAO, AccountTransactionDAO xActionDAO) {
        this.balanceDAO = balanceDAO;
        this.xActionService = new AccountTransactionService(xActionDAO);
    }

    public DepositWithdrawService(AccountBalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }


    /**
     *
     * Description: If entry is valid this will send the data to the database.
     *
     * @param
     * @return boolean
     * @throws InvalidRequestException
     */
    public AccountBalance createBalance(BankUser bankUser, int aID, double depositAmt) throws InvalidRequestException {
        //perhaps we should un-nest the getbalance method
            //its a little challenging to scale, perhaps?
            //**coding decision**
        /**
         * Since each route is validated
         *  just pass current user as arg here - I think that may make tying in ORM simpler?
         */

//        System.out.println("in create balance "+ aID);
        if (!isDepositValid(depositAmt)) {
            throw new InvalidRequestException("Invalid Deposit Amount Entered");
        }

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAcctID(aID);
        accountBalance.setBalance(depositAmt);

        //tie in current user id to make it more secure?
        AccountBalance balance = balanceDAO.getBalance(accountBalance);
        System.out.println("balance ret in dw swervice " + balance.getBalance());
        double newBalance = balance.getBalance() + depositAmt;


        // Sends extra information to transaction table in the database.
//        xActionService.sendBalanceAsTransaction(depositAmt, "Deposit");
       //neewd to send account_id
        accountBalance.setAcctID(aID);
        accountBalance.setBalance(newBalance);
        balanceDAO.saveBalance(accountBalance);



        return accountBalance;
    }

    /**
     *
     * Description: Ensures user input is valid
     *
     * @param amount
     * @return boolean
     */
    public boolean isDepositValid(double amount) {
//        String regex = "[0-9]*(\\.[0-9]{0,2})?";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(usrInput);

//        if (amount == null || amount.trim().isEmpty() || amount.contains("-") || usrInamountput.contains(" ") || !m.matches()) return false;

        if(Math.abs(amount) < 1){
            return false;
        }

        return true;
    }
}
