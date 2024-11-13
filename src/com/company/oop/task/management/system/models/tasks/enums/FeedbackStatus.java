package com.company.oop.task.management.system.models.tasks.enums;

public enum FeedbackStatus {
    NEW, UNSCHEDULED, SCHEDULED, DONE;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException("Invalid Feedback Status. Feedback Status can be:" +
                        " New, Unscheduled, Scheduled, Done.");
        }
    }
}