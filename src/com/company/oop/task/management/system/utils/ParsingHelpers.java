package com.company.oop.task.management.system.utils;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParsingHelpers {
    private static final String NO_SUCH_ENUM = "There is no ''%s'' in %ss.";
    private static final int NEGATIVE_NUMBER = 0;
    private static final String NEGATIVE_INT_ERROR = "%s should be a positive number.";
    private static final String INVALID_BOOLEAN_ERR = "Invalid value for ''%s''. Should be either true or false.";

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(errorMessage);
        }
    }

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(errorMessage);
        }
    }

    public static boolean tryParseBoolean(String valueToParse, String parameterName) {
        if (!valueToParse.equalsIgnoreCase("true") &&
                !valueToParse.equalsIgnoreCase("false")) {
            throw new InvalidUserInputException(String.format(INVALID_BOOLEAN_ERR, parameterName));
        }

        return Boolean.parseBoolean(valueToParse);
    }

    public static void validatePositiveDouble(double value, String type) {
        if (value < NEGATIVE_NUMBER) {
            throw new InvalidUserInputException(String.format(NEGATIVE_INT_ERROR, type));
        }
    }

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }

    public static String formatTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

        return dateTime.format(formatter);
    }
}
