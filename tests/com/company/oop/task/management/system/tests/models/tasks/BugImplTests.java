package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.BugMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class BugImplTests {




    @Test
    public void constructor_Should_Throw_When_TitleIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new BugImpl( 1,
                        TaskBaseConstants.INVALID_TASK_TITLE,
                        TaskBaseConstants.VALID_TASK_DESCRIPTION,
                        VALID_REPRODUCIBLE_STEPS,
                        VALID_BUG_PRIORITY_TYPE,
                        VALID_BUG_SEVERITY));
    }
    @Test
    public void constructor_Should_Throw_When_DescriptionIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new BugImpl( 1,
                        TaskBaseConstants.VALID_TASK_TITLE,
                        TaskBaseConstants.INVALID_TASK_DESCRIPTION,
                        VALID_REPRODUCIBLE_STEPS,
                        VALID_BUG_PRIORITY_TYPE,
                        VALID_BUG_SEVERITY));
    }
    @Test
    public void constructor_Should_Throw_When_ReproducibleStepsAreInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new BugImpl( 1,
                        TaskBaseConstants.VALID_TASK_TITLE,
                        TaskBaseConstants.VALID_TASK_DESCRIPTION,
                        INVALID_REPRODUCIBLE_STEPS,
                        VALID_BUG_PRIORITY_TYPE,
                        VALID_BUG_SEVERITY));
    }

}
