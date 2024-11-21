package com.company.oop.task.management.system.tests.utils.models.tasksmock;

import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.enums.BugSeverity;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;

import java.util.List;

public class BugMock {

    public static final List<String> VALID_REPRODUCIBLE_STEPS = List.of("test1,test2,test3");
    public static final PriorityType VALID_BUG_PRIORITY_TYPE = PriorityType.HIGH;
    public static final BugSeverity VALID_BUG_SEVERITY = BugSeverity.CRITICAL;
    public static final TaskType VALID_BUG_TASK_TYPE = TaskType.BUG;
    public static final TaskType INVALID_BUG_TASK_TYPE = TaskType.STORY;

    public static final List<String>  INVALID_REPRODUCIBLE_STEPS = List.of();
    public static final PriorityType INVALID_BUG_PRIORITY_TYPE = null;
    public static final BugSeverity INVALID_BUG_SEVERITY = null;

    public static BugImpl getValidBugMock() {
        return new BugImpl( 1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_REPRODUCIBLE_STEPS,
                VALID_BUG_PRIORITY_TYPE,
                VALID_BUG_SEVERITY);
    }

    public static BugImpl getInvalidBugMockAllWithInvalidArguments() {
        return new BugImpl(
                1,
                "",
                null,
                INVALID_REPRODUCIBLE_STEPS,
                INVALID_BUG_PRIORITY_TYPE,
                INVALID_BUG_SEVERITY
        );
    }

    public static BugImpl getInvalidTitleBugMock() {
        return new BugImpl(
                1,
                "",
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_REPRODUCIBLE_STEPS,
                VALID_BUG_PRIORITY_TYPE,
                VALID_BUG_SEVERITY
        );
    }

    public static BugImpl getInvalidDescriptionBugMock() {
        return new BugImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                null,
                VALID_REPRODUCIBLE_STEPS,
                VALID_BUG_PRIORITY_TYPE,
                VALID_BUG_SEVERITY
        );
    }

    public static BugImpl getInvalidReproducibleStepsBugMock() {
        return new BugImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                INVALID_REPRODUCIBLE_STEPS,
                VALID_BUG_PRIORITY_TYPE,
                VALID_BUG_SEVERITY
        );
    }

    public static BugImpl getInvalidPriorityBugMock() {
        return new BugImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_REPRODUCIBLE_STEPS,
                INVALID_BUG_PRIORITY_TYPE,
                VALID_BUG_SEVERITY
        );
    }

    public static BugImpl getInvalidSeverityBugMock() {
        return new BugImpl(
                1,
                TaskBaseConstants.VALID_TASK_TITLE,
                TaskBaseConstants.VALID_TASK_DESCRIPTION,
                VALID_REPRODUCIBLE_STEPS,
                VALID_BUG_PRIORITY_TYPE,
                INVALID_BUG_SEVERITY
        );
    }
}
