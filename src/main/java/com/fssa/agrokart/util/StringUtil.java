package com.fssa.agrokart.util;

import com.fssa.agrokart.error.ProductValidatorErrors;
import com.fssa.agrokart.exception.InvalidProductDataException;
import com.fssa.agrokart.validator.ProductFieldNames;

import java.util.regex.Pattern;

/**
 * A class which holds the basic method to validate a string like
 * is the string is empty or null
 * @author HemanathMuralikrishnan
 */

public class StringUtil {

    /**
     * @param input is string
     * @return if the input is null or after trimming both ends if the string is empty then this will
     * return null, Otherwise it will return the trimmed input to the other methods
     */
    public String trimString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        return input.trim();
    }

    /**
     *
     * @param input is string
     * @param pattern is string consists of regex pattern
     * @return if matches true other false
     */
    public boolean StringWithRegex(String input, String pattern) {

//		calling the trim string method to trim the string
        String trimmedInput = trimString(input);

//		matching the input with the given regex pattern
//		if it matches then matches method return true
//		otherwise it will return false
        boolean isMatch = Pattern.matches(pattern, trimmedInput);

//		if the input doesn't match the pattern then return false
        if (!isMatch)
           return false;

//		if the input matches the regex pattern, then return true
        return true;

    }

}
