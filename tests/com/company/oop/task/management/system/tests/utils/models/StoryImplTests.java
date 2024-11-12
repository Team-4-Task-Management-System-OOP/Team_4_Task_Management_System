package com.company.oop.task.management.system.tests.utils.models;

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
                StorySize.LARGE, StoryStatus.NOTDONE, DEFAULT_MEMBER);
    }

    @Test
    void testStoryInitialization() {
        assertNotNull(story);
        assertEquals(1, story.getId());
        assertEquals("Implement login feature", story.getTitle());
        assertEquals("Allow users to log in", story.getDescription());
        assertEquals(PriorityType.HIGH, story.getPriority());
        assertEquals(StorySize.LARGE, story.getSize());
        assertEquals(StoryStatus.NOTDONE, story.getStatus());
        assertEquals("JohnDoe", story.getAssignee());
        assertEquals(TaskType.STORY, story.getType());
    }

    @Test
    void testGetPriority() {
        assertEquals(PriorityType.HIGH, story.getPriority());
    }

    @Test
    void testGetSize() {
        assertEquals(StorySize.LARGE, story.getSize());
    }

    @Test
    void testGetStatus() {
        assertEquals(StoryStatus.NOTDONE, story.getStatus());
    }

    @Test
    void testGetAssignee() {
        assertEquals("JohnDoe", story.getAssignee());
    }

    @Test
    void testGetType() {
        assertEquals(TaskType.STORY, story.getType());
    }

    @Test
    void testChangeStatus() {
        story.changeStatus(StoryStatus.INPROGESS);
        assertEquals(StoryStatus.INPROGESS, story.getStatus());

        story.changeStatus(StoryStatus.DONE);
        assertEquals(StoryStatus.DONE, story.getStatus());
    }

    @Test
    void testChangeStatusInvalid() {
        StoryStatus currentStatus = story.getStatus();
        try {
            story.changeStatus(null);
            fail("Expected an IllegalArgumentException for null status.");
        } catch (IllegalArgumentException e) {
            assertEquals("Status cannot be null", e.getMessage());
        }
        assertEquals(currentStatus, story.getStatus());
    }
}

