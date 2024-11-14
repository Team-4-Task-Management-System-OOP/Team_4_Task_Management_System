package com.company.oop.task.management.system.models.tasks.enums;

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
                throw new IllegalArgumentException("Invalid Story Size. Story size" +
                        " can be: Large, Medium, small.");
        }
    }
}
