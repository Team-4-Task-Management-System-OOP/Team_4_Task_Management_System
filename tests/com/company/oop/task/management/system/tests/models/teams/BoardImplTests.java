package com.company.oop.task.management.system.tests.models.teams;

import com.company.oop.task.management.system.exceptions.InvalidUserInputException;
import com.company.oop.task.management.system.models.teams.BoardImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardImplTests {
    private BoardImpl board;
    //ToDo
    @BeforeEach
    void setUp() {
        board = new BoardImpl("DevBoard");
    }

    @Test
    void testBoardInitialization() {
        assertNotNull(board);
        assertEquals("DevBoard", board.getName());
        assertTrue(board.getTasks().isEmpty());
        assertTrue(board.getHistory().isEmpty());
    }

//    @Test
//    void testAddTask_ValidTask() {
//        String task = "Implement login feature";
//        board.addTask(task);
//
//        List<Task> tasks = board.getTasks();
//        List<String> history = board.getActivityHistory();
//
//        assertEquals(1, tasks.size());
//        assertEquals(task, tasks.getFirst());
//        assertEquals(1, history.size());
//        assertEquals(task, history.getFirst());
//    }

    @Test
    void testAddTask_NullTask_ShouldThrowException() {
        InvalidUserInputException exception = assertThrows(InvalidUserInputException.class, () -> board.addTask(null));
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    void testGetTasks_WhenNoTasksAdded_ShouldReturnEmptyList() {
        assertTrue(board.getTasks().isEmpty());
    }

    @Test
    void testGetActivityHistory_WhenNoActivity_ShouldReturnEmptyList() {
        assertTrue(board.getHistory().isEmpty());
    }

//    @Test
//    void testAddMultipleTasks() {
//        Task task = new Task("Task 1");
//        board.addTask(task);
//
//        assertEquals(2, board.getTasks().size());
//        assertEquals("Task 1", board.getTasks().get(0));
//        assertEquals("Task 2", board.getTasks().get(1));
//        assertEquals(2, board.getActivityHistory().size());
//    }
}
