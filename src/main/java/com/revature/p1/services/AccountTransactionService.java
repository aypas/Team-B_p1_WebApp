package com.revature.p1.services;

import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.models.account.AccountTransaction;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 3:35 PM
 * Description: Persists a transaction to the database.
 */
public class AccountTransactionService {

    private AccountTransactionDAO transactionDAO;

    public AccountTransactionService(AccountTransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    /**
     * Description: If entry is valid this will send the data to the database
     *
     * @param newTransaction
     * @return boolean
     */

    public boolean sendBalanceAsTransaction(AccountTransaction newTransaction) {
        System.out.println("in send balance atService " + newTransaction.toString());

//        AccountTransaction newTransaction = new AccountTransaction();

//        newTransaction.setAcctID(CurrentAccount.getInstance().getCurrentAccount().getaID());
//        newTransaction.setTransactionAmt(Double.parseDouble(transactionAmt));
//        newTransaction.setDescription(description);
        boolean result;

        result = transactionDAO.saveTransaction(newTransaction);
        System.out.println("result send bal as trans ats " + result);

        return result;
    }

}
