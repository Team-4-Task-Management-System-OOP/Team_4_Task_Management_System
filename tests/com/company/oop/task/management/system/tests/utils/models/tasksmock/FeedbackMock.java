package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.FeedbackImpl;

import static com.company.oop.task.management.system.models.tasks.FeedbackImpl.RATING_MIN;

public class FeedbackMock {
    public static final int VALID_FEEDBACK_RATING = RATING_MIN + 1;
    public static final int INVALID_FEEDBACK_RATING = RATING_MIN - 1;

    public static FeedbackImpl getFeedbackMock() {
        return new FeedbackImpl( 1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_FEEDBACK_RATING);
    }
}
