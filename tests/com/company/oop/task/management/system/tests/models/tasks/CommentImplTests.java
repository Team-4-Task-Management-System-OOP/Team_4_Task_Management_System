package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.CommentImpl;
import com.company.oop.task.management.system.models.tasks.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.oop.task.management.system.models.tasks.CommentImpl.COMMENT_HEADLINE;
import static com.company.oop.task.management.system.tests.utils.models.tasksmock.CommentMock.*;

public class CommentImplTests {

    private static final String CORRECT_AUTHOR = "Method should return the correct author.";
    private static final String CORRECT_FORMATTED_STRING = "Method should return the correct formatted string.";
    private CommentImpl comment;

    @BeforeEach
    public void setUpMemberImplConstructor(){
        comment = getCommentMock();
    }

    @Test
    public void commentImpl_Should_ImplementCommentInterface() {
        // Assert
        Assertions.assertTrue(comment instanceof Comment);
    }

    @Test
    public void constructor_Should_ThrowException_When_ModelNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(InvalidUserInputException.class, () -> new CommentImpl(
                INVALID_CONTENT,
                VALID_AUTHOR
        ));
    }

    @Test
    public void constructor_Should_CreateNewComment_When_ParametersAreCorrect() {
        // Assert
        Assertions.assertEquals(VALID_CONTENT, comment.getContent());
    }

    @Test
    public void getAuthor_Should_ReturnCorrectAuthor_WhenParametersAreCorrect() {
        // Act
        String actualAuthor = comment.getAuthor();

        // Assert
        Assertions.assertEquals(VALID_AUTHOR, actualAuthor, CORRECT_AUTHOR);
    }

    @Test
    public void toString_Should_ReturnCorrectStringOrder_When_ParametersAreCorrect() {

        String expectedToString = String.format(COMMENT_HEADLINE +
                "Author: %s%n" +
                "Content: %s%n" +
                "-------------", comment.getAuthor(), comment.getContent());

        // Assert
        Assertions.assertEquals(expectedToString, comment.toString(),
                CORRECT_FORMATTED_STRING);
    }

}
