package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

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
                throw new InvalidUserInputException("Invalid Bug Status. Bug Status can be either" +
                        " ''Active'' or ''Done''");
        }
    }
}
