package com.revature.p1.daos;

import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:15 PM
 * Description: Interacts with the account_transaction table within the database.
 */
public class AccountTransactionDAO {

    /**
     *
     * Description: Gets all of the transactions relating to a specific user bank account.
     *
     * @param acct
     * @return array of transactions
     */
    public AccountTransaction[] getAllAcctTransactions(Account acct) {
        AccountTransaction[] acctTransactions = null;
        AccountTransaction acctTransaction = null;
        int numOfTransactions = 0;
        int rsCounter = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlCountAcctTransactions = "select count(*) " +
                    "from account_Transaction where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlCountAcctTransactions);

            pstmt.setInt(1, acct.getaID());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                numOfTransactions = rs.getInt("count");
            }

            acctTransactions = new AccountTransaction[numOfTransactions];

            String sqlGetAcctTransactions = "select * " +
                    "from account_Transaction where account_id = ? " +
                    "order by id desc;";
            pstmt = conn.prepareStatement(sqlGetAcctTransactions);

            pstmt.setInt(1, acct.getaID());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                acctTransaction = new AccountTransaction();

                acctTransaction.setTransactionID(rs.getInt("id"));
                acctTransaction.setAcctID(rs.getInt("account_id"));
                acctTransaction.setTransactionAmt(rs.getDouble("transaction_amt"));
                acctTransaction.setDescription(rs.getString("description"));

                acctTransactions[rsCounter] = acctTransaction;

                rsCounter++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acctTransactions;
    }

    /**
     *
     * Description: Saves a new transaction to the database.
     *
     * @param transaction
     */
    public void saveTransaction(AccountTransaction transaction) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertTransaction = "insert into account_transaction" +
                    "(account_id , transaction_amt, description) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertTransaction);

            pstmt.setInt(1, transaction.getAcctID());
            pstmt.setDouble(2, transaction.getTransactionAmt());
            pstmt.setString(3, transaction.getDescription());
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
