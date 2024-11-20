package com.company.oop.task.management.system.tests.models.tasks;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.tasks.StoryImpl;
import com.company.oop.task.management.system.models.tasks.enums.PriorityType;
import com.company.oop.task.management.system.models.tasks.enums.StorySize;
import com.company.oop.task.management.system.models.tasks.enums.StoryStatus;
import com.company.oop.task.management.system.models.tasks.enums.TaskType;
import com.company.oop.task.management.system.models.teams.MemberImpl;
import com.company.oop.task.management.system.models.teams.contracts.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryImplTests {
    private StoryImpl story;
    //ToDo
    private static Member DEFAULT_MEMBER = new MemberImpl("unassigned");

    @BeforeEach
    void setUp() {
        story = new StoryImpl(1, "Implement login feature",
                "Allow users to log in", PriorityType.HIGH,
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
        assertEquals("JohnDoe", story.getAssignee());
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
        assertEquals("JohnDoe", story.getAssignee());
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
}

