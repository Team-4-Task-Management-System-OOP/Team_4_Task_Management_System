package com.company.oop.task.management.system.utils;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

import java.util.List;

public class ValidationHelpers {
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. " +
            "Expected: %d, received: %d.";

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new InvalidUserInputException(message);
        }
    }

    public static void validateStringLength(String stringToValidate, double minLength, double maxLength, String errorMessage) {
        validateDecimalRange(stringToValidate.length(), minLength, maxLength, errorMessage);
    }

    public static void validateDecimalRange(double value, double min, double max, String message) {
        if (value < min || value > max) {
            throw new InvalidUserInputException(message);
        }
    }

    public static void validatePositive(int value, String errorMessage) {
        if (value <= 0) {
            throw new InvalidUserInputException(errorMessage);
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new InvalidUserInputException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }
}
