package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.tests.utils.TestUtilities;

import static com.company.oop.task.management.system.models.tasks.TaskBase.DESCRIPTION_MIN_LENGTH;
import static com.company.oop.task.management.system.models.tasks.TaskBase.TITLE_MIN_LENGTH;

public class TaskBaseConstants {

    public static final String VALID_TASK_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH + 1);
    public static final String INVALID_TASK_TITLE = TestUtilities.getString(TITLE_MIN_LENGTH - 1);
    public static final String VALID_TASK_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH + 1);
    public static final String INVALID_TASK_DESCRIPTION = TestUtilities.getString(DESCRIPTION_MIN_LENGTH - 1);

}
