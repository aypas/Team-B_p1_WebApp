package com.revature.p0.services;

import com.revature.p0.daos.AccountTransactionDAO;
import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.account.AccountTransaction;
import com.revature.p0.util.singleton.CurrentAccount;

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
     *
     * Description: If entry is valid this will send the data to the database
     *
     * @param transactionAmt, description
     * @return boolean
     */
    public boolean sendBalanceAsTransaction(String transactionAmt, String description) {

        AccountTransaction newTransaction = new AccountTransaction();

        newTransaction.setAcctID(CurrentAccount.getInstance().getCurrentAccount().getaID());
        newTransaction.setTransactionAmt(Double.parseDouble(transactionAmt));
        newTransaction.setDescription(description);

        transactionDAO.saveTransaction(newTransaction);

        return true;
    }
}
