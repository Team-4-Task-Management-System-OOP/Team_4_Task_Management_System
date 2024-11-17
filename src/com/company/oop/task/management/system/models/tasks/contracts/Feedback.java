package com.company.oop.task.management.system.models.tasks.contracts;

import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;

public interface Feedback extends Task {

    int getFeedbackRating();

    FeedbackStatus getFeedbackStatus();

    void changeFeedbackRating(int rating);

    void changeFeedbackStatus(FeedbackStatus status);
}
