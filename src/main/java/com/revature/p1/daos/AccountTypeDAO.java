package com.revature.p1.daos;

import com.revature.p1.models.account.AccountType;
import com.revature.p1.util.factory.ConnectionFactory;
import com.revature.querinator.PostgresQueryBuilder;

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

    /**
     *
     * Description: Brings back all available account types from the database
     *
     * @return array of account types
     */
    public List<AccountType> getAllAcctTypes() {
        List<Map<String, Object>> rawRows;
        List<AccountType> accountTypes = new ArrayList<>();




        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            queryMaker = new PostgresQueryBuilder(conn);
            rawRows = queryMaker.selectAllFromTable(new AccountType());

            for (Map<String, Object> row : rawRows) {
                accountTypes.add(new AccountType((int) row.get("id"), (String) row.get("acct_type"), (double) row.get("monthly_fee"), (double) row.get("interest")));
            }

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
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        return accountTypes;
    }

}
