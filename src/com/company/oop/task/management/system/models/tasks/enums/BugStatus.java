package com.company.oop.task.management.system.models.tasks.enums;

public enum BugStatus {
    ACTIVE, DONE;
    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException("Invalid Priority. Priority can be: High, Medium, Low.");
        }
    }
}
