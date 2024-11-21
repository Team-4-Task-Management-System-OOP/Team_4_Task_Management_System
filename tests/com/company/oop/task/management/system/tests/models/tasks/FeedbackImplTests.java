package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.FeedbackImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Feedback;
import com.company.oop.task.management.system.models.tasks.contracts.Task;
import com.company.oop.task.management.system.models.tasks.enums.FeedbackStatus;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.tests.utils.models.tasksmock.FeedbackMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.oop.task.management.system.tests.utils.models.tasksmock.CommentMock.getInvalidContentCommentMock;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.FeedbackMock.*;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.VALID_TASK_DESCRIPTION;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.TaskBaseConstants.VALID_TASK_TITLE;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeedbackImplTests {

    private FeedbackImpl feedback;

    @BeforeEach
    public void setUpFeedbackImplConstructor(){
        feedback = getFeedbackMock();
    }

    @Test
    public void feedbackImpl_Should_ImplementFeedbackInterface() {

        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void feedbackImpl_Should_ImplementTaskInterface() {

        Assertions.assertTrue(feedback instanceof Task);
    }

    @Test
    public void constructor_Should_Throw_When_TitleIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                FeedbackMock::getInvalidTitleFeedbackMock);
    }

    @Test
    public void constructor_Should_Throw_When_DescriptionIsOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                FeedbackMock::getInvalidDescriptionFeedbackMock);
    }

    @Test
    public void constructor_Should_Throw_When_RatingIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> getInvalidRatingFeedbackMock());
    }

    @Test
    public void constructor_Should_ReturnValidTaskType_WhenTaskTypeIsValid() {
        // Arrange, Act, Assert
        Assertions.assertEquals(feedback.getTaskType(), VALID_FEEDBACK_TASK_TYPE);
    }

    @Test
    public void constructor_Should_NotEqual_WhenTaskTypeIsValid() {
        // Arrange, Act, Assert
        Assertions.assertNotEquals(feedback.getTaskType(), INVALID_FEEDBACK_TASK_TYPE);
    }

    @Test
    void getTaskType_Should_ReturnFeedback_ForAllInstances() {
        // Arrange
        FeedbackImpl feedback1 = FeedbackMock.getFeedbackMock();
        FeedbackImpl feedback2 = FeedbackMock.getFeedbackMock();

        // Act & Assert
        Assertions.assertEquals(TaskType.FEEDBACK, feedback1.getTaskType());
        Assertions.assertEquals(TaskType.FEEDBACK, feedback2.getTaskType());
    }

    @Test
    public void should_Create_Bus_When_ValidValuesArePassed() {
        // Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, feedback.getId()),
                () -> Assertions.assertEquals(VALID_TASK_TITLE, feedback.getTitle()),
                () -> Assertions.assertEquals(VALID_TASK_DESCRIPTION, feedback.getDescription()),
                () -> Assertions.assertEquals(VALID_FEEDBACK_RATING, feedback.getFeedbackRating())
        );
    }

    @Test
    public void toString_Should_PrintCorrectStringOrder_When_ParametersAreCorrect() {

        String expectedToString = String.format("%s" +
                        "Comments: %s%n" +
                        "History: %s%n",
                feedback.printImportantInfo(), feedback.printComments(), feedback.printLogHistory());

        // Assert
        Assertions.assertEquals(expectedToString, feedback.toString());
    }

    @Test
    void changeFeedbackStatus_Should_ChangeCorrectlyFeedbackStatus_When_ParametersAreCorrect() {
        feedback.changeFeedbackStatus(FeedbackStatus.DONE);
        assertEquals(FeedbackStatus.DONE, feedback.getFeedbackStatus());

        feedback.changeFeedbackStatus(FeedbackStatus.UNSCHEDULED);
        assertEquals(FeedbackStatus.UNSCHEDULED, feedback.getFeedbackStatus());
    }

    @Test
    void changeFeedbackStatus_Should_ThrowException_When_StatusIsAlreadySet() {
        feedback.changeFeedbackStatus(FeedbackStatus.DONE);
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> feedback.changeFeedbackStatus(FeedbackStatus.DONE));

    }

    @Test
    void changeFeedbackStatus_Should_ThrowException_When_StatusIsNull() {
        feedback.changeFeedbackStatus(FeedbackStatus.DONE);
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> feedback.changeFeedbackStatus(null));
    }

    @Test
    void changeFeedbackRating_Should_ChangeCorrectlyRating_When_ParametersAreCorrect() {
        feedback.changeFeedbackRating(5);
        assertEquals(5, feedback.getFeedbackRating());

        feedback.changeFeedbackRating(6);
        assertEquals(6, feedback.getFeedbackRating());
    }

    @Test
    void changeFeedbackRating_Should_ThrowException_When_RatingNewIsAlreadySet() {
        feedback.changeFeedbackRating(5);
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> feedback.changeFeedbackRating(5));

    }

    @Test
    void changeFeedbackRating_Should_ThrowException_When_RatingNewIsInvalidNumber() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> feedback.changeFeedbackRating(120));

    }



}
