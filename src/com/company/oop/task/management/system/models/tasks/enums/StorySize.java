package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

public enum StorySize {
    LARGE,
    MEDIUM,
    SMALL;

    @Override
    public String toString() {
        switch (this) {
            case LARGE:
                return "Large";
            case MEDIUM:
                return "Medium";
            case SMALL:
                return "Small";
            default:
                throw new InvalidUserInputException("Invalid Story Size. Story size" +
                        " can be: Large, Medium, small.");
        }
    }
}
