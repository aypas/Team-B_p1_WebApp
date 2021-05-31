package com.revature.p1.daos;

import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.util.factory.ConnectionFactory;
import com.revature.querinator.GenericObjectMaker;
import com.revature.querinator.PostgresQueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:15 PM
 * Description: Interacts with the account_transaction table within the database.
 */
public class AccountTransactionDAO {

    private PostgresQueryBuilder queryMaker;
    private GenericObjectMaker objectMaker;

    /**
     *
     * Description: Gets all of the transactions relating to a specific user bank account.
     *
     * @param acct
     * @return array of transactions
     */
    public List<AccountTransaction> getAllAcctTransactions(Account acct) {

         List<AccountTransaction> allTransactions = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            objectMaker = new GenericObjectMaker();
            AccountTransaction example = new AccountTransaction();
            Object[] fkInfo = {"account_id", acct.getaID()};

            // could probably use a getObjectsByFK method in ORM to better fit OOP principles but this should work fine
            allTransactions = objectMaker.buildObjects(AccountTransaction.class, queryMaker.getObjectByForeignKey(example, fkInfo));

        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

        return allTransactions;
    }

    /**
     *
     * Description: Saves a new transaction to the database.
     *
     * @param transaction
     */
    public boolean saveTransaction(AccountTransaction transaction) {

        System.out.println("in save trans dao " + transaction.toString());

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            queryMaker.insert(transaction);


        } catch (SQLException | IllegalAccessException throwables) {
            //LEAVE THIS COMMENTED OUT
//            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
