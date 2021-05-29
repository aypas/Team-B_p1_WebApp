package com.revature.p1.daos;

import com.revature.p1.models.account.Account;

import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:01 PM
 * Description: Interacts with the account_balance table within the database.
 */
public class AccountBalanceDAO {

    /**
     * Description: When creating a new bank account this will initialize the accounts balance record.
     *
     * @param acct
     */
    public void saveNewBalance(Account acct) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcctBal = "insert into account_balance" +
                    "(account_id , balance) values (?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);

            pstmt.setInt(1, acct.getaID());
            pstmt.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Description: Updates the accounts balance record within the database.
     *
     * @param
     * @param accountBalance
     * @return
     */
    public void saveBalance(AccountBalance accountBalance) {

//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sqlInsertAcctBal = "update account_balance " +
//                    "set balance = ? where account_id = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);
//
//            //was getting account from singleton before, so logic is very similar
//            pstmt.setDouble(1, currBalance);
//            pstmt.setInt(2, aID);
//            pstmt.executeUpdate();
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    /**
     * Description: Gets the balance of the desired account from the database.
     *
     * @param
     * @return double
     */
    public AccountBalance getBalance(int aID) {

        System.out.println("in get balance");
        AccountBalance accountBalance = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlInsertAcctBal = "select account_id, balance from account_balance where account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);

            pstmt.setInt(1, aID);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                accountBalance = new AccountBalance();
                accountBalance.setAcctID(rs.getInt("account_id"));
                accountBalance.setBalance(rs.getDouble("balance"));
            }

            System.out.println(accountBalance.toString());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return accountBalance;

    }
}
