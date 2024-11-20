package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;


public class StoryMock {

    public static final PriorityType VALID_STORY_PRIORITY_TYPE = PriorityType.HIGH;
    public static final StorySize VALID_STORY_SIZE = StorySize.LARGE;
    public static final PriorityType INVALID_STORY_PRIORITY_TYPE = null;
    public static final StorySize INVALID_STORY_SIZE = null;

    public static StoryImpl getStoryMock() {
        return new StoryImpl( 1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_STORY_PRIORITY_TYPE,
                VALID_STORY_SIZE);
    }

}
