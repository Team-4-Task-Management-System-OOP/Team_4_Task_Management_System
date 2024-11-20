package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import static com.company.oop.task.management.system.models.tasks.FeedbackImpl.RATING_MIN;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.INVALID_TASK_DESCRIPTION;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.INVALID_TASK_TITLE;

public class FeedbackMock {

    public static final int VALID_FEEDBACK_RATING = RATING_MIN + 1;
    public static final TaskType VALID_FEEDBACK_TASK_TYPE = TaskType.FEEDBACK;

    public static final int INVALID_FEEDBACK_RATING = RATING_MIN - 1;
    public static final TaskType INVALID_FEEDBACK_TASK_TYPE = TaskType.STORY;

    public static FeedbackImpl getFeedbackMock() {
        return new FeedbackImpl(1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_FEEDBACK_RATING);
    }

    public static FeedbackImpl getInvalidFeedbackMockWithInvalidArguments() {
        return new FeedbackImpl(
                1,
                INVALID_TASK_TITLE,
                INVALID_TASK_DESCRIPTION,
                INVALID_FEEDBACK_RATING
        );
    }

    public static FeedbackImpl getInvalidTitleFeedbackMock() {
        return new FeedbackImpl(
                1,
                INVALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_FEEDBACK_RATING
        );
    }

    public static FeedbackImpl getInvalidDescriptionFeedbackMock() {
        return new FeedbackImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                INVALID_TASK_DESCRIPTION,
                VALID_FEEDBACK_RATING
        );
    }

    public static FeedbackImpl getInvalidRatingFeedbackMock() {
        return new FeedbackImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                INVALID_FEEDBACK_RATING
        );
    }

}

