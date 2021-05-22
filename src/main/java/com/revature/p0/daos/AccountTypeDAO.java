package com.revature.p0.daos;

import com.revature.p0.models.account.AccountType;
import com.revature.p0.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:16 PM
 * Description: Interacts with the account_type table in the database
 */
public class AccountTypeDAO {

    /**
     *
     * Description: Brings back all available account types from the database
     *
     * @return array of account types
     */
    public AccountType[] getAllAcctTypes() {
        AccountType[] acctTypes = null;
        AccountType acctType = null;
        int numOfTypes = 0;
        int rsCounter = 0;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sqlCountAcctTypes = "select count(*)" +
                    "from account_type";
            PreparedStatement pstmt = conn.prepareStatement(sqlCountAcctTypes);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                numOfTypes = rs.getInt("count");
            }

            acctTypes = new AccountType[numOfTypes];

            String sqlGetAcctTypes = "select *" +
                    "from account_type";
            pstmt = conn.prepareStatement(sqlGetAcctTypes);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                acctType = new AccountType();

                acctType.setId(rs.getInt("id"));
                acctType.setType(rs.getString("acct_type"));
                acctType.setInterest(rs.getDouble("interest"));
                acctType.setMonthlyFees(rs.getDouble("monthly_fee"));

                acctTypes[rsCounter] = acctType;

                rsCounter++;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acctTypes;
    }

}
