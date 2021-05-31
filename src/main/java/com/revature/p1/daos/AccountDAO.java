package com.revature.p1.daos;

import com.revature.p1.models.account.Account;
import com.revature.p1.models.account.AccountBalance;
import com.revature.p1.models.account.AccountTransaction;
import com.revature.p1.models.account.BankUser;
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
 * Time: 3:27 PM
 * Description: Interacts with the account table within the database.
 */
public class AccountDAO {

    private PostgresQueryBuilder queryMaker;
    private GenericObjectMaker objectMaker;

    /**
     *
     * Description: Saves a new bank account to the database.
     *
     * @param acct
     * @return Account
     */
    public Account saveNewAcct(Account acct) {

        AccountBalanceDAO balanceDAO = new AccountBalanceDAO();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            int aId = queryMaker.insertAndGetId(acct);
            System.out.println("savene acct aid " + aId);
            acct.setaID(aId);
            balanceDAO.saveNewBalance(new AccountBalance(aId, 0));

//            String sqlInsertAcct = "insert into account " +
//                    "(user_id , type_id, acct_name) values (?,?,?)";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAcct, new String[] { "id" });
//
//            pstmt.setInt(1,newAcct.getuID());
//            pstmt.setInt(2, newAcct.gettID());
//            pstmt.setString(3, newAcct.getaName());
//
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted != 0) {
//                ResultSet rs = pstmt.getGeneratedKeys();
//                while (rs.next()) {
//                    newAcct.setaID(rs.getInt("id"));
//                }
//            }
//
//            balanceDAO.saveNewBalance(newAcct);

        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        return acct;

    }

    /**
     *
     * Description: Gets all of the accounts related to a specific user from the database.
     *
     * @param bankUser
     * @return Array of accounts
     */
    public List<Account> getAcct(BankUser bankUser) {

//        Account[] accts = null;
//        Account acct = null;
//        int count = 0;
//        int rsCounter = 0;

        List<Account> allAccounts = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            queryMaker = new PostgresQueryBuilder(conn);
            objectMaker = new GenericObjectMaker();
            Account example = new Account();
            Object[] fkInfo = {"user_id", bankUser.getuID()};
            allAccounts = objectMaker.buildObjects(AccountTransaction.class, queryMaker.getObjectByForeignKey(example, fkInfo));

//            String sqlGetNumOfAccts = "select count(*) " +
//                    "from account where user_id = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sqlGetNumOfAccts);
//            pstmt.setInt(1,bankUser.getuID());
//
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                count = rs.getInt("count");
//            }
//
//            accts = new Account[count];
//
//            String sqlGetAcct = "select * " +
//                    "from account where user_id = ?";
//            pstmt = conn.prepareStatement(sqlGetAcct);
//
//            pstmt.setInt(1,bankUser.getuID());
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                acct = new Account();
//
//                acct.setaID(rs.getInt("id"));
//                acct.setaName(rs.getString("acct_name"));
//                acct.setuID(rs.getInt("user_id"));
//                acct.setjUID(rs.getInt("joint_user_id"));
//                acct.settID(rs.getInt("type_id"));
//
//                accts[rsCounter] = acct;
//                rsCounter++;
//            }

        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

        return allAccounts;

    }
}
