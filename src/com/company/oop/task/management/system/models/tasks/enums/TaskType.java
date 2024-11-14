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
                return "Bug";
            case STORY:
                return "Story";
            case FEEDBACK:
                return "Feedback";
            default:
                throw new InvalidUserInputException("Invalid Task Type. " +
                        "Task Type can be: Bug, Story, Feedback.");
        }
    }
}
