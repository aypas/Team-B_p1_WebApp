package com.revature.p1.daos;

import com.revature.p1.exceptions.DataSourceException;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.util.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/7/2021
 * Time: 8:28 AM
 * Description: Access user information in the database
 */
public class BankUserDAO {

    /**
     *
     * Description: Adds a new user to the database
     *
     * @param newUser
     * @return BankUser
     */
    public void save(BankUser newUser) {

        System.out.println("in bank userdao save " + newUser.toString());

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sqlInsertUser = "insert into user_table" +
                                    "(email , first_name , last_name, username, password) values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[] { "id" });

            pstmt.setString(1,newUser.getEmail());
            pstmt.setString(2,newUser.getfName());
            pstmt.setString(3,newUser.getlName());
            pstmt.setString(4,newUser.getuName());
            pstmt.setString(5,newUser.getPassword());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    newUser.setuID(rs.getInt("id"));
                }
            }

//            String sqlInsertLogin = "insert into user_login" +
//                    "(user_id , username , password) values (?,?,crypt(?, gen_salt('bf')))";
//            pstmt = conn.prepareStatement(sqlInsertLogin);

//            pstmt.setInt(1,newUser.getuID());
//            pstmt.setString(2,newUser.getuName());
//            pstmt.setString(3,newUser.getPassword());

//            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException();
        }
    }

    /**
     *
     * Description: Checks database for an existing username
     *
     * @param username
     * @return boolean
     */
    public boolean isUsernameAvailable(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from user_table where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    /**
     *
     * Description: Checks database for an existing email
     *
     * @param email
     * @return boolean
     */
    public boolean isEmailAvailable(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from user_table where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     *
     * Description: Checks database and ensures proper login credentials were given
     *
     * @param username, password
     * @return BankUser
     */
    public BankUser findUserByUsernameAndPassword(String username, String password) {
        System.out.println("bank user dao " + username+password);
        BankUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

//            String encryptedPass = null;

            String sql = "select id, first_name, last_name, email, username, password from user_table where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new BankUser();
                user.setuID(rs.getInt("id"));
                user.setfName(rs.getString("first_name"));
                user.setlName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setuName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

}
