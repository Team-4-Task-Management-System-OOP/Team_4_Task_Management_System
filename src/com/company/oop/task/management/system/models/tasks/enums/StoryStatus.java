package com.company.oop.task.management.system.models.tasks.enums;

public enum StoryStatus {
    NOTDONE,
    INPROGESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NOTDONE:
                return "Not done";
            case INPROGESS:
                return "In progress";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException("Invalid Story Status. " +
                        "Story Status can be: Not Done, In Progress, Low.");
        }
    }
}
