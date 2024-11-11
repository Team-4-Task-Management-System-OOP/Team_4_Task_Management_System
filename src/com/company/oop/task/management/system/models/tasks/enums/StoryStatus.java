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
                return "";
        }
    }
}
