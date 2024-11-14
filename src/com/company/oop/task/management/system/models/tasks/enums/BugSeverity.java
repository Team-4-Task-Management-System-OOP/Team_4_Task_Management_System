package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

public enum BugSeverity {
    CRITICAL, MAJOR, MINOR;

    @Override
    public String toString() {
        switch (this) {
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            default:
                throw new InvalidUserInputException("Invalid Bug Severity. " +
                        "Bug Severity can be: Critical, Major or Minor");
        }
    }
}
