package com.revature.p1.services;

import com.revature.p1.daos.UserDAO;
import com.revature.p1.exceptions.*;
import com.revature.p1.models.account.BankUser;
import com.revature.p1.util.factory.ConnectionFactory;
import com.revature.p1.util.singleton.LoggedInUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.revature.p1.Driver.app;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/9/2021
 * Time: 4:04 PM
 * Description: Assures the users user account creation input is valid before persisting to the database.
 */
public class BankUserService {

    private UserDAO userDao;

    public BankUserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * Description: If entry is valid this will send the data to the database
     *
     * @param newUser
     * @return BankUser
     * @throws InvalidRequestException
     */
    public void register(BankUser newUser) {
        System.out.println("in bankuserwervice register " + newUser.toString());


        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid new user data provided!");
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            if (!userDao.isUsernameAvailable(newUser.getuName())) {
                throw new UsernameUnavailableException();
            }

            if (!userDao.isEmailAvailable(newUser.getEmail())) {
                throw new EmailUnavailableException();
            }
            System.out.println("in bankuserwervice register before return");

            userDao.save(conn, newUser);
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new ResourcePersistenceException();
        } catch (UsernameUnavailableException | EmailUnavailableException e) {
            e.printStackTrace();
        }
    }

    public BankUser authenticate(String username, String password) throws AuthenticationException {

        try {
            BankUser authenticatedUser = userDao.findUserByUsernameAndPassword(username, password);

            return authenticatedUser;

        } catch (DataSourceException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

//    public boolean isUsernamePasswordValid(String username, String password){
//        if (username == null ||username.trim().isEmpty() || username.length() > 15) return false;
//        if (password == null || password.trim().isEmpty() || password.length() > 72) return false;
//
//        return true;
//
//    }


    /**
     * Description: Ensures user input is valid
     *
     * @param user
     * @return boolean
     */
    public boolean isUserValid(BankUser user) {
        if (user == null) return false;
        if (user.getuName() == null || user.getuName().trim().isEmpty() || user.getuName().length() > 15) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 72)
            return false;
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getEmail().length() > 50) return false;
        if (user.getfName() == null || user.getfName().trim().isEmpty() || user.getfName().length() > 50) return false;
        if (user.getfName() == null || user.getfName().trim().isEmpty() || user.getfName().length() > 50) return false;

        /*
            Regular expression evaluation email input...
            Example #5 on: https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
         */

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }


}
