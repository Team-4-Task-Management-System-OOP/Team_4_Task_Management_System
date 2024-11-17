package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validatePositive;
import static java.lang.String.format;


public class FeedbackImpl extends TaskBase implements Feedback {

    //Constants
    public static final String RATING_ERR = "%d is an invalid number! Rating must be a positive number!";
    public static final String FEEDBACK_STATUS_CHANGED = "Feedback Status changed from %s to %s successfully.";
    public static final String RATING_CHANGED = "Feedback's feedbackRating changed from %d to %d successfully.";
    public static final String FEEDBACK_STATUS_ALREADY_SET = "Feedback status is already set to %s!";
    public static final String RATING_ALREADY_SET = "Feedback's feedbackRating is already set to %s!";
    public static final String FEEDBACK_STATUS_CANNOT_BE_EMPTY = "Feedback Status cannot be empty.";

    //Fields
    private int feedbackRating;
    private FeedbackStatus feedbackStatus;

    //Constructor
    public FeedbackImpl(int id, String title, String description, int feedbackRating) {
        super(id, title, description);
        setFeedbackRating(feedbackRating);
        this.feedbackStatus = FeedbackStatus.NEW;
    }

    //Setters
    private void setFeedbackRating(int feedbackRating) {
        validatePositive(feedbackRating, (format(RATING_ERR, feedbackRating)));
        this.feedbackRating = feedbackRating;
    }

    //Getters
    @Override
    public int getFeedbackRating() {
        return feedbackRating;
    }

    @Override
    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.FEEDBACK;
    }

    //Methods
    @Override
    public void changeFeedbackRating(int ratingNew) {
        validatePositive(ratingNew, (format(RATING_ERR, ratingNew)));
        if (getFeedbackRating() != ratingNew){
            super.historyLogger(format(RATING_CHANGED, getFeedbackRating(), ratingNew));
            feedbackRating = ratingNew;
        }
        else {
            throw new InvalidUserInputException(format(RATING_ALREADY_SET, getFeedbackRating()));
        }
    }

    @Override
    public void changeFeedbackStatus(FeedbackStatus feedbackStatusNew) {
        if (feedbackStatusNew == null) {
            throw new InvalidUserInputException(FEEDBACK_STATUS_CANNOT_BE_EMPTY);
        }
        if (feedbackStatusNew != getFeedbackStatus()){
            super.historyLogger(format(FEEDBACK_STATUS_CHANGED, getFeedbackStatus(), feedbackStatusNew));
            feedbackStatus = feedbackStatusNew;
        }
        else {
            throw new InvalidUserInputException(format(FEEDBACK_STATUS_ALREADY_SET, getFeedbackStatus()));
        }
    }

    //Print
    @Override
    public String printImportantInfo() {
        return format("%s" +
                "Rating: %d%n" +
                "Feedback Status: %s%n", super.printImportantInfo(), getFeedbackRating(), getFeedbackStatus());
    }
}
