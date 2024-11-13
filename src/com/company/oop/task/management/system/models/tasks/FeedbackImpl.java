package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validatePositive;
import static java.lang.String.format;


public class FeedbackImpl extends TaskBase implements Feedback {
    private static final String RATING_ERR = "%d is an invalid number! Rating must be a positive number!";

    private int rating;
    private FeedbackStatus feedbackStatus;
    private TaskType taskType;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatus feedbackStatus) {
        super(id, title, description);
        setRating(rating);
        this.feedbackStatus = feedbackStatus;
    }

    private void setRating(int rating) {
        validatePositive(rating, (format(RATING_ERR, rating)));
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public FeedbackStatus getStatus() {
        return feedbackStatus;
    }

    //ToDo
    @Override
    public void changeRating(int rating) {

    }

    //ToDo
    @Override
    public void changeStatus(FeedbackStatus status) {
        if (status.toString() != FeedbackStatus.DONE.toString()){

        }
    }

    @Override
    public TaskType getType() {
        return TaskType.FEEDBACK;
    }

    //ToDo
    @Override
    public String printInfo() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s",super.toString());
    }


}
