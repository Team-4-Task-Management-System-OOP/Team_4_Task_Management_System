package com.company.oop.task.management.system.tests.utils.models;

import com.company.oop.task.management.system.models.tasks.BoardImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardImplTests {
    private BoardImpl board;

    @BeforeEach
    void setUp() {
        board = new BoardImpl("DevBoard");
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(board);
        assertEquals("DevBoard", board.getName());
        assertTrue(board.getTasks().isEmpty());
        assertTrue(board.getActivityHistory().isEmpty());
    }

    @Test
    void testSetName_ValidName() {
        board.setName("TestBoard");
        assertEquals("TestBoard", board.getName());
    }

    @Test
    void testSetName_NameTooShort_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.setName("Dev"));
        assertTrue(exception.getMessage().contains("Name must be 5 and 10 symbols"));
    }

    @Test
    void testSetName_NameTooLong_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.setName("DevelopmentBoard"));
        assertTrue(exception.getMessage().contains("Name must be 5 and 10 symbols"));
    }

    @Test
    void testAddTask_ValidTask() {
        String task = "Implement login feature";
        board.addTask(task);

        List<String> tasks = board.getTasks();
        List<String> history = board.getActivityHistory();

        assertEquals(1, tasks.size());
        assertEquals(task, tasks.getFirst());
        assertEquals(1, history.size());
        assertEquals(task, history.getFirst());
    }

    @Test
    void testAddTask_NullTask_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> board.addTask(null));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void testGetTasks_WhenNoTasksAdded_ShouldReturnEmptyList() {
        assertTrue(board.getTasks().isEmpty());
    }

    @Test
    void testGetActivityHistory_WhenNoActivity_ShouldReturnEmptyList() {
        assertTrue(board.getActivityHistory().isEmpty());
    }

    @Test
    void testAddMultipleTasks() {
        board.addTask("Task 1");
        board.addTask("Task 2");

        assertEquals(2, board.getTasks().size());
        assertEquals("Task 1", board.getTasks().get(0));
        assertEquals("Task 2", board.getTasks().get(1));
        assertEquals(2, board.getActivityHistory().size());
    }
}
