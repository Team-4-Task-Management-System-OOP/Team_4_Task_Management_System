package com.company.oop.task.management.system.exceptions;

public class InvalidUserInputException extends RuntimeException {

    public InvalidUserInputException() {
    }

    public InvalidUserInputException(String message) {
        super(message);
    }

}
