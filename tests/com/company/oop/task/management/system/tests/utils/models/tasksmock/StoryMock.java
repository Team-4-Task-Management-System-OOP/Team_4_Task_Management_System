package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.INVALID_TASK_DESCRIPTION;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.INVALID_TASK_TITLE;


public class StoryMock {

    public static final PriorityType VALID_STORY_PRIORITY_TYPE = PriorityType.HIGH;
    public static final StorySize VALID_STORY_SIZE = StorySize.LARGE;

    public static StoryImpl getValidStoryMock() {
        return new StoryImpl(1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY_TYPE,
                VALID_STORY_SIZE
        );
    }

    public static StoryImpl getFullyInvalidStoryMock() {
        return new StoryImpl(
                1, // Valid ID
                INVALID_TASK_TITLE, // Invalid (empty) title
                INVALID_TASK_DESCRIPTION, // Invalid (null) description
                null, // Invalid (null) priority
                null // Invalid (null) size
        );
    }

    public static StoryImpl getInvalidPriorityStoryMock() {
        return new StoryImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                null,
                VALID_STORY_SIZE
        );
    }

    public static StoryImpl getInvalidTitleStoryMock() {
        return new StoryImpl(
                1,
                INVALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY_TYPE,
                VALID_STORY_SIZE
        );
    }

    public static StoryImpl getInvalidDescriptionStoryMock() {
        return new StoryImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                INVALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY_TYPE,
                VALID_STORY_SIZE
        );
    }

}
