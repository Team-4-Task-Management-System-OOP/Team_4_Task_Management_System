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
                throw new IllegalArgumentException("Invalid Priority. Priority can be: High, Medium, Low.");
        }
    }
}
