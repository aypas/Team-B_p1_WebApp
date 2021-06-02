package com.revature.p1.services;

import com.revature.p1.daos.AccountBalanceDAO;
import com.revature.p1.exceptions.InvalidRequestException;
import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.models.account.BankUser;

/**
 * This class validates data for Deposits and Withdrawals before sending it to appropriate DAO's
 */

public class DepositWithdrawService {

    AccountBalanceDAO balanceDAO;
    AccountTransactionService accountTransactionService;

    public DepositWithdrawService(AccountBalanceDAO balanceDAO, AccountTransactionService accountTransactionService) {
        this.balanceDAO = balanceDAO;
        this.accountTransactionService = accountTransactionService;
    }

    public AccountBalance createBalance(BankUser bankUser, int aID, double ammount, String transType) throws InvalidRequestException {
        AccountBalance accountBalance = new AccountBalance();
        AccountTransaction accountTransaction = new AccountTransaction();
        try {
            if (!isDepositValid(ammount, transType)) {
                throw new InvalidRequestException("Invalid Deposit Amount Entered");
            }

            if (transType.compareTo("withdraw") == 0) {
                ammount = -ammount;
            }

            accountBalance.setAcctID(aID);
            accountBalance.setBalance(ammount);

            AccountBalance balance = balanceDAO.getBalance(accountBalance);

            double newBalance = balance.getBalance() + ammount;

            accountBalance.setAcctID(aID);
            accountBalance.setBalance(newBalance);
            if (!balanceDAO.saveBalance(accountBalance)) {
                accountBalance.setBalance(balance.getBalance());
                throw new InvalidRequestException("Invalid transaction amount.");
            }
            accountTransaction.setTransactionAmt(ammount);
            accountTransaction.setDescription(transType);
            accountTransaction.setAcctID(aID);

            accountTransactionService.sendBalanceAsTransaction(accountTransaction);

        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
        return accountBalance;
    }

    /**
     * Description: Ensures user input is valid
     *
     * @param amount
     * @return boolean
     */
    public boolean isDepositValid(double amount, String transType) {

        if (amount < 1) {
            return false;
        }

        return true;
    }
}
