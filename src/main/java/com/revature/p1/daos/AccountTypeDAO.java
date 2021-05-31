package com.revature.p1.daos;

import com.revature.p1.models.account.AccountType;
import com.revature.p1.util.factory.ConnectionFactory;
import com.revature.querinator.GenericObjectMaker;
import com.revature.querinator.PostgresQueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 4:16 PM
 * Description: Interacts with the account_type table in the database
 */
public class AccountTypeDAO {

    private PostgresQueryBuilder queryMaker;
    private GenericObjectMaker objectMaker;

    /**
     *
     * Description: Brings back all available account types from the database
     *
     * @return array of account types
     */
    public List<AccountType> getAllAcctTypes() {

        List<AccountType> accountTypes = null;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            queryMaker = new PostgresQueryBuilder(conn);
            objectMaker = new GenericObjectMaker();
            accountTypes = objectMaker.buildObjects(AccountType.class, queryMaker.selectAllFromTable(new AccountType()));

            System.out.println("account type list "+ accountTypes.size());

//            String sqlCountAcctTypes = "select count(*)" +
//                    "from account_type";
//            PreparedStatement pstmt = conn.prepareStatement(sqlCountAcctTypes);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                numOfTypes = rs.getInt("count");
//            }
//
//            acctTypes = new AccountType[numOfTypes];
//
//            String sqlGetAcctTypes = "select *" +
//                    "from account_type";
//            pstmt = conn.prepareStatement(sqlGetAcctTypes);
//
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                acctType = new AccountType();
//
//                acctType.setId(rs.getInt("id"));
//                acctType.setType(rs.getString("acct_type"));
//                acctType.setInterest(rs.getDouble("interest"));
//                acctType.setMonthlyFees(rs.getDouble("monthly_fee"));
//
//                acctTypes[rsCounter] = acctType;
//
//                rsCounter++;
//            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

        return accountTypes;
    }

}
