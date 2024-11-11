package com.company.oop.task.management.system.utils;

public class ParsingHelpers {
    private static final String NO_SUCH_ENUM = "There is no %s in %ss.";
    private static final int NEGATIVE_NUMBER = 0;
    private static final String NEGATIVE_INT_ERROR = "%s should be non negative.";

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static int tryParseInt(String valueToParse, String errorMessage) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validatePositiveDouble(double value, String type) {
        if (value < NEGATIVE_NUMBER) {
            throw new IllegalArgumentException(String.format(NEGATIVE_INT_ERROR, type));
        }
    }

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type) {
        try {
            return Enum.valueOf(type, valueToParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(NO_SUCH_ENUM, valueToParse, type.getSimpleName()));
        }
    }
}
