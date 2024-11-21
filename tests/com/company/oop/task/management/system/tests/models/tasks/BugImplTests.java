package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.BugImpl;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Bug;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.BugStatus;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.BugMock.*;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.CommentMock.getInvalidCommentMock;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.CommentMock.getValidCommentMock;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.VALID_TASK_DESCRIPTION;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.VALID_TASK_TITLE;

public class BugImplTests {

    private BugImpl bug;

    @BeforeEach
    public void setUpFeedbackImplConstructor() {
        bug = getValidBugMock();
    }

    @Test
    public void constructor_Should_Throw_When_TitleIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> getInvalidTitleBugMock());
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> getInvalidDescriptionBugMock());
    }

    @Test
    public void constructor_Should_Throw_When_ReproducibleStepsAreInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> getInvalidReproducibleStepsBugMock());
    }

    @Test
    public void bugImpl_Should_ImplementBugInterface() {

        Assertions.assertTrue(bug instanceof Bug);
    }

    @Test
    public void bugImpl_Should_ImplementTaskInterface() {

        Assertions.assertTrue(bug instanceof Task);
    }

    @Test
    public void constructor_Should_Throw_When_PriorityIsNull() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> getInvalidPriorityBugMock());
    }

    @Test
    public void constructor_Should_ReturnValidTaskType_WhenTaskTypeIsValid() {
        // Arrange, Act, Assert
        Assertions.assertEquals(bug.getTaskType(), VALID_BUG_TASK_TYPE);
    }

    @Test
    public void constructor_Should_NotEqual_WhenTaskTypeIsValid() {
        // Arrange, Act, Assert
        Assertions.assertNotEquals(bug.getTaskType(), INVALID_BUG_TASK_TYPE);
    }

    @Test
    void getTaskType_Should_ReturnBug_ForAllInstances() {
        // Arrange
        BugImpl bug1 = getValidBugMock();
        BugImpl bug2 = getValidBugMock();

        // Act & Assert
        Assertions.assertEquals(TaskType.BUG, bug1.getTaskType());
        Assertions.assertEquals(TaskType.BUG, bug2.getTaskType());
    }

    @Test
    public void should_Create_Feedback_When_ValidValuesArePassed() {
        // Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, bug.getId()),
                () -> Assertions.assertEquals(VALID_TASK_TITLE, bug.getTitle()),
                () -> Assertions.assertEquals(VALID_TASK_DESCRIPTION, bug.getDescription()),
                () -> Assertions.assertEquals(VALID_REPRODUCIBLE_STEPS, bug.getReproducibleSteps()),
                () -> Assertions.assertEquals(VALID_BUG_PRIORITY_TYPE, bug.getPriority()),
                () -> Assertions.assertEquals(VALID_BUG_SEVERITY, bug.getBugSeverity())
        );
    }

    @Test
    public void equals_Should_Return_True_When_ValidObjectsOfBugAreCompared() {
        // Act, Assert
        Assertions.assertTrue(getValidBugMock().equals(bug));;
    }

    @Test
    public void toString_Should_PrintCorrectStringOrder_When_ParametersAreCorrect() {

        String expectedToString = String.format("%s" +
                        "Comments: %s%n" +
                        "History: %s%n",
                bug.printImportantInfo(), bug.printComments(), bug.printLogHistory());

        // Assert
        Assertions.assertEquals(expectedToString, bug.toString());
    }

    @Test
    void changeBugStatus_Should_ChangeCorrectlyBugStatus_When_ParametersAreCorrect() {
        bug.changeBugStatus(BugStatus.DONE);
        Assertions.assertEquals(BugStatus.DONE, bug.getBugStatus());

        bug.changeBugStatus(BugStatus.ACTIVE);
        Assertions.assertEquals(BugStatus.ACTIVE, bug.getBugStatus());
    }

    @Test
    void changeBugStatus_Should_ThrowException_When_StatusIsAlreadySet() {
        bug.changeBugStatus(BugStatus.DONE);
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.changeBugStatus(BugStatus.DONE));

    }

    @Test
    void changeBugStatus_Should_ThrowException_When_NewStatusIsInvalid() {
        bug.changeBugStatus(BugStatus.DONE);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {BugStatus invalidStatus = BugStatus.valueOf("INVALID");
                    bug.changeBugStatus(invalidStatus);
                });

    }

    @Test
    void changeFeedbackStatus_Should_ThrowException_When_StatusIsNull() {
        bug.changeBugStatus(BugStatus.DONE);
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.changeBugStatus(null));
    }


    @Test
    void changePriority_Should_ChangeCorrectlyPriority_When_ParametersAreCorrect() {
        bug.changePriority(PriorityType.LOW);
        Assertions.assertEquals(PriorityType.LOW, bug.getPriority());

        bug.changePriority(PriorityType.MEDIUM);
        Assertions.assertEquals(PriorityType.MEDIUM, bug.getPriority());
    }

    @Test
    void changePriority_Should_ThrowException_When_PriorityNewIsAlreadySet() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.changePriority(PriorityType.HIGH));

    }

    @Test
    void changePriority_Should_ThrowException_When_PriorityIsInvalid() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.changePriority(null));

    }

    @Test
    void addComment_Should_AddCommentsCorrectly() {
        CommentImpl comment = getValidCommentMock();
        CommentImpl comment2 = getValidCommentMock();

        bug.addComment(comment);
        bug.addComment(comment2);

        Assertions.assertEquals(bug.getComments().size(), 2);
    }

    @Test
    void addComment_Should_ThrowException_When_CommentIsInvalid() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.addComment(getInvalidCommentMock()));
    }

    @Test
    void addComment_Should_ThrowException_When_CommentIsNull() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> bug.addComment(new CommentImpl()));
    }

    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {

        // Act
        bug.getComments().add(getValidCommentMock());

        // Assert
        Assertions.assertEquals(0, bug.getComments().size());
    }
}
