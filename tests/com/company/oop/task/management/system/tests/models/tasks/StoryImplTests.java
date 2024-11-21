package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.company.oop.task.management.system.models.tasks.StoryImpl.*;
import static com.company.oop.task.management.system.models.tasks.contracts.Assignable.DEFAULT_ASSIGNEE;
import static com.company.oop.task.management.system.tests.utils.models.teamsmock.MemberMock.getValidMockMember;
import static com.company.oop.task.management.system.utils.ParsingHelpers.formatTime;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

public class StoryImplTests {
    private StoryImpl story;
    //ToDo
    private static Member DEFAULT_MEMBER = new MemberImpl("unassigned");

    @BeforeEach
    void setUp() {
        story = new StoryImpl(1,
                "Implement login feature",
                "Allow users to log in",
                PriorityType.HIGH,
                StorySize.LARGE);
    }

    @Test
    void testStoryInitialization() {
        assertNotNull(story);
        assertEquals(1, story.getId());
        assertEquals("Implement login feature", story.getTitle());
        assertEquals("Allow users to log in", story.getDescription());
        assertEquals(PriorityType.HIGH, story.getPriority());
        assertEquals(StorySize.LARGE, story.getStorySize());
        assertEquals(StoryStatus.NOT_DONE, story.getStoryStatus());
        assertEquals(DEFAULT_ASSIGNEE, story.getAssignee());
        assertEquals(TaskType.STORY, story.getTaskType());
    }

    @Test
    void testGetPriority() {
        assertEquals(PriorityType.HIGH, story.getPriority());
    }

    @Test
    void testGetStorySize() {
        assertEquals(StorySize.LARGE, story.getStorySize());
    }

    @Test
    void testGetStoryStatus() {
        assertEquals(StoryStatus.NOT_DONE, story.getStoryStatus());
    }

    @Test
    void testGetAssignee() {
        assertEquals(DEFAULT_ASSIGNEE, story.getAssignee());
    }

    @Test
    void testGetTaskType() {
        assertEquals(TaskType.STORY, story.getTaskType());
    }

    @Test
    void testChangeStoryStatus() {
        story.changeStoryStatus(StoryStatus.IN_PROGRESS);
        assertEquals(StoryStatus.IN_PROGRESS, story.getStoryStatus());

        story.changeStoryStatus(StoryStatus.DONE);
        assertEquals(StoryStatus.DONE, story.getStoryStatus());
    }

    @Test
    void testChangeStoryStatusInvalid() {
        StoryStatus currentStatus = story.getStoryStatus();
        try {
            story.changeStoryStatus(null);
            fail("Expected an InvalidUserInputException for null status.");
        } catch (InvalidUserInputException e) {
            assertEquals("Story's Status cannot be empty!", e.getMessage());
        }
        assertEquals(currentStatus, story.getStoryStatus());
    }

    @Test
    void testAssignMember_Should_Throw_When_AlreadyAssigned() {
        story.assignMember(getValidMockMember());

        InvalidUserInputException exception = Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> story.assignMember(getValidMockMember())
        );

        Assertions.assertEquals((format(ALREADY_ASSIGNED_STORY,
                story.getTitle(), story.getAssignee().getName())), exception.getMessage());
    }

    @Test
    void testAssignMember_Should_Throw_When_AssigneeIsNull() {
        InvalidUserInputException exception = Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> story.assignMember(null)
        );

        Assertions.assertEquals(ASSIGNEE_CANNOT_BE_EMPTY, exception.getMessage());
    }

    @Test
    void testAssignMember_Should_AssignMember_And_LogHistory() {
        story.assignMember(getValidMockMember());
        assertEquals(getValidMockMember().getName(), story.getAssignee().getName());
        assertEquals(
                format("[%s] - %s",
                        formatTime(LocalDateTime.now()),
                        format(SET_STORY_ASSIGNEE_SUCCESSFULLY,
                                story.getTitle(),
                                story.getAssignee().getName(),
                                DEFAULT_ASSIGNEE.getName())
                ),
                story.getHistory().get(0));
    }

    @Test
    public void test_change_priority_success() {
        assertEquals(PriorityType.HIGH, story.getPriority());
    }

    @Test
    public void test_change_priority_shouldThrow_When_Already_Assigned() {
        story.changePriority(PriorityType.LOW);

        InvalidUserInputException exception = Assertions.assertThrows(
                InvalidUserInputException.class,
                () -> story.changePriority(PriorityType.LOW)
        );

        Assertions.assertEquals((format(STORY_PRIORITY_ALREADY_SET, story.getPriority())), exception.getMessage());
    }

    @Test
    public void test_change_priority_null_exception() {

        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            story.changePriority(null);
        });
        assertEquals(STORY_PRIORITY_TYPE_CANNOT_BE_EMPTY, exception.getMessage());
    }

    @Test
    public void test_log_history_on_priority_change() {

        story.changePriority(PriorityType.LOW);
        story.changePriority(PriorityType.HIGH);
        List<String> history = story.getHistory();
        assertTrue(history.get(history.size() - 1).contains("Story Priority changed from Low to High successfully."));
    }

    @Test
    public void test_exception_on_same_priority() {
        story.changePriority(PriorityType.MEDIUM);
        Exception exception = assertThrows(InvalidUserInputException.class, () -> {
            story.changePriority(PriorityType.MEDIUM);
        });
        assertEquals("Story Priority is already set to Medium!", exception.getMessage());
    }

    @Test
    public void test_return_updated_priority() {
        story.changePriority(PriorityType.LOW);
        assertEquals(PriorityType.LOW, story.getPriority());
        story.changePriority(PriorityType.HIGH);
        assertEquals(PriorityType.HIGH, story.getPriority());
    }
}

