package com.revature.p1.services;

import com.revature.p1.daos.AccountTransactionDAO;
import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountTransaction;

import java.util.List;

/**
 *  This class passes data for getting and saving user transactions to appropriate DAO's
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

        boolean result;

        result = transactionDAO.saveTransaction(newTransaction);

        return result;
    }

    public List<AccountTransaction> getTransactions(Account account){

        List<AccountTransaction> allTransactions =   transactionDAO.getAllAcctTransactions(account);

        return allTransactions;
    }
}
