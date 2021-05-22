package com.revature.p0.services;

import com.revature.p0.exceptions.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/11/2021
 * Time: 1:36 PM
 * Description: Ensures user input is valid within menu selections.
 */
public class UserInputService {

    public UserInputService() {
    }

    /**
     *
     * Description: Ensures user input is valid within menu selections.
     *
     * @param usrInput
     * @return boolean
     * @throws InvalidRequestException
     */
    public boolean validateInput(int largestOption, String usrInput) throws InvalidRequestException {

        if (!isSelectionInputValid(largestOption, usrInput) && !isExitValid(usrInput)) {
            throw new InvalidRequestException("Invalid selection provided!");
        }

        return true;
    }

    /**
     *
     * Description: Ensures user input is valid.
     *
     * @param usrInput
     * @return boolean
     */
    public boolean isSelectionInputValid(int largestOption, String usrInput) {

        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(usrInput);

        if (usrInput == null || usrInput.trim().isEmpty() || !m.matches() || Integer.parseInt(usrInput) > largestOption) return false;

        return true;
    }

    /**
     *
     * Description: Ensures user input is valid.
     *
     * @param usrInput
     * @return boolean
     */
    public boolean isExitValid(String usrInput) {

        if (usrInput == null || usrInput.trim().isEmpty() || !usrInput.equalsIgnoreCase("e")) return false;

        return true;
    }

}
