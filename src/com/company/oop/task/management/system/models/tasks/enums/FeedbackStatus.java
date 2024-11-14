package com.company.oop.task.management.system.models.tasks.enums;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;

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
                throw new InvalidUserInputException("Invalid Feedback Status. Feedback Status can be:" +
                        " New, Unscheduled, Scheduled, Done.");
        }
    }
}