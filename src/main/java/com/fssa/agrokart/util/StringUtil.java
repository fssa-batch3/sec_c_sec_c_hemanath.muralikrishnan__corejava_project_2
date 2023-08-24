package com.fssa.agrokart.util;

import java.util.regex.Pattern;

/**
 * A utility class containing methods for basic string validation operations.
 * This class provides methods to validate strings for empty, null values, and regex patterns.
 *
 * @author HemanathMuralikrishnan
 */
public class StringUtil {

    /**
     * Trim the input string and check if it is empty or null.
     * If the input is null or becomes empty after trimming, this method returns null.
     * Otherwise, the trimmed input string is returned for further processing.
     *
     * @param input The string to be trimmed and validated.
     * @return The trimmed input string if it's not empty or null; otherwise, null.
     */
    public String trimString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }
        return input.trim();
    }

    /**
     * Check if the input string matches a given regular expression pattern.
     *
     * @param input   The string to be validated against the regex pattern.
     * @param pattern The regex pattern string to be matched against the input.
     * @return true if the input matches the regex pattern, otherwise false.
     */
    public boolean stringWithRegex(String input, String pattern) {
        // Call the trimString method to trim the input string
        String trimmedInput = trimString(input);

        // Match the input with the given regex pattern
        // If it matches, the matches method returns true; otherwise, false is returned.
        return Pattern.matches(pattern, trimmedInput);
    }
}
