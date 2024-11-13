package com.company.oop.task.management.system.models.tasks.enums;

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
                throw new IllegalArgumentException("Invalid Bug Severity. " +
                        "Bug Severity can be: Critical, Major or Minor");
        }
    }
}
