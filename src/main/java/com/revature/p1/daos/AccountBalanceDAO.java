package com.revature.p1.daos;

import com.revature.p1.models.account.Account;

import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.util.factory.ConnectionFactory;
import com.revature.querinator.GenericObjectMaker;
import com.revature.querinator.PostgresQueryBuilder;

import java.lang.reflect.InvocationTargetException;
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

    private PostgresQueryBuilder queryMaker;
    private GenericObjectMaker objectMaker;

    /**
     *
     * Description: When creating a new bank account this will initialize the accounts balance record.
     *
     * @param bal
     */
    public void saveNewBalance(AccountBalance bal) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            queryMaker.insert(bal);

//            String sqlInsertAcctBal = "insert into account_balance" +
//                    "(account_id , balance) values (?,0)";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);
//
//            pstmt.setInt(1,acct.getaID());
//            pstmt.executeUpdate();


        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * Description: Updates the accounts balance record within the database.
     *
     * @param bal
     * @return
     */
    public boolean saveBalance(AccountBalance bal) {

        boolean success = false;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            success = queryMaker.update(bal);

//            String sqlInsertAcctBal = "update account_balance " +
//                    "set balance = ? where account_id = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);
//
//            pstmt.setDouble(1, currBalance);
//            pstmt.setInt(2, acct.getaID());
//            pstmt.executeUpdate();


        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        return success;

    }

    /**
     *
     * Description: Gets the balance of the desired account from the database.
     *
     * @param bal
     * @return AccountBalance
     */
    public AccountBalance getBalance(AccountBalance bal) {

        System.out.println("in getbalance in dao " + bal.getAcctID());

        AccountBalance currentBalance = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            objectMaker = new GenericObjectMaker();
            currentBalance = (AccountBalance) objectMaker.buildObject(AccountBalance.class, queryMaker.selectByPrimaryKey(bal));

//            String sqlInsertAcctBal = "select balance " +
//                    "from account_balance where account_id = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcctBal);
//
//            pstmt.setInt(1, acct.getaID());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                balance = rs.getDouble("balance");
//            }


        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("balance " + currentBalance.getBalance());

        return currentBalance;

    }
}
