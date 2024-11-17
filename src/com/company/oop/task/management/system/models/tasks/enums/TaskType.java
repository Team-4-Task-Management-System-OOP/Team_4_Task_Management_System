package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

public enum TaskType {
    BUG,
    STORY,
    FEEDBACK;

    @Override
    public String toString() {
        switch (this) {
            case BUG:
                return "BUG";
            case STORY:
                return "STORY";
            case FEEDBACK:
                return "FEEDBACK";
            default:
                throw new InvalidUserInputException("Invalid Task Type. " +
                        "Task Type can be: Bug, Story, Feedback.");
        }
    }
}
