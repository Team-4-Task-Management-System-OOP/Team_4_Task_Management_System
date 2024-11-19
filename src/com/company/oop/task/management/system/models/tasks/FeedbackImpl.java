package com.company.oop.task.management.system.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.contracts.Member;

import static com.company.oop.task.management.system.utils.ValidationHelpers.validateIntRange;
import static java.lang.String.format;


public class FeedbackImpl extends TaskBase implements Feedback {

    public static final String FEEDBACK_STATUS_CHANGED = "Feedback Status changed from \"%s\" to \"%s\" successfully.";
    public static final String RATING_CHANGED = "Feedback's feedbackRating changed from \"%d\" to \"%d\" successfully.";
    public static final String FEEDBACK_STATUS_ALREADY_SET = "Feedback status is already set to \"%s\"!";
    public static final String RATING_ALREADY_SET = "Feedback's feedbackRating is already set to \"%s\"!";
    public static final String FEEDBACK_STATUS_CANNOT_BE_EMPTY = "Feedback Status cannot be empty.";
    public static final String RATING_ERR = "Feedback rating is invalid. It must be between %d and %d.";
    public static final String CANNOT_ASSIGN_FEEDBACK = "Cannot assign feedback to any member " +
            "because feedback is not an assignable Task!";
    public static final String CANNOT_UNASSIGN_FEEDBACK = "Cannot unassign feedback to any member " +
            "because feedback is not an assignable Task!";
    public static final int RATING_MIN = 1;
    public static final int RATING_MAX = 10;

    private int feedbackRating;
    private FeedbackStatus feedbackStatus;

    public FeedbackImpl(int id, String title, String description, int feedbackRating) {
        super(id, title, description);
        setFeedbackRating(feedbackRating);
        this.feedbackStatus = FeedbackStatus.NEW;
    }

    private void setFeedbackRating(int feedbackRating) {
        validateIntRange(feedbackRating, RATING_MIN, RATING_MAX,
                (format(RATING_ERR, RATING_MIN, RATING_MAX)));
        this.feedbackRating = feedbackRating;
    }

    @Override
    public int getFeedbackRating() {
        return feedbackRating;
    }

    @Override
    public FeedbackStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    @Override
    public void assignMember(Member assignee) {
        throw new InvalidUserInputException(CANNOT_ASSIGN_FEEDBACK);
    }

    @Override
    public void unassignMember(){
        throw new InvalidUserInputException(CANNOT_UNASSIGN_FEEDBACK);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.FEEDBACK;
    }

    @Override
    public void changeFeedbackRating(int ratingNew) {
        validateIntRange(feedbackRating, RATING_MIN, RATING_MAX,
                (format(RATING_ERR, RATING_MIN, RATING_MAX)));
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

    @Override
    public String printImportantInfo() {
        return format("%s" +
                "Rating: %d%n" +
                "Feedback Status: %s%n", super.printImportantInfo(), getFeedbackRating(), getFeedbackStatus());
    }
}
