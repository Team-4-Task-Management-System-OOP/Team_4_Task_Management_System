package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;

public interface Feedback extends Task {

    int getRating();

    FeedbackStatus getStatus();

    void changeRating(int rating);

    void changeStatus(FeedbackStatus status);
}
