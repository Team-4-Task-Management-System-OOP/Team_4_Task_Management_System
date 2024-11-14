package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

public enum StoryStatus {
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In progress";
            case DONE:
                return "Done";
            default:
                throw new InvalidUserInputException("Invalid Story Status. " +
                        "Story Status can be: Not Done, In Progress, Low.");
        }
    }
}
