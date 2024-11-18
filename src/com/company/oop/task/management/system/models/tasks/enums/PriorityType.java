package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

public enum PriorityType {
    HIGH, MEDIUM, LOW;

    public static final String INVALID_PRIORITY = "Invalid Priority. Priority can be: High, Medium, Low.";

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            default:
                throw new InvalidUserInputException(INVALID_PRIORITY);
        }
    }

}
